package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HTeam implements Serializable
{
    @SerializedName("triCode")
    @Expose
    private String triCode;

    @SerializedName("score")
    @Expose
    private String score;

    public HTeam(String triCode, String score)
    {
        this.triCode = triCode;
        this.score = score;
    }

    public String getTriCode()
    {
        return triCode;
    }

    public void setTriCode(String triCode)
    {
        this.triCode = triCode;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }
}