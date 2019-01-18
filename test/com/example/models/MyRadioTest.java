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
    public void toggle() {
        MyRadio radio = new MyRadio();
        boolean initState = radio.getState();
        radio.toggle();
        initState = !initState;
        assertEquals(initState, radio.getState());
    }

}