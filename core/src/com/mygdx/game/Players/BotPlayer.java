package com.mygdx.game.Players;

import com.mygdx.game.Tokens.PlayerToken;

import java.util.List;

public abstract class BotPlayer extends Player {
    public BotPlayer(String name, List<PlayerToken> tokens) {
        super(name, tokens);
    }

    @Override
    public boolean isBot() {
        return true;
    }

    public abstract void makeMove();
}
