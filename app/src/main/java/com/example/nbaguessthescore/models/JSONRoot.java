package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class JSONRoot implements Serializable
{
    @SerializedName("numGames")
    @Expose
    private int gamesCount;

    @SerializedName("games")
    @Expose
    private ArrayList<Game> gamesArrList;

    public JSONRoot(int _gamesCount, ArrayList<Game> _gamesArrList)
    {
        this.gamesCount = _gamesCount;
        this.gamesArrList = _gamesArrList;
    }

    public int getGamesCount()
    {
        return gamesCount;
    }

    public ArrayList<Game> getGamesArrList()
    {
        return gamesArrList;
    }
}
