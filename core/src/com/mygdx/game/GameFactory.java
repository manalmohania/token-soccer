package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Players.HumanPlayer;
import com.mygdx.game.Players.RandomBot;
import com.mygdx.game.Tokens.BallToken;
import com.mygdx.game.Tokens.PlayerToken;

import java.util.ArrayList;

class GameFactory {

    private static ArrayList<PlayerToken> p1Tokens, p2Tokens;

    static Game createGame(int formation, String name1, String name2, boolean twoPlayer, World world){
        positionTokens(formation, world);
        return createPlayers(name1, name2, twoPlayer, world);
    }

    private static void positionTokens(int formation, World world) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        p1Tokens = new ArrayList<PlayerToken>();
        p2Tokens = new ArrayList<PlayerToken>();

        switch (formation) {
            case 0: p1Tokens.add(new PlayerToken(new Vector2(width / 4 - 50, height / 4 - 50), "10", world));
                    p1Tokens.add(new PlayerToken(new Vector2(width / 4 - 50, height / 4 + 50), "11", world));
                    p2Tokens.add(new PlayerToken(new Vector2(width / 4 + 50, height / 4 - 50), "20", world));
                    p2Tokens.add(new PlayerToken(new Vector2(width / 4 + 50, height / 4 + 50), "21", world));
                    break;
            default: throw new IllegalArgumentException("Formation not recognised");
        }
    }

    private static Game createPlayers(String name1, String name2, boolean twoPlayer, World world) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        if (twoPlayer) {
            return new Game(
                    new HumanPlayer(name1, p1Tokens),
                    new HumanPlayer(name2, p2Tokens),
                    new BallToken(new Vector2(width / 4, height / 4), world));
        }
        else {
            return new Game(
                    new HumanPlayer(name1, p1Tokens),
                    new RandomBot(name2, p2Tokens),
                    new BallToken(new Vector2(width / 4, height / 4), world));
        }
    }
}
