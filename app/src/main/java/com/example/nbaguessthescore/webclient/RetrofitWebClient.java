package com.example.nbaguessthescore.webclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWebClient
{
    //private static final String BASE_URL = "https://data.nba.net/prod/v2/";
    private static final String API_BASE_URL = "https://localhost:44352/api/";

    public static Retrofit getRetrofitClient()
    {
        //Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
