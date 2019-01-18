package com.example.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MyRadio implements Radio {
    /**
     * Quantity of steps between stations in an AM frequency change
     */
    public final static double STEPS_AM = 10;
    /**
     * Quantity of steps between stations in an FM frequency change
     */
    public final static double STEPS_FM = 0.2;
    /**
     * Lowest station AM frequency supports
     */
    public final static double LOWER_LIMIT_AM = 530;
    /**
     * Highest station AM frequency supports
     */
    public final static double UPPER_LIMIT_AM = 1610;
    /**
     * Lowest station FM frequency supports
     */
    public final static double LOWER_LIMIT_FM = 87.9;
    /**
     * Highest station AM frequency supports
     */
    public final static double UPPER_LIMIT_FM = 107.9;

    /**
     * False if MyRadio is off, true if it is on
     */
    private boolean isTurnedOn;
    /**
     * Determines the actual frequency of the Radio, false if it is on AM, true if it is on FM
     */
    private boolean isOnFM;
    /**
     * Radios's current Station, represented as a double
     */
    private double currentStation;
    /**
     * Array to store 12 possible saved stations given by the user
     */
    private double[] stations;


    /**
     * This is a constructor to initialize MyRadio object, it does not receive parameters
     */
    public MyRadio() {
        this.isTurnedOn = false;
        this.isOnFM = false;
        this.stations = new double[12];
        this.currentStation = LOWER_LIMIT_AM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggle() {
        this.isTurnedOn = !isTurnedOn;
    }

    /**
     * {@inheritDoc}
     * @return the state of the radio, true for ON state, false for OFF state
     */

    @Override
    public boolean getState() {
        return this.isTurnedOn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeFrequency() {
        if (this.isOnFM){
            currentStation = LOWER_LIMIT_AM;
        } else {
            currentStation = LOWER_LIMIT_FM;
        }
        this.isOnFM = !isOnFM;
    }

    /**
     * {@inheritDoc}
     * @param up true to go to the next station, false to go to the previous station
     */
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

    /**
     * {@inheritDoc}
     * @return a boolean to represent if radio is on AM (false) / FM (true)
     */
    @Override
    public boolean getFrequency() {
        return this.isOnFM;
    }

    /**
     * {@inheritDoc}
     * @param numButton the position of the array where the station will be saved
     */
    @Override
    public void saveStation(int numButton) {
        this.stations[numButton-1] = currentStation;

    }

    /**
     * {@inheritDoc}
     * @param numButton the position of the array of the station to set as current station
     */
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
    /**
     * {@inheritDoc}
     * @return the radios's current station, represented as a double
     */
    @Override
    public double getStation() {
        return this.currentStation;
    }
}