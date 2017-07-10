package com.mygdx.game;

import com.mygdx.game.Players.Player;
import com.mygdx.game.Players.RandomBot;
import com.mygdx.game.Tokens.BallToken;
import com.mygdx.game.Tokens.PlayerToken;

class Game {
    private Player player1;
    private Player player2;
    private BallToken ballToken;
    private boolean isP1Turn;
    private Timer timer;
    private Player winner;

    Game(Player player1, Player player2, BallToken ballToken) {
        this.player1 = player1;
        this.player2 = player2;
        this.ballToken = ballToken;
        this.isP1Turn = true;
        this.timer = new Timer();
        winner = null;
    }

    Player getPlayer2() {
        return player2;
    }

    Player getPlayer1() {
        return player1;
    }

    BallToken getBallToken() {
        return ballToken;
    }

    Timer getTimer() {
        return timer;
    }

    Player currentPlayer() {
        return isP1Turn ? player1 : player2;
    }

    void toggleTurns() {
        isP1Turn = !isP1Turn;
        timer.reset();
    }

    Player getWinner(){
        return winner;
    }

    void setWinner(Player player){
        winner = player;
    }

    boolean atRest() {
        // TODO make system rest faster
        // Check that the ball is stationary
        if (!ballToken.atRest()) return false;
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

    void makeMove(String tokenId, float lastX, float lastY, float releaseX, float releaseY) {
        currentPlayer().makeMove(tokenId, lastX, lastY, releaseX, releaseY);
        toggleTurns();
    }

    void makeBotMove() {
        RandomBot randomBot = (RandomBot) currentPlayer();
        randomBot.makeMove();
        toggleTurns();
    }
}
