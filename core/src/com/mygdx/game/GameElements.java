package com.mygdx.game;

import com.mygdx.game.Players.BotPlayer;
import com.mygdx.game.Players.Players;
import com.mygdx.game.Tokens.BallToken;

/**
 * Created by manalmohania on 15/6/17.
 */
public class GameElements implements Runnable{

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

    public void makeMove(String id, float angle, float length, float lastX, float lastY, float releaseX) {
        players.currentPlayer().makeMove(id, angle, length, lastX, lastY, releaseX);
        Thread thread = new Thread(this, "myThread");
        System.out.println("Checkpoint 1");
        // thread.start();
        toggleTurns();
        // System.out.println(Thread.currentThread().getName());
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Reached here");
        if (players.currentPlayer() instanceof BotPlayer) {
            players.currentPlayer().makeMove();
//            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            toggleTurns();
        }
    }

    private boolean atRest() {
        if (! ballToken.token.getLinearVelocity().epsilonEquals(0, 0, 1f)) return false;
        for (int i = 0; i < players.player1.getTokens().size(); i++) {
            if (! players.player1.getTokens().get(i).token.getLinearVelocity().epsilonEquals(0, 0, 1f)) return false;
            if (! players.player2.getTokens().get(i).token.getLinearVelocity().epsilonEquals(0, 0, 1f)) return false;
        }
        return true;
    }

    public void toggleTurns() {
//        while (!atRest()) {
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        players.toggleTurns();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("Checkpoint 2");
        while (!atRest()) {
            System.out.println("Inside loop");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Checkpoint 3");
        players.toggleTurns();
        System.out.println("Checkpoint 4");
    }
}
