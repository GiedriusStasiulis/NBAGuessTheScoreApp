package com.example.nbaguessthescore.models;

import java.io.Serializable;

public class Guess implements Serializable
{
    private String userId, gameId, selTeam;
    private int byPts;

    public Guess(String userId, String gameId, String selTeam, int byPts) {
        this.userId = userId;
        this.gameId = gameId;
        this.selTeam = selTeam;
        this.byPts = byPts;
    }

    public Guess() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getSelTeam() {
        return selTeam;
    }

    public void setSelTeam(String selTeam) {
        this.selTeam = selTeam;
    }

    public int getByPts() {
        return byPts;
    }

    public void setByPts(int byPts) {
        this.byPts = byPts;
    }
}
