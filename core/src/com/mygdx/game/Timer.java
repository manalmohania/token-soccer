package com.mygdx.game;

import java.text.DecimalFormat;

class Timer {
    private static final int TIME_LIMIT = 50;
    private double initialTime;
    private DecimalFormat df;
    private boolean isRunning;

    Timer() {
        this.initialTime = System.nanoTime();
        this.df = new DecimalFormat("#.00");
        this.isRunning = true;
    }

    void start() {
        if (!isRunning) {
            this.initialTime = System.nanoTime();
            this.isRunning = true;
        }
    }

    void reset() {
        this.isRunning = false;
    }

    private double getTimeElapsed() {
        return isRunning ? getSeconds(System.nanoTime() - initialTime) : 0;
    }

    String getTimeRemaining() {
        return df.format(TIME_LIMIT - getTimeElapsed());
    }

    private double getSeconds(double nanoseconds) {
        return nanoseconds / 1E9;
    }

    boolean expired() {
        return this.getTimeElapsed() < TIME_LIMIT;
    }
}
