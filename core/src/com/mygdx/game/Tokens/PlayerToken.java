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

    public PlayerToken(World world, Vector2 initialPos, String tokenID) {
        this(world, initialPos, tokenID, 8.0f);
    }

    public PlayerToken(World world, Vector2 initialPos, String tokenID, float radius) {
        super(world, initialPos, radius);
        this.tokenID = tokenID;
    }

    @Override
    public void draw(SpriteBatch batch, Texture texture) {
        super.draw(batch, background);
        super.draw(batch, texture);
    }

    public String getTokenID() {
        return tokenID;
    }

}
