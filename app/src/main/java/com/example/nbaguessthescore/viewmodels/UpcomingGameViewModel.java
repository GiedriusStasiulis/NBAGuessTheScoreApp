package com.example.nbaguessthescore.viewmodels;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.repositories.GameRepository;

public class UpcomingGameViewModel extends ViewModel
{
    private LiveData<JSONRoot> jsonRoot;
    private GameRepository gameRepo;
    private MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    public void init()
    {
        if(jsonRoot != null)
        {
            return;
        }

        gameRepo = GameRepository.getInstance();
        jsonRoot = gameRepo.getNumberOfUpcomingGames();
    }

    @SuppressLint("StaticFieldLeak")
    public void refreshUpcomingGames()
    {
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                isRefreshing.setValue(true);
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                super.onPostExecute(aVoid);
                gameRepo = GameRepository.getInstance();
                jsonRoot = gameRepo.getNumberOfUpcomingGames();
                isRefreshing.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids)
            {
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<Boolean> getIsRefreshing()
    {
        return isRefreshing;
    }

    public LiveData<JSONRoot> getNumOfUpGames()
    {
        if(jsonRoot == null)
        {
            gameRepo = GameRepository.getInstance();
            jsonRoot = gameRepo.getNumberOfUpcomingGames();
        }

        return jsonRoot;
    }
}