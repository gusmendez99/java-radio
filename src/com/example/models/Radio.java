package com.example.models;

public interface Radio {
    void toggle();
    boolean getState();
    void changeFrequency();
    void changeStation(boolean up);
    boolean getFrequency();
    void saveStation(int numButton);
    void changeStationButton(int numButton);
    double getStation();
}
