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

    public boolean atRest() {
        // TODO make system rest faster
        if (! getBallToken().token.getLinearVelocity().epsilonEquals(0, 0, 1f)) return false;
        for (int i = 0; i < getPlayers().player1.getTokens().size(); i++) {
            if (! getPlayers().player1.getTokens().get(i).token.getLinearVelocity().epsilonEquals(0, 0, 1f)) return false;
            if (! getPlayers().player2.getTokens().get(i).token.getLinearVelocity().epsilonEquals(0, 0, 1f)) return false;
        }
        return true;
    }
}
