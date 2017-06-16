package com.mygdx.game.Players;

import com.mygdx.game.Tokens.PlayerToken;

import java.util.ArrayList;

/**
 * Created by manalmohania on 15/6/17.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name, ArrayList<PlayerToken> tokens) {
        super(name, tokens);
    }

    @Override
    public boolean isBot() {
        return true;
    }
}
