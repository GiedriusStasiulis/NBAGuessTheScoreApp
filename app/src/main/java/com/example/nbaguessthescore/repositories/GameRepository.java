package com.example.nbaguessthescore.repositories;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.nbaguessthescore.models.Game;
import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.webclient.IRetrofitWebClient;
import com.example.nbaguessthescore.webclient.RetrofitWebClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    private Date startingDate;

    public static GameRepository getInstance()
    {
        if(instance == null)
        {
            instance = new GameRepository();
        }
        return instance;
    }

    public MutableLiveData<JSONRoot> getGamesFromRepo(final int statusNum, String baseUrl) throws ParseException {
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

                setStartingDate();
                while(startingDate.before(getSeasonEndDate()))
                {
                    incrementDateByOne(startingDate);
                }
            {

            }

                break;

            //Finished games
            case 3:

                break;

                default:
                    break;
        }
        return upGameJsonData;
    }

    public void setStartingDate() throws ParseException
    {
        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fDate = sdf.format(date);
        startingDate = sdf.parse(fDate);
    }

    public Date getSeasonEndDate() throws ParseException
    {
        String seasonEndDateStr = "20190701";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date seasonEndDate = sdf.parse(seasonEndDateStr);
        return seasonEndDate;
    }

    public void incrementDateByOne(Date startDate) throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date newDate = cal.getTime();
        String fDate = sdf.format(newDate);
        startingDate = sdf.parse(fDate);
    }
}