package com.mygdx.game.Players;

import com.mygdx.game.Tokens.PlayerToken;

import java.util.ArrayList;

/**
 * Created by allen on 13/06/17.
 */
public abstract class Player {
    private final String name;
    private int score;
    ArrayList<PlayerToken> tokens;

    Player(String name, ArrayList<PlayerToken> tokens) {
        this.name = name;
        this.score = 0;
        this.tokens = tokens;
    }

    public void makeMove(String id, float angle, float len, float lastX, float lastY, float releaseX) {

        len = Math.min(len, 20);

        PlayerToken lastToken = null;

        for (PlayerToken token : tokens) {
            if (token.getTokenId().equals(id)) {
                lastToken = token;
                break;
            }
        }

        // this should never happen, but I'm keeping it here for the time being
        if (lastToken == null)
            throw new NullPointerException("lastToken should not be null");

        if (releaseX >= lastX)
            lastToken.token.applyLinearImpulse((float) (-1000 * len * Math.cos(angle)), (float) (-1000 * len * Math.sin(angle)), lastX, lastY, false);
        else
            lastToken.token.applyLinearImpulse((float) (1000 * len * Math.cos(angle)), (float) (1000 * len * Math.sin(angle)), lastX, lastY, false);

    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void scoreGoal() {
        this.score++;
    }

    public void resetGoal() {
        this.score = 0;
    }

    public ArrayList<PlayerToken> getTokens() {
        return tokens;
    }

    public abstract boolean isBot();

}
