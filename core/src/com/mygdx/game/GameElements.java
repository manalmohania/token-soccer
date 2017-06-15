package com.mygdx.game;

import com.mygdx.game.Players.Players;
import com.mygdx.game.Tokens.BallToken;

/**
 * Created by manalmohania on 15/6/17.
 */
public class GameElements {

    private Players players;
    private BallToken ballToken;

    GameElements(Players players, BallToken ballToken) {
        this.players = players;
        this.ballToken = ballToken;
    }

    public Players getPlayers() {
        return players;
    }

    public BallToken getBallToken() {
        return ballToken;
    }
}
