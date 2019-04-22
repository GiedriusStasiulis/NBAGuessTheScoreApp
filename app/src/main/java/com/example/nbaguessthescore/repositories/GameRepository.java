package com.example.nbaguessthescore.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.nbaguessthescore.models.Game;
import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.webclient.IRetrofitWebClient;
import com.example.nbaguessthescore.webclient.RetrofitWebClient;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository
{
    private static GameRepository instance;

    private JSONRoot gameJsonRoot;
    private final MutableLiveData<JSONRoot> upGameJsonData = new MutableLiveData<>();

    private ArrayList<Game> allGamesArrList = new ArrayList<>();
    private ArrayList<Game> upcomingGamesArrList = new ArrayList<>();

    public static GameRepository getInstance()
    {
        if(instance == null)
        {
            instance = new GameRepository();
        }
        return instance;
    }

    public MutableLiveData<JSONRoot> getGamesFromRepo(final int statusNum, String baseUrl)
    {
        IRetrofitWebClient rfClient = RetrofitWebClient.getRetrofitClient().create(IRetrofitWebClient.class);

        switch(statusNum)
        {
            //Upcoming games
            case 1:

                Call<JSONRoot> call = rfClient.getJsonRoot(baseUrl);
                call.enqueue(new Callback<JSONRoot>()
                {
                    @Override
                    public void onResponse(Call<JSONRoot> call, Response<JSONRoot> response)
                    {
                        response.body();

                        if(response.isSuccessful())
                        {
                            assert response.body() != null;

                            allGamesArrList.clear();
                            upcomingGamesArrList.clear();
                            allGamesArrList = response.body().getGamesArrList();

                            for(int i = 0; i < allGamesArrList.size(); i++)
                            {
                                if(allGamesArrList.get(i).getStatusNum() == statusNum)
                                {
                                    upcomingGamesArrList.add(allGamesArrList.get(i));
                                }
                            }
                            gameJsonRoot = new JSONRoot(upcomingGamesArrList.size(),upcomingGamesArrList);
                            upGameJsonData.setValue(gameJsonRoot);
                        }
                    }
                    @Override
                    public void onFailure(Call<JSONRoot> call, Throwable t)
                    {
                        Log.e("OnFailure", "Failure: Sum ting wong: "  +  t.getMessage() + " , StackTrace: " + t.getLocalizedMessage());
                    }
                });
                break;

            //Live games
            case 2:

                break;

            //Finished games
            case 3:

                break;

                default:
                    break;
        }
        return upGameJsonData;
    }
}