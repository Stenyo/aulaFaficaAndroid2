package com.example.stenyo.stenyo_desafio_android.models;

import java.io.Serializable;

public class RequestAuth implements Serializable {

    public static final String GRANT_TYPE_USER_CREDENTIALS = "user_credentials";
    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    public String clientId;

    public String clientSecret;

    public String grantType;

    public String accessToken;

}
