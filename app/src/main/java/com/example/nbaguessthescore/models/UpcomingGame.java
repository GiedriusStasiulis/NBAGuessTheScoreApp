package com.example.nbaguessthescore.models;

import java.io.Serializable;

public class UpcomingGame implements Serializable
{
    private String GameId,GameDateUTC,GameStartTimeUTC,TeamATriCode,TeamAFullName,TeamALogoSrc,TeamBTriCode,TeamBFullName,TeamBLogoSrc,TeamAScore,TeamBScore,LastUpdated;
    private int OrderNo, StatusNum;

    public UpcomingGame(String gameId, String gameDateUTC, String gameStartTimeUTC, String teamATriCode, String teamAFullName, String teamALogoSrc, String teamBTriCode, String teamBFullName, String teamBLogoSrc, String teamAScore, String teamBScore, String lastUpdated, int orderNo, int statusNum) {
        GameId = gameId;
        GameDateUTC = gameDateUTC;
        GameStartTimeUTC = gameStartTimeUTC;
        TeamATriCode = teamATriCode;
        TeamAFullName = teamAFullName;
        TeamALogoSrc = teamALogoSrc;
        TeamBTriCode = teamBTriCode;
        TeamBFullName = teamBFullName;
        TeamBLogoSrc = teamBLogoSrc;
        TeamAScore = teamAScore;
        TeamBScore = teamBScore;
        LastUpdated = lastUpdated;
        OrderNo = orderNo;
        StatusNum = statusNum;
    }

    public UpcomingGame() {
    }

    public String getGameId() {
        return GameId;
    }

    public void setGameId(String gameId) {
        GameId = gameId;
    }

    public String getGameDateUTC() {
        return GameDateUTC;
    }

    public void setGameDateUTC(String gameDateUTC) {
        GameDateUTC = gameDateUTC;
    }

    public String getGameStartTimeUTC() {
        return GameStartTimeUTC;
    }

    public void setGameStartTimeUTC(String gameStartTimeUTC) {
        GameStartTimeUTC = gameStartTimeUTC;
    }

    public String getTeamATriCode() {
        return TeamATriCode;
    }

    public void setTeamATriCode(String teamATriCode) {
        TeamATriCode = teamATriCode;
    }

    public String getTeamAFullName() {
        return TeamAFullName;
    }

    public void setTeamAFullName(String teamAFullName) {
        TeamAFullName = teamAFullName;
    }

    public String getTeamALogoSrc() {
        return TeamALogoSrc;
    }

    public void setTeamALogoSrc(String teamALogoSrc) {
        TeamALogoSrc = teamALogoSrc;
    }

    public String getTeamBTriCode() {
        return TeamBTriCode;
    }

    public void setTeamBTriCode(String teamBTriCode) {
        TeamBTriCode = teamBTriCode;
    }

    public String getTeamBFullName() {
        return TeamBFullName;
    }

    public void setTeamBFullName(String teamBFullName) {
        TeamBFullName = teamBFullName;
    }

    public String getTeamBLogoSrc() {
        return TeamBLogoSrc;
    }

    public void setTeamBLogoSrc(String teamBLogoSrc) {
        TeamBLogoSrc = teamBLogoSrc;
    }

    public String getTeamAScore() {
        return TeamAScore;
    }

    public void setTeamAScore(String teamAScore) {
        TeamAScore = teamAScore;
    }

    public String getTeamBScore() {
        return TeamBScore;
    }

    public void setTeamBScore(String teamBScore) {
        TeamBScore = teamBScore;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }

    public int getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(int orderNo) {
        OrderNo = orderNo;
    }

    public int getStatusNum() {
        return StatusNum;
    }

    public void setStatusNum(int statusNum) {
        StatusNum = statusNum;
    }
}