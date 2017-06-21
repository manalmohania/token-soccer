package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Players.BotPlayer;
import com.mygdx.game.Tokens.PlayerToken;

/**
 * Created by manalmohania on 13/6/17.
 */
public class Events implements InputProcessor {
    private Game game;
    private float lastX, lastY;
    private PlayerToken lastToken;

    public Events(Game game) {
        this.game = game;
        if (game.currentPlayer() instanceof BotPlayer) {
            game.makeBotMove();
        }
    }

    private PlayerToken contains(float x, float y) {
        for (PlayerToken token : game.currentPlayer().getTokens()) {
            float xPos = token.getX();
            float yPos = token.getY();
            // detect if the touch was inside a body
            if ((x - xPos) * (x - xPos) + (y - yPos) * (y - yPos) <= token.getRadius() * token.getRadius()) {
                return token;
            }
        }
        return null;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();
        // the next line is a bit of a hack and might not always work
        PlayerToken body = contains(x / 2, y / 2);
        lastX = x / 2;
        lastY = y / 2;
        if (body != null) {
            lastToken = body;
        } else {
            lastToken = null;
        }
        return true;
    }

    /**
     * Called when a finger was lifted or a mouse button was released.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button   @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!game.atRest()) {
            return false;
        }

        if (lastToken == null) return false;
        float releaseX = Gdx.input.getX() / 2;
        float releaseY = (Gdx.graphics.getHeight() - Gdx.input.getY()) / 2;
        if (releaseX - lastX == 0 || ((releaseX - lastX) * (releaseX - lastX) + (releaseY - lastY) * (releaseY - lastY)) < 1) {
            System.out.println("returning from here");
            return false;
        }
        game.makeMove(lastToken.getTokenID(), lastX, lastY, releaseX, releaseY);

        return true;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
}
