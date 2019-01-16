package com.example.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class MyRadio implements Radio {

    private static double STEPS_AM = 10;
    private static double STEPS_FM = 0.2;
    private static double LOWER_LIMIT_AM = 530;
    private static double UPPER_LIMIT_AM = 1610;
    private static double LOWER_LIMIT_FM = 87.9;
    private static double UPPER_LIMIT_FM = 107.9;

    private boolean isTurnedOn;
    private boolean isOnFM;
    private double currentStation;
    private double[] stations;

    public MyRadio() {
        this.isTurnedOn = false;
        this.isOnFM = false;
        this.stations = new double[12];
        this.currentStation = LOWER_LIMIT_AM;
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
        if (isOnFM) {
            if (up) {
                if (this.currentStation + STEPS_FM <= UPPER_LIMIT_FM) {
                    this.currentStation += STEPS_FM;
                } else {
                    this.currentStation = LOWER_LIMIT_FM;
                }
            } else {
                if (this.currentStation - STEPS_FM >= LOWER_LIMIT_FM) {
                    this.currentStation -= STEPS_FM;
                } else {
                    this.currentStation = UPPER_LIMIT_FM;
                }
            }
        } else {
            if (up) {
                if (this.currentStation + STEPS_AM <= UPPER_LIMIT_AM) {
                    this.currentStation += STEPS_AM;
                } else {
                    this.currentStation = LOWER_LIMIT_AM;
                }
            } else {
                if (this.currentStation - STEPS_AM >= LOWER_LIMIT_AM) {
                    this.currentStation -= STEPS_AM;
                } else {
                    this.currentStation = UPPER_LIMIT_AM;
                }
            }
        }
    }

    @Override
    public boolean getFrequency() {
        return this.isOnFM;
    }

    @Override
    public void saveStation(int numButton) {
        this.stations[numButton-1] = currentStation;

    }

    @Override
    public void changeStationButton(int numButton) {
        if (LOWER_LIMIT_FM <= this.stations[numButton-1] && this.stations[numButton-1] <= UPPER_LIMIT_FM){
            this.isOnFM = true;
        } else if (LOWER_LIMIT_AM <= this.stations[numButton-1] && this.stations[numButton-1] <= UPPER_LIMIT_AM){
            this.isOnFM = false;
        }
        this.currentStation = this.stations[numButton - 1];
    }

    @Override
    public double getStation() {
        return this.currentStation;
    }

    //TODO: Check if this method can be removed, or replaced
    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    private String getFormattedRadioButtons(){
        String formattedStations = "";
        for(int i = 0; i < this.stations.length; i++){

            if(stations[i] != 0) {
                formattedStations += stations[i] + "\t";
            } else {
                formattedStations += (i + 1) + "\t\t";
            }

            if((i + 1)%4 == 0 && i < this.stations.length - 1){
                formattedStations += "\n\t";
            }
        }
        return formattedStations;
    }



}