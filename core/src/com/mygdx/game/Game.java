package com.mygdx.game;

import com.mygdx.game.Players.Player;
import com.mygdx.game.Players.RandomBot;
import com.mygdx.game.Tokens.BallToken;
import com.mygdx.game.Tokens.PlayerToken;

public class Game {
    private Player player1;
    private Player player2;
    private BallToken ballToken;
    private boolean isP1Turn;
    private Timer timer;

    Game(Player player1, Player player2, BallToken ballToken) {
        this.player1 = player1;
        this.player2 = player2;
        this.ballToken = ballToken;
        this.isP1Turn = true;
        this.timer = new Timer();
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public BallToken getBallToken() {
        return ballToken;
    }

    public Timer getTimer() {
        return timer;
    }

    public Player currentPlayer() {
        return isP1Turn ? player1 : player2;
    }

    public void toggleTurns() {
        isP1Turn = !isP1Turn;
        timer.reset();
    }

    public boolean atRest() {
        // TODO make system rest faster
        // Check that the ball is stationary
        if (!ballToken.getBody().getLinearVelocity().epsilonEquals(0, 0, 1f)) return false;
        // Check that each of the players tokens are stationary
        for (PlayerToken token : player1.getTokens())
            if (!token.atRest()) return false;
        for (PlayerToken token : player2.getTokens())
            if (!token.atRest()) return false;

        makeStationary();
        return true;
    }

    private void makeStationary() {
        ballToken.getBody().setLinearVelocity(0, 0);
        for (PlayerToken token : player1.getTokens()) {
            token.getBody().setLinearVelocity(0, 0);
        }
        for (PlayerToken token : player2.getTokens()) {
            token.getBody().setLinearVelocity(0, 0);
        }
    }

    public void makeMove(String tokenId, float lastX, float lastY, float releaseX, float releaseY) {
        currentPlayer().makeMove(tokenId, lastX, lastY, releaseX, releaseY);
        toggleTurns();
    }

    public void makeBotMove() {
        RandomBot randomBot = (RandomBot) currentPlayer();
        randomBot.makeMove();
        toggleTurns();
    }
}
