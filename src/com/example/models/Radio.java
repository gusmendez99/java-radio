package com.example.models;

public interface Radio {
    /**
     * Changes the actual state of the radio between ON / OFF
     */
    void toggle();

    /**
     * obtains the actual state of the radio (ON/OFF)
     * @return true for ON state, false for OFF state
     */
    boolean getState();

    /**
     * Changes the frequency of the radio between AM/FM
     */
    void changeFrequency();

    /**
     * changes tha station of the radio, represented by a double
     * @param up true to go to the next station, false to go to the previous station
     */
    void changeStation(boolean up);

    /**
     * gets the actual frequency of the radio
     * @return a boolean to represent if radio is on AM/FM
     */
    boolean getFrequency();

    /**
     * Saves the current station in the stations array
     * @param numButton the position of the array where the station will be saved
     */
    void saveStation(int numButton);

    /**
     * sets the current station as the value of an specific saved station in the stations array
     * @param numButton the position of the array of the station to set as current station
     */
    void changeStationButton(int numButton);

    /**
     * get the current station of the radio
     * @return the radios's current station, represented as a double
     */
    double getStation();
}
