package com.example.stenyo.stenyo_desafio_android.injectors.components;

import android.content.SharedPreferences;

import com.example.stenyo.stenyo_desafio_android.injectors.modules.AppModule;
import com.example.stenyo.stenyo_desafio_android.injectors.modules.WebServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(
        modules = {
                AppModule.class,
                WebServiceModule.class,
        })
public interface WebServiceComponent {

    Retrofit retrofit();

    OkHttpClient okHttpClient();

    SharedPreferences sharedPreferences();

}
