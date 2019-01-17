package com.example.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyRadioTest {

    @Test
    public void getState() {
        MyRadio radio = new MyRadio();
        radio.toggle();
        assertEquals(true, radio.getState());
    }

    @Test
    public void getFrequency() {
        MyRadio radio = new MyRadio();
        radio.changeFrequency();
        assertEquals(true, radio.getFrequency());
    }

    @Test
    public void getStation() {
        MyRadio radio = new MyRadio();
        radio.changeFrequency();
        assertEquals(MyRadio.LOWER_LIMIT_FM, radio.getStation()); //TODO: Replace this method
    }
}