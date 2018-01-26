package com.example.stenyo.stenyo_desafio_android.api.auth;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    public AuthenticationInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder();
                //.header("Authorization", "Bearer " + UserProfile.getInstance().getToken());
                //AQUI FICARIA TOKEN DE AUTENTICAÇÃO CASO NESCESSARIO, ASSIM TODAS REQUISIÇÕES SÃO INTERCEPTADAS E ADICIONADAS O TOKEN
        Request request = builder.build();
        return chain.proceed(request);
    }

}