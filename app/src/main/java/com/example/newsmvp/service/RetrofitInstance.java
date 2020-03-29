package com.example.newsmvp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private final static String BASE_URL = "https://api.nytimes.com/";

    public static NewsEndpoint getInstance() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NewsEndpoint.class);
    }
}
