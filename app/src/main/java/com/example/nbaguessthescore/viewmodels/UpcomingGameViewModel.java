package com.example.nbaguessthescore.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.repositories.GameRepository;

public class UpcomingGameViewModel extends ViewModel
{
    private LiveData<JSONRoot> jsonRoot;
    private GameRepository gameRepo;

    public void init()
    {
        if(jsonRoot != null)
        {
            return;
        }

        gameRepo = GameRepository.getInstance();
        jsonRoot = gameRepo.getNumberOfUpcomingGames();

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
