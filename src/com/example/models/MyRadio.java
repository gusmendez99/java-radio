package com.example.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MyRadio implements Radio {

    public final static double STEPS_AM = 10;
    public final static double STEPS_FM = 0.2;
    public final static double LOWER_LIMIT_AM = 530;
    public final static double UPPER_LIMIT_AM = 1610;
    public final static double LOWER_LIMIT_FM = 87.9;
    public final static double UPPER_LIMIT_FM = 107.9;

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
        if (this.isOnFM){
            currentStation = LOWER_LIMIT_AM;
        } else {
            currentStation = LOWER_LIMIT_FM;
        }
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
            this.currentStation = this.stations[numButton - 1];
        } else if (LOWER_LIMIT_AM <= this.stations[numButton-1] && this.stations[numButton-1] <= UPPER_LIMIT_AM){
            this.isOnFM = false;
            this.currentStation = this.stations[numButton - 1];
        }

    }

    @Override
    public double getStation() {
        return this.currentStation;
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

    @Override
    public String toString(){

        NumberFormat formatter = new DecimalFormat("#0.0");

        String finalRadioStr = "\t\t\t\t\t\t\t___\n\t\t\t\t\t\t\t|\t|\n" +
                "\t\t\t\t\t\t\t|\t|\n---------------------------------\n" +
                "Radio: \n\t" +
                "Frecuencia: " + ((this.isOnFM ? "FM" : "AM")) + "\n\t" +
                "Emisora actual: " + formatter.format(this.currentStation) + "\n\n\t" + getFormattedRadioButtons() +
                "\n---------------------------------\n";

        return finalRadioStr;
    }



}