package com.mygdx.game.Players;

import com.mygdx.game.Tokens.PlayerToken;

import java.util.List;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, List<PlayerToken> tokens) {
        super(name, tokens);
    }

    @Override
    public boolean isBot() {
        return false;
    }
}
