package com.mygdx.game;

/**
 * Created by manalmohania on 13/6/17.
 */
public class Players {
    Player player1;
    Player player2;
    boolean isplayer1turn;
    private Timer timer;

    public Players(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        isplayer1turn = true;
        this.timer = new Timer();
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
}
