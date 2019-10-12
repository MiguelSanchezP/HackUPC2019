package com.miguelsanchezp.hackupcproject;

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

    Data () {

    }

    public Data (int EcoFriendlySecs, int PrivateSecs, int PublicSecs, double EcoFriendlyDistance, double PrivateDistance, double PublicDistance) {
        this.EcoFriendlySecs = EcoFriendlySecs;
        this.PrivateSecs = PrivateSecs;
        this.PublicSecs = PublicSecs;
        this.EcoFriendlyDistance = EcoFriendlyDistance;
        this.PrivateDistance = PrivateDistance;
        this.PublicDistance = PublicDistance;
        this.EcoFriendlyPercentage = EcoFriendlySecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        this.PrivatePercentage = PrivateSecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        this.PublicPercentage = PublicSecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        this.emittedCO2 = (.08*PrivateDistance/1000)+(.2*PublicDistance/1000);
        this.avoidedCO2 = (.08*EcoFriendlyDistance/1000)+(.6*PublicDistance/1000);
    }

    public int getEcoFriendlySecs () {
        return EcoFriendlySecs;
    }
    public int getPrivateSecs () {
        return PrivateSecs;
    }
    public int getPublicSecs () {
        return PublicSecs;
    }
    public double getEcoFriendlyPercentage () {
        return EcoFriendlyPercentage;
    }
    public double getPrivatePercentage () {
        return PrivatePercentage;
    }
    public double getPublicPercentage () {
        return PublicPercentage;
    }
    public double getEmittedCO2 () {
        return emittedCO2;
    }
    public double getAvoidedCO2 () {
        return avoidedCO2;
    }
    public double getEcoFriendlyDistance () {
        return EcoFriendlyDistance;
    }
    public double getPrivateDistance () {
        return PrivateDistance;
    }
    public double getPublicDistance () {
        return PublicDistance;
    }
    public void setEcoFriendlySecs (int secs) {
        EcoFriendlySecs = secs;
    }
    public void setPrivateSecs (int secs) {
        PrivateSecs = secs;
    }
    public void setPublicSecs (int secs) {
        PublicSecs = secs;
    }
    public void setEcoFriendlyDistance (double distance) {
        EcoFriendlyDistance = distance;
    }
    public void setPrivateDistance (double distance) {
        PrivateDistance = distance;
    }
    public void setPublicDistance (double distance) {
        PublicDistance = distance;
    }
    public void setOthers () {
        EcoFriendlyPercentage = (double)EcoFriendlySecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        PrivatePercentage = (double)PrivateSecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        PublicPercentage = (double)PublicSecs/(EcoFriendlySecs+PrivateSecs+PublicSecs)*100;
        emittedCO2 = (.08*PrivateDistance/1000)+(.2*PublicDistance/1000);
        avoidedCO2 = (.08*EcoFriendlyDistance/1000)+(.6*PublicDistance/1000);
    }

}
