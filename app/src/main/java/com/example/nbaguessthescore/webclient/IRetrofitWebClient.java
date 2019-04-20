package com.example.nbaguessthescore.webclient;

import com.example.nbaguessthescore.models.JSONRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IRetrofitWebClient
{
    @Headers("Content-Type: application/json")
    @GET("scoreboard.json")
    Call<JSONRoot> getJsonRoot();
}
