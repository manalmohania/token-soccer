package com.mygdx.game.Players;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Tokens.PlayerToken;

import java.util.List;
import java.util.Random;

public class RandomBot extends BotPlayer {
    public RandomBot(String name, List<PlayerToken> tokens) {
        super(name, tokens);
    }

    @Override
    public void makeMove() {
        // Generate random values
        Random random = new Random();
        PlayerToken token = this.tokens.get(random.nextInt(tokens.size()));
        float lastX = token.getX();
        float lastY = token.getY();
        float releaseX = random.nextFloat() * Gdx.graphics.getWidth();
        float releaseY = random.nextFloat() * Gdx.graphics.getHeight();
        // Make the move
        super.makeMove(token.getTokenID(), lastX, lastY, releaseX, releaseY);
    }
}
