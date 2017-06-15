package com.mygdx.game.Players;

import com.mygdx.game.Tokens.PlayerToken;

import java.util.ArrayList;

/**
 * Created by manalmohania on 15/6/17.
 */
public abstract class BotPlayer extends Player {

    BotPlayer(String name, ArrayList<PlayerToken> tokens) {
        super(name, tokens);
    }

    @Override
    public void makeMove(String id, float angle, float len, float lastX, float lastY, float releaseX) {
        try {
            throw new NoSuchMethodException("makeMove(String, float, float, float, flat, float) should not be called");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public abstract void makeMove();
}
