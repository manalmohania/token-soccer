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
    private boolean isRunning;


    public boolean isRunning() {
        return isRunning;
    }

    public Timer() {
        this.isRunning = true;
        this.original_time = System.nanoTime();
        this.df = new DecimalFormat("#.00");
    }

    public void start() {
        if (!isRunning) {
            this.original_time = System.nanoTime();
            this.isRunning = true;
        }
    }
    public void reset() {
        this.isRunning = false;
    }

    private double getTimeElapsed() {
        if (isRunning) {
            return (System.nanoTime() - original_time) * secToNanosec;
        } else {
            return 0;
        }
    }

    public String getTimeRemaining() {
        return df.format(MAX_TURN_TIME - getTimeElapsed());
    }

    public boolean timeRemaining() {
        return this.getTimeElapsed() < MAX_TURN_TIME;
    }
}
