package com.mygdx.game.Players;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Tokens.PlayerToken;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by manalmohania on 15/6/17.
 */
public class RandomBot extends BotPlayer {
    private Random random;

    public RandomBot(String name, ArrayList<PlayerToken> tokens) {
        super(name, tokens);
        random = new Random();
    }

    @Override
    public void makeMove() {
        int i = random.nextInt(tokens.size());
        String id = tokens.get(i).getTokenId();
        float angle = (float) (random.nextFloat() * Math.PI - (Math.PI / 2));
        float len = random.nextFloat() * 20;
        float lastX = tokens.get(i).token.getPosition().x;
        float lastY = tokens.get(i).token.getPosition().y;
        float releaseX = random.nextFloat() * Gdx.graphics.getWidth();

        super.makeMove(id, angle, len, lastX, lastY, releaseX);

    }
}
