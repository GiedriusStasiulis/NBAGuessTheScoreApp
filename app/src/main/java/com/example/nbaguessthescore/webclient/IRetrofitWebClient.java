package com.example.nbaguessthescore.webclient;

import com.example.nbaguessthescore.models.JSONRoot;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface IRetrofitWebClient
{
    @Headers("Content-Type: application/json")
    @GET
    Call<JSONRoot> getJsonRoot(@Url String url);
}