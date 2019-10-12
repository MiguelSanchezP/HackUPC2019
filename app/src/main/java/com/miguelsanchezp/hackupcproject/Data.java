package com.miguelsanchezp.hackupcproject;

import static com.miguelsanchezp.hackupcproject.ReadData.sum;

public class Data {
    private int EcoFriendlySecs;
    private int PrivateSecs;
    private int PublicSecs;
    private double EcoFriendlyPercentage;
    private double PrivatePercentage;
    private double PublicPercentage;
    private double emittedCO2;
    private double avoidedCO2;
    private double EcoFriendlyDistance;
    private double PrivateDistance;
    private double PublicDistance;
    private int DailyScore;
    private int WeeklyScore;
    private int MonthlyScore;

    Data () {

    }

    public Data (int EcoFriendlySecs, int PrivateSecs, int PublicSecs, double EcoFriendlyDistance, double PrivateDistance, double PublicDistance) {
        this.EcoFriendlySecs = EcoFriendlySecs;
        this.PrivateSecs = PrivateSecs;
        this.PublicSecs = PublicSecs;
        this.EcoFriendlyDistance = EcoFriendlyDistance;
        this.PrivateDistance = PrivateDistance;
        this.PublicDistance = PublicDistance;
        if ((EcoFriendlySecs+PrivateSecs+PublicSecs) == 0) {
            this.EcoFriendlyPercentage = 0;
            this.PrivatePercentage = 0;
            this.PublicPercentage = 0;
        }else {
            this.EcoFriendlyPercentage = EcoFriendlySecs / (EcoFriendlySecs + PrivateSecs + PublicSecs) * 100;
            this.PrivatePercentage = PrivateSecs / (EcoFriendlySecs + PrivateSecs + PublicSecs) * 100;
            this.PublicPercentage = PublicSecs / (EcoFriendlySecs + PrivateSecs + PublicSecs) * 100;
        }
        this.emittedCO2 = (.08*PrivateDistance/1000)+(.2*PublicDistance/1000);
        this.avoidedCO2 = (.08*EcoFriendlyDistance/1000)+(.6*PublicDistance/1000);
        this.DailyScore = (int)(EcoFriendlyPercentage+PublicPercentage/2);
        this.WeeklyScore = sum(7)/7;
        this.MonthlyScore = sum(30)/30;
    }

    int getEcoFriendlySecs () {
        return EcoFriendlySecs;
    }
    int getPrivateSecs () {
        return PrivateSecs;
    }
    int getPublicSecs () {
        return PublicSecs;
    }
    double getEcoFriendlyPercentage () {
        return EcoFriendlyPercentage;
    }
    double getPrivatePercentage () {
        return PrivatePercentage;
    }
    double getPublicPercentage () {
        return PublicPercentage;
    }
    double getEmittedCO2 () {
        return emittedCO2;
    }
    double getAvoidedCO2 () {
        return avoidedCO2;
    }
    double getEcoFriendlyDistance () {
        return EcoFriendlyDistance;
    }
    double getPrivateDistance () {
        return PrivateDistance;
    }
    double getPublicDistance () {
        return PublicDistance;
    }
    int getDailyScore () {
        return DailyScore;
    }
    int getWeeklyScore () {
        return WeeklyScore;
    }
    int getMonthlyScore() {
        return MonthlyScore;
    }
    void setEcoFriendlySecs (int secs) {
        EcoFriendlySecs = secs;
    }
    void setPrivateSecs (int secs) {
        PrivateSecs = secs;
    }
    void setPublicSecs (int secs) {
        PublicSecs = secs;
    }
    void setEcoFriendlyDistance (double distance) {
        EcoFriendlyDistance = distance;
    }
    void setPrivateDistance (double distance) {
        PrivateDistance = distance;
    }
    void setPublicDistance (double distance) {
        PublicDistance = distance;
    }
    void setOthers () {
        EcoFriendlyPercentage = (double)EcoFriendlySecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        PrivatePercentage = (double)PrivateSecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        PublicPercentage = (double)PublicSecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        emittedCO2 = (.08*PrivateDistance/1000)+(.2*PublicDistance/1000);
        avoidedCO2 = (.08*EcoFriendlyDistance/1000)+(.6*PublicDistance/1000);
        DailyScore = (int)(EcoFriendlyPercentage+PublicPercentage/2);
        WeeklyScore = sum(7)/7;
        MonthlyScore = sum(30)/30;
    }

}
