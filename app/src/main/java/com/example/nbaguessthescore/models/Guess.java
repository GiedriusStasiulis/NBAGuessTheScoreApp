package com.example.nbaguessthescore.models;

public class Guess
{
    public String userId, gameId, selTeam;
    public int byPts;

    public Guess(String userId, String gameId, String selTeam, int byPts) {
        this.userId = userId;
        this.gameId = gameId;
        this.selTeam = selTeam;
        this.byPts = byPts;
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

    @Override
    public String toString() {
        return "Guess{" +
                "userId='" + userId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", selTeam='" + selTeam + '\'' +
                ", byPts=" + byPts +
                '}';
    }
}
