package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Tokens.PlayerToken;

/**
 * Created by manalmohania on 13/6/17.
 */
public class Events implements InputProcessor {

    private GameElements gameElements;
    private float lastX, lastY;
    private PlayerToken lastToken;

    public Events(GameElements gameElements){
        this.gameElements = gameElements;
    }

    private PlayerToken contains(float x, float y) {
        for (PlayerToken body : gameElements.getPlayers().currentPlayer().getTokens()) {
            float xPos = body.token.getPosition().x;
            float yPos = body.token.getPosition().y;
            // detect if the touch was inside a token
            if ((x - xPos) * (x - xPos) + (y - yPos) * (y - yPos) <= body.radius * body.radius) {
                return body;
            }
        }
        return null;
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
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();
        // the next line is a bit of a hack and might not always work
        PlayerToken body = contains(x/2, y/2);
        lastX = x/2;
        lastY = y/2;
        if (body != null) {
            lastToken = body;
        }
        else {
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
        if (lastToken == null) return true;
        float releaseX = Gdx.input.getX()/2;
        float releaseY = (Gdx.graphics.getHeight() - Gdx.input.getY())/2;

        float len = Math.max(20, (float) Math.sqrt((releaseX - lastX) * (releaseX - lastX) + (releaseY - lastY) * (releaseY - lastY)));
        float slope = (releaseY - lastY)/(releaseX - lastX);
        if (Float.isNaN(slope)) {
            return true;
        }
        float angle = (float) Math.atan(slope);
        gameElements.getPlayers().makeMove(lastToken.getTokenId(), angle, len, lastX, lastY, releaseX);

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
}
