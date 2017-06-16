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
        float angle = (float) (random.nextFloat() * Math.PI - (Math.PI / 2));
        float len = random.nextFloat() * 20;
        float lastX = token.body.getPosition().x;
        float lastY = token.body.getPosition().y;
        float releaseX = random.nextFloat() * Gdx.graphics.getWidth();
        // Make the move
        super.makeMove(token.getTokenId(), angle, len, lastX, lastY, releaseX);
    }
}
