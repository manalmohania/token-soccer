package com.mygdx.game;

/**
 * Created by allen on 13/06/17.
 */
public class Player {
    private final String name;
    private int score;
    private final Boolean isBot;

    Player(String name, Boolean isBot) {
        this.name = name;
        this.score = 0;
        this.isBot = isBot;
    }

    public int getScore() {return score;}
    public String getName() {return name;}

    public void scoreGoal() {
        this.score ++;
    }

    public void resetGoal() {
        this.score = 0;
    }
}
