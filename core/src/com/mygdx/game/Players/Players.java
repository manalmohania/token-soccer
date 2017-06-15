package com.mygdx.game.Players;

import com.mygdx.game.Timer;

/**
 * Created by manalmohania on 13/6/17.
 */
public class Players {
    private Player player1;
    private Player player2;
    private boolean isplayer1turn;
    private Timer timer;

    public Players(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        isplayer1turn = true;
        this.timer = new Timer();
    }

    public Player currentPlayer(){
        if (isplayer1turn) return player1;
        else return player2;
    }

    public void toggleTurns(){
        if (isplayer1turn) {
            player1.isTurn = false;
            player2.isTurn = true;
        }
        else {
            player1.isTurn = true;
            player1.isTurn = false;
        }
        isplayer1turn = !isplayer1turn;
        this.timer.reset();
    }

    public Timer getTimer() {
        return timer;
    }

    public void makeMove(String id, float angle, float length, float lastX, float lastY, float releaseX) {
        currentPlayer().makeMove(id, angle, length, lastX, lastY, releaseX);
        toggleTurns();
        if (currentPlayer() instanceof BotPlayer) {
            ((BotPlayer) currentPlayer()).makeMove();
            toggleTurns();
        }
    }

}
