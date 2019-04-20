package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Period implements Serializable
{
    @SerializedName("current")
    @Expose
    private int current;

    @SerializedName("type")
    @Expose
    private int type;

    @SerializedName("maxRegular")
    @Expose
    private int maxRegular;

    @SerializedName("isHalftime")
    @Expose
    private Boolean isHalftime;

    @SerializedName("isEndOfPeriod")
    @Expose
    private Boolean isEndOfPeriod;

    public int getCurrent()
    {
        return current;
    }

    public void setCurrent(int current)
    {
        this.current = current;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getMaxRegular()
    {
        return maxRegular;
    }

    public void setMaxRegular(int maxRegular)
    {
        this.maxRegular = maxRegular;
    }

    public Boolean getIsHalftime()
    {
        return isHalftime;
    }

    public void setIsHalftime(Boolean isHalftime)
    {
        this.isHalftime = isHalftime;
    }

    public Boolean getIsEndOfPeriod()
    {
        return isEndOfPeriod;
    }

    public void setIsEndOfPeriod(Boolean isEndOfPeriod)
    {
        this.isEndOfPeriod = isEndOfPeriod;
    }
}