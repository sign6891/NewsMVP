package com.example.newsmvp.service;

import com.example.newsmvp.model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsEndpoint {

    //https://api.nytimes.com/svc/topstories/v2/world.json?api-key=sVNYUCDqQngxDsUy0yfmp3piOCrlWAIg

   // @GET("svc/topstories/v2/world.json")
    @GET("svc/topstories/v2/{section}.json?")
    Call<NewsList> getNewsList(@Path("section") String section, @Query("api-key") String apiKey);
}
