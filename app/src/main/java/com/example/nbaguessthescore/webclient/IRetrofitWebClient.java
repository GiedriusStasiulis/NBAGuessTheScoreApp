package com.example.nbaguessthescore.webclient;

import com.example.nbaguessthescore.models.Guess;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IRetrofitWebClient
{
    @Headers("Content-Type: application/json")
    @POST("guess/")
    Call<Guess> postGuess(@Body Guess guess);

    @Headers("Content-Type: application/json")
    @PUT("guess/")
    Call<Guess> putGuess(@Body Guess guess);

    @Headers("Content-Type: application/json")
    @GET("guess/")
    Call<Guess> checkGuess(@Body Guess guess);
}