package com.miguelsanchezp.hackupcproject;

public class PendingItem {
    private String initialTime;
    private String finalTime;
    private String distance;

    public PendingItem (String initialTime, String finalTime, String distance) {
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.distance = distance;
    }

    public PendingItem (String initialTime, String finalTime) {
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }

    public String getInitialTime () {
        return initialTime;
    }
    public String getFinalTime () {
        return finalTime;
    }
    public String getDistance () {
        return distance;
    }
}
