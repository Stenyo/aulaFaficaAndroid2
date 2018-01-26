package com.example.stenyo.stenyo_desafio_android.injectors.modules;

import com.example.stenyo.stenyo_desafio_android.api.service.RepositoriesService;
import com.example.stenyo.stenyo_desafio_android.injectors.scope.PerUser;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class APIModule {

    @Provides
    @PerUser
    public RepositoriesService providesActionInterface(Retrofit retrofit) {
        return retrofit.create(RepositoriesService.class);
    }


}
