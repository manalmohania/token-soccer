package com.mygdx.game;

import java.text.DecimalFormat;

/**
 * Created by allen on 14/06/17.
 */
public class Timer {
    private final int MAX_TURN_TIME = 50;
    private final double secToNanosec = 1/1000000000.0;
    private double original_time;
    private DecimalFormat df;


    public Timer() {
        original_time = System.nanoTime();
        this.df = new DecimalFormat("#.00");
    }

    public void reset() {
        original_time = System.nanoTime();
    }

    public double getTimeElapsed() {
        return (System.nanoTime() - original_time) * secToNanosec;
    }

    public String getTimeRemaining() {
        return df.format(MAX_TURN_TIME - (System.nanoTime() - original_time)*secToNanosec);
    }

    public boolean timeRemaining() {
        return this.getTimeElapsed() < MAX_TURN_TIME;
    }
}
