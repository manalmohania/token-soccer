package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerToken extends Token {
    /**
     * tokenID is used for the bots and for communicating moves over the internet
     *
     * Assume a max of ten tokens per team
     * A body ID is formed as follows "" + <team number> (1 to 2) + <body number> (0 to 9)
     */
    private String tokenID;
    private static Texture texture = new Texture("images/tokens/my-yellow-ring-64.png");

    public PlayerToken(Vector2 initialPos, String tokenID, World world) {
        this(initialPos, tokenID, 8.0f, world);
    }

    public PlayerToken(Vector2 initialPos, String tokenID, float radius, World world) {
        super(initialPos, radius, world);
        this.tokenID = tokenID;
    }

    @Override
    public void draw(SpriteBatch batch, Texture texture) {
        super.draw(batch, background);
        super.draw(batch, texture);
    }

    public void drawRing(SpriteBatch batch) {
        batch.draw(texture, 2 * getX() - texture.getWidth() / 2, 2 * getY() - texture.getHeight() / 2);
    }

    public String getTokenID() {
        return tokenID;
    }

    public static void dispose() {
        texture.dispose();
    }

}
