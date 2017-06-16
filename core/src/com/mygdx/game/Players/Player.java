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

    /**
     * the mathod makes a move depending on certain parameters
     *
     * @param id : The id of the token
     *           float an
     * */
    public void makeMove(String id, float lastX, float lastY, float releaseX, float releaseY) {
        // FIXME: Add comments please :)
        float len = (float) Math.min(Math.sqrt((lastX - releaseX) * (lastX - releaseX) + (lastY - releaseY) * (lastY - releaseY)), 20);
        float angle = (float) Math.atan((releaseY - lastY)/(releaseX - lastX));

        PlayerToken lastToken = null;
        for (PlayerToken token : tokens) {
            if (token.getTokenID().equals(id)) {
                lastToken = token;
                break;
            }
        }

        // This should not happen
        if (lastToken == null)
            throw new NullPointerException("lastToken should not be null");

        if (releaseX >= lastX)
            lastToken.getBody().applyLinearImpulse((float) (-1000 * len * Math.cos(angle)), (float) (-1000 * len * Math.sin(angle)), lastX, lastY, false);
        else
            lastToken.getBody().applyLinearImpulse((float) (1000 * len * Math.cos(angle)), (float) (1000 * len * Math.sin(angle)), lastX, lastY, false);
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
