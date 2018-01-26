package com.example.stenyo.stenyo_desafio_android.injectors.components;

import com.example.stenyo.stenyo_desafio_android.activitys.MainActivity;
import com.example.stenyo.stenyo_desafio_android.activitys.RequestActivity;
import com.example.stenyo.stenyo_desafio_android.injectors.modules.APIModule;
import com.example.stenyo.stenyo_desafio_android.injectors.scope.PerUser;

import dagger.Component;

@PerUser
@Component(
        dependencies = WebServiceComponent.class,
        modules = APIModule.class
)
public interface APIComponent {

    void inject(MainActivity activity);

    void inject(RequestActivity activity);


}
