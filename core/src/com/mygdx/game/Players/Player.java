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
     * the method makes a move depending on certain parameters
     *
     * @param id : The id of the token
     * @param lastX : The x-coordinate on which the mouse was clicked
     * @param lastY : The y-coordinate on which the mouse was clicked
     * @param releaseX : The x-coordinate on which the mouse was released
     * @param releaseY : The y-coordinate on which the mouse was released
     * */
    public void makeMove(String id, float lastX, float lastY, float releaseX, float releaseY) {
        // calculate the length and angle between the click and release coordinates
        float len = (float) Math.min(Math.sqrt((lastX - releaseX) * (lastX - releaseX) + (lastY - releaseY) * (lastY - releaseY)), 50);
        float angle = (float) Math.atan((releaseY - lastY)/(releaseX - lastX));

        PlayerToken lastToken = null;
        for (PlayerToken token : tokens) {
            if (token.getTokenID().equals(id)) {
                lastToken = token;
                break;
            }
        }

        float lengthToImpulse = 1000; // increase this number to increase shot strength

        // This should not happen
        if (lastToken == null)
            throw new NullPointerException("lastToken should not be null");

        if (releaseX >= lastX)
            lastToken.getBody().applyLinearImpulse((float) (-lengthToImpulse * len * Math.cos(angle)), (float) (-lengthToImpulse * len * Math.sin(angle)), lastX, lastY, false);
        else
            lastToken.getBody().applyLinearImpulse((float) (lengthToImpulse * len * Math.cos(angle)), (float) (lengthToImpulse * len * Math.sin(angle)), lastX, lastY, false);
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
