package com.mygdx.game;

import java.text.DecimalFormat;

public class Timer {
    private static final int TIME_LIMIT = 50;
    private double initialTime;
    private DecimalFormat df;
    private boolean isRunning;

    public Timer() {
        this.initialTime = System.nanoTime();
        this.df = new DecimalFormat("#.00");
        this.isRunning = true;
    }

    public void start() {
        if (!isRunning) {
            this.initialTime = System.nanoTime();
            this.isRunning = true;
        }
    }

    public void reset() {
        this.isRunning = false;
    }

    private double getTimeElapsed() {
        return isRunning ? getSeconds(System.nanoTime() - initialTime) : 0;
    }

    public String getTimeRemaining() {
        return df.format(TIME_LIMIT - getTimeElapsed());
    }

    private double getSeconds(double nanoseconds) {
        return nanoseconds / 1E9;
    }

    public boolean expired() {
        return this.getTimeElapsed() < TIME_LIMIT;
    }
}
