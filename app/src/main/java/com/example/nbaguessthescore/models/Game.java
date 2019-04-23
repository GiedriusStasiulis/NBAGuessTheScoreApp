package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.Period;

public class Game implements Serializable
{
    @SerializedName("seasonStageId")
    @Expose
    private Integer seasonStageId;

    @SerializedName("seasonYear")
    @Expose
    private String seasonYear;

    @SerializedName("gameId")
    @Expose
    private String gameId;

    @SerializedName("isGameActivated")
    @Expose
    private Boolean isGameActivated;

    @SerializedName("statusNum")
    @Expose
    private int statusNum;

    @SerializedName("extendedStatusNum")
    @Expose
    private int extendedStatusNum;

    @SerializedName("startTimeEastern")
    @Expose
    private String startTimeEastern;

    @SerializedName("startTimeUTC")
    @Expose
    private String startTimeUTC;

    @SerializedName("startDateEastern")
    @Expose
    private String startDateEastern;

    @SerializedName("clock")
    @Expose
    private String clock;

    @SerializedName("isBuzzerBeater")
    @Expose
    private Boolean isBuzzerBeater;

    @SerializedName("gameDuration")
    @Expose
    private GameDuration gameDuration;

    @SerializedName("period")
    @Expose
    private Period period;

    @SerializedName("vTeam")
    @Expose
    private VTeam vTeam;
    @SerializedName("hTeam")
    @Expose
    private HTeam hTeam;

    public Game(int seasonStageId,
                String seasonYear,
                String gameId,
                Boolean isGameActivated,
                int statusNum,
                int extendedStatusNum,
                String startTimeEastern,
                String startTimeUTC,
                String startDateEastern,
                String clock,
                Boolean isBuzzerBeater,
                GameDuration gameDuration,
                Period period,
                VTeam vTeam,
                HTeam hTeam)
    {
        this.seasonStageId = seasonStageId;
        this.seasonYear = seasonYear;
        this.gameId = gameId;
        this.isGameActivated = isGameActivated;
        this.statusNum = statusNum;
        this.extendedStatusNum = extendedStatusNum;
        this.startTimeEastern = startTimeEastern;
        this.startTimeUTC = startTimeUTC;
        this.startDateEastern = startDateEastern;
        this.clock = clock;
        this.isBuzzerBeater = isBuzzerBeater;
        this.gameDuration = gameDuration;
        this.period = period;
        this.vTeam = vTeam;
        this.hTeam = hTeam;
    }

    public int getSeasonStageId()
    {
        return seasonStageId;
    }

    public void setSeasonStageId(int seasonStageId)
    {
        this.seasonStageId = seasonStageId;
    }

    public String getSeasonYear()
    {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear)
    {
        this.seasonYear = seasonYear;
    }

    public String getGameId()
    {
        return gameId;
    }

    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }

    public Boolean getIsGameActivated()
    {
        return isGameActivated;
    }

    public void setIsGameActivated(Boolean isGameActivated)
    {
        this.isGameActivated = isGameActivated;
    }

    public int getStatusNum()
    {
        return statusNum;
    }

    public void setStatusNum(int statusNum)
    {
        this.statusNum = statusNum;
    }

    public int getExtendedStatusNum()
    {
        return extendedStatusNum;
    }

    public void setExtendedStatusNum(int extendedStatusNum)
    {
        this.extendedStatusNum = extendedStatusNum;
    }

    public String getStartTimeEastern()
    {
        if(!startTimeEastern.isEmpty())
        {
            return startTimeEastern;
        }

        return "TDB";
    }

    public void setStartTimeEastern(String startTimeEastern)
    {
        this.startTimeEastern = startTimeEastern;
    }

    public String getStartTimeUTC()
    {
        return startTimeUTC;
    }

    public void setStartTimeUTC(String startTimeUTC)
    {
        this.startTimeUTC = startTimeUTC;
    }

    public String getStartDateEastern()
    {
        return startDateEastern;
    }

    public void setStartDateEastern(String startDateEastern)
    {
        this.startDateEastern = startDateEastern;
    }

    public String getClock()
    {
        return clock;
    }

    public void setClock(String clock)
    {
        this.clock = clock;
    }

    public Boolean getIsBuzzerBeater()
    {
        return isBuzzerBeater;
    }

    public void setIsBuzzerBeater(Boolean isBuzzerBeater)
    {
        this.isBuzzerBeater = isBuzzerBeater;
    }

    public GameDuration getGameDuration()
    {
        return gameDuration;
    }

    public void setGameDuration(GameDuration gameDuration)
    {
        this.gameDuration = gameDuration;
    }

    public Period getPeriod()
    {
        return period;
    }

    public void setPeriod(Period period)
    {
        this.period = period;
    }

    public VTeam getVTeam() {
        return vTeam;
    }

    public void setVTeam(VTeam vTeam) {
        this.vTeam = vTeam;
    }

    public HTeam getHTeam() {
        return hTeam;
    }

    public void setHTeam(HTeam hTeam) {
        this.hTeam = hTeam;
    }
}