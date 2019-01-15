package com.example.models;

public class MyRadio implements Radio {

    public static double STEPS_FM = 0.3;
    public static double STEPS_AM = 0.5;
    public static double LOWER_LIMIT_AM = 78.1;
    public static double UPPER_LIMIT_AM = 78.1;
    public static double LOWER_LIMIT_FM = 88.0;
    public static double UPPER_LIMIT_FM = 108.0;
    //TODO: Conts. for lower and upper limits for AM and FM

    private boolean isTurnedOn;
    private boolean isOnFM;
    private double currentStation;
    private double[] stationsFM;
    private double[] stationsAM;

    public MyRadio(){
        this.isTurnedOn = true;
        this.isOnFM = false;
        this.stationsAM = new double[12];
        this.stationsFM = new double[12];
    }

    @Override
    public void toggle() {
        this.isTurnedOn = !isTurnedOn;
    }

    @Override
    public boolean getState() {
        return this.isTurnedOn;
    }

    @Override
    public void changeFrequency() {
        this.isOnFM = !isOnFM;
    }

    @Override
    public void changeStation(boolean up) {
        //TODO: Check upper and lower limit if is on AM or FM
        if(isOnFM){
            if(up){
                this.currentStation += STEPS_FM;
            } else {
                this.currentStation -= STEPS_FM;
            }
        } else {
            if(up){
                this.currentStation += STEPS_AM;
            } else {
                this.currentStation -= STEPS_AM;
            }
        }
    }

    @Override
    public boolean getFrequency() {
        return this.isOnFM;
    }

    @Override
    public void saveStation(int numButton) {
        if(isOnFM){
            this.stationsFM[numButton] = currentStation;
        } else {
            this.stationsAM[numButton] = currentStation;
        }

    }

    @Override
    public void changeStationButton(int numButton) {
        this.currentStation = ((isOnFM) ? this.stationsFM[numButton] : this.stationsAM[numButton]);
    }

    @Override
    public double getStation() {
        return this.currentStation;
    }
}
