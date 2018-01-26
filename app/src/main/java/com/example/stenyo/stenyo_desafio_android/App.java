package com.example.stenyo.stenyo_desafio_android;

import android.app.Application;


import com.example.stenyo.stenyo_desafio_android.activitys.MainActivity;
import com.example.stenyo.stenyo_desafio_android.injectors.components.APIComponent;
import com.example.stenyo.stenyo_desafio_android.injectors.components.DaggerAPIComponent;
import com.example.stenyo.stenyo_desafio_android.injectors.components.DaggerWebServiceComponent;
import com.example.stenyo.stenyo_desafio_android.injectors.components.WebServiceComponent;
import com.example.stenyo.stenyo_desafio_android.injectors.modules.AppModule;
import com.example.stenyo.stenyo_desafio_android.injectors.modules.WebServiceModule;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


public class App extends Application {

    private WebServiceComponent webServiceComponent;

    private APIComponent apiComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        Iconify.with(new FontAwesomeModule());

        webServiceComponent = DaggerWebServiceComponent
                .builder()
                .appModule(new AppModule(this))
                .webServiceModule(new WebServiceModule(BuildConfig.API_URL))
                .build();

        apiComponent = DaggerAPIComponent
                .builder()
                .webServiceComponent(webServiceComponent)
                .build();

        //Hawk.init(this).build();

        //getAPIComponent().inject();

        //printKeyHash();
    }
/*
    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("br.com.parallaxsolutions.lanceminimo", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            Log.e("KeyHash:", e.toString());
        }
    }
*/
    public WebServiceComponent getWebServiceComponent() {
        return webServiceComponent;
    }

    public APIComponent getAPIComponent() {
        return apiComponent;
    }
}
