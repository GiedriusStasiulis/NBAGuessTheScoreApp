package com.example.nbaguessthescore.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.webclient.IRetrofitWebClient;
import com.example.nbaguessthescore.webclient.RetrofitWebClient;

import java.lang.invoke.MutableCallSite;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository
{
    private static GameRepository instance;

    private JSONRoot jRoot;

    final MutableLiveData<JSONRoot> data = new MutableLiveData<JSONRoot>();

    public static GameRepository getInstance()
    {
        if(instance == null)
        {
            instance = new GameRepository();
        }
        return instance;
    }

    public MutableLiveData<JSONRoot> getNumberOfUpcomingGames()
    {
        IRetrofitWebClient rfClient = RetrofitWebClient.getRetrofitClient().create(IRetrofitWebClient.class);
        Call<JSONRoot> call = rfClient.getJsonRoot();

        call.enqueue(new Callback<JSONRoot>()
        {
            @Override
            public void onResponse(Call<JSONRoot> call, Response<JSONRoot> response)
            {
                response.body();

                if(response != null && response.isSuccessful())
                {
                    Log.d("OnSuccess", "onResponse: " + response.toString());

                    int noGames = Integer.valueOf(response.body().getNumGames());

                    jRoot = new JSONRoot(noGames);

                    Log.d("OnSuccess", "onResponse: " + jRoot.toString());

                    data.setValue(jRoot);
                }
            }

            @Override
            public void onFailure(Call<JSONRoot> call, Throwable t)
            {
                Log.e("OnFailure", "Failure: Sum ting wong: "  +  t.getMessage() + " , StackTrace: " + t.getLocalizedMessage());
            }
        });

        return data;
    }
}