package com.mygdx.game.Players;

import com.mygdx.game.Tokens.PlayerToken;

import java.util.List;

public abstract class Player {
    private final String name;
    private int score;
    List<PlayerToken> tokens;

    Player(String name, List<PlayerToken> tokens) {
        this.name = name;
        this.score = 0;
        this.tokens = tokens;
    }

    public void makeMove(String id, float angle, float len, float lastX, float lastY, float releaseX) {
        // FIXME: Add comments please :)
        len = Math.min(len, 20);

        PlayerToken lastToken = null;
        for (PlayerToken token : tokens) {
            if (token.getTokenId().equals(id)) {
                lastToken = token;
                break;
            }
        }

        // This should not happen
        if (lastToken == null)
            throw new NullPointerException("lastToken should not be null");

        if (releaseX >= lastX)
            lastToken.body.applyLinearImpulse((float) (-1000 * len * Math.cos(angle)), (float) (-1000 * len * Math.sin(angle)), lastX, lastY, false);
        else
            lastToken.body.applyLinearImpulse((float) (1000 * len * Math.cos(angle)), (float) (1000 * len * Math.sin(angle)), lastX, lastY, false);
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

    public void resetGoals() {
        this.score = 0;
    }

    public List<PlayerToken> getTokens() {
        return tokens;
    }

    public abstract boolean isBot();
}
