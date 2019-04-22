package com.example.nbaguessthescore.webclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWebClient
{
    private static final String BASE_URL = "https://data.nba.net/prod/v2/";

    public static Retrofit getRetrofitClient()
    {
        //Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
