package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tokens.PlayerToken;
import com.mygdx.game.Tokens.Token;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by allen on 13/06/17.
 */
public class Player {
    private final String name;
    private int score;
    private final Boolean isBot;
    private ArrayList<PlayerToken> tokens;
    public boolean isTurn;

    Player(String name, Boolean isBot, ArrayList<PlayerToken> tokens) {
        this.name = name;
        this.score = 0;
        this.isBot = isBot;
        this.tokens = tokens;
    }

    public void makeMove() {
        if (isBot && isTurn) {
            Exception up = new NotImplementedException();
            try {
                throw up; // <--- this
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void scoreGoal() {
        this.score ++;
    }

    public void resetGoal() {
        this.score = 0;
    }

    public ArrayList<PlayerToken> getTokens(){
        return tokens;
    }

}
