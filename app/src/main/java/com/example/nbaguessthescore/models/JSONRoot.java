package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JSONRoot implements Serializable
{
    @SerializedName("numGames")
    @Expose
    private int numGames;

    @SerializedName("games")
    @Expose
    private List<Game> games = null;

    public int getNumGames()
    {
        return numGames;
    }

    public void setNumGames(int numGames)
    {
        this.numGames = numGames;
    }

    public List<Game> getGames()
    {
        return games;
    }

    public void setGames(List<Game> games)
    {
        this.games = games;
    }
}
