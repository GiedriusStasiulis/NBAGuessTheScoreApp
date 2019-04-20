package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONRoot
{
    @SerializedName("numGames")
    @Expose
    private Integer numGames;

    @SerializedName("games")
    @Expose
    private List<Game> games = null;

    public Integer getNumGames() {
        return numGames;
    }

    public void setNumGames(Integer numGames) {
        this.numGames = numGames;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
