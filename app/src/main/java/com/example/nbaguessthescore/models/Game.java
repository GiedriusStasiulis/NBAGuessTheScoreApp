package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Period;
import java.util.List;

public class Game
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
    private Integer statusNum;

    @SerializedName("extendedStatusNum")
    @Expose
    private Integer extendedStatusNum;

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

    public Integer getSeasonStageId() {
        return seasonStageId;
    }

    public void setSeasonStageId(Integer seasonStageId) {
        this.seasonStageId = seasonStageId;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Boolean getIsGameActivated() {
        return isGameActivated;
    }

    public void setIsGameActivated(Boolean isGameActivated) {
        this.isGameActivated = isGameActivated;
    }

    public Integer getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(Integer statusNum) {
        this.statusNum = statusNum;
    }

    public Integer getExtendedStatusNum() {
        return extendedStatusNum;
    }

    public void setExtendedStatusNum(Integer extendedStatusNum) {
        this.extendedStatusNum = extendedStatusNum;
    }

    public String getStartTimeEastern() {
        return startTimeEastern;
    }

    public void setStartTimeEastern(String startTimeEastern) {
        this.startTimeEastern = startTimeEastern;
    }

    public String getStartTimeUTC() {
        return startTimeUTC;
    }

    public void setStartTimeUTC(String startTimeUTC) {
        this.startTimeUTC = startTimeUTC;
    }

    public String getStartDateEastern() {
        return startDateEastern;
    }

    public void setStartDateEastern(String startDateEastern) {
        this.startDateEastern = startDateEastern;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public Boolean getIsBuzzerBeater() {
        return isBuzzerBeater;
    }

    public void setIsBuzzerBeater(Boolean isBuzzerBeater) {
        this.isBuzzerBeater = isBuzzerBeater;
    }

    public GameDuration getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(GameDuration gameDuration) {
        this.gameDuration = gameDuration;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
