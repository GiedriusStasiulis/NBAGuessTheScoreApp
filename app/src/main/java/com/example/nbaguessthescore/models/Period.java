package com.example.nbaguessthescore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Period {

    @SerializedName("current")
    @Expose
    private Integer current;

    @SerializedName("type")
    @Expose
    private Integer type;

    @SerializedName("maxRegular")
    @Expose
    private Integer maxRegular;

    @SerializedName("isHalftime")
    @Expose
    private Boolean isHalftime;

    @SerializedName("isEndOfPeriod")
    @Expose
    private Boolean isEndOfPeriod;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMaxRegular() {
        return maxRegular;
    }

    public void setMaxRegular(Integer maxRegular) {
        this.maxRegular = maxRegular;
    }

    public Boolean getIsHalftime() {
        return isHalftime;
    }

    public void setIsHalftime(Boolean isHalftime) {
        this.isHalftime = isHalftime;
    }

    public Boolean getIsEndOfPeriod() {
        return isEndOfPeriod;
    }

    public void setIsEndOfPeriod(Boolean isEndOfPeriod) {
        this.isEndOfPeriod = isEndOfPeriod;
    }
}