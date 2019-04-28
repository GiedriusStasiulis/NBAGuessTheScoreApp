package com.example.nbaguessthescore.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.example.nbaguessthescore.models.Guess;
import com.example.nbaguessthescore.repositories.Repository;

public class GuessDetailActivityViewModel extends ViewModel
{
    private Repository repo;

    public MutableLiveData<Integer> ptsValue = new MutableLiveData<>();

    public void init()
    {
        repo = Repository.getInstance();
        initPtsValue();
    }

    public void initPtsValue()
    {
        int initValue = 1;
        ptsValue.setValue(initValue);
    }

    public MutableLiveData<Integer> getPtsValue()
    {
        return ptsValue;
    }

    public void incrementPtsVal()
    {
        int addValue = 1;
        ptsValue.setValue(ptsValue.getValue() + addValue);
    }

    public void decrementPtsVal()
    {
        int minusValue = 1;
        ptsValue.setValue(ptsValue.getValue() - minusValue);
    }

    public void setPts(int pts)
    {
        ptsValue.setValue(0);
        ptsValue.setValue(pts);
    }

    public void submitGuess(String userId, String gameId, String selTeam, int byPts)
    {
        Guess guess = new Guess(userId,gameId,selTeam,byPts);
        Log.d("SUBMIT","Guess: " + guess.toString());
    }
}
