package com.mygdx.game;

import java.text.DecimalFormat;

/**
 * Created by allen on 14/06/17.
 */
public class Timer {
    private static final int TIME_LIMIT = 10; // in seconds
    private double initialTime;
    private DecimalFormat df;

    public Timer() {
        initialTime = System.nanoTime();
        this.df = new DecimalFormat("#.00");
    }

    public void reset() {
        initialTime = System.nanoTime();
    }

    public double getTimeElapsed() {
        return getSeconds(System.nanoTime() - initialTime);
    }

    public String getTimeRemaining() {
        return df.format(TIME_LIMIT - getSeconds(System.nanoTime() - initialTime));
    }

    private double getSeconds(double nanoseconds) {
        return nanoseconds / 1E9;
    }

    public boolean expired() {
        return this.getTimeElapsed() < TIME_LIMIT;
    }
}
