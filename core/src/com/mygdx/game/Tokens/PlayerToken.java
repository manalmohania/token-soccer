package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by manalmohania on 13/6/17.
 */
public class PlayerToken extends Token {
    /*
    * tokenId is used for the bots and for communicating moves over the internet
    *
    * Assume a max of ten tokens per team
    * A body id is formed as follows "" + <team number> (1 to 2) + <body number> (0 to 9)
    * */
    private String tokenId;

    public PlayerToken(World world, Vector2 initialPos, String tokenId) {
        // 5 is the radius of the player -- will probably increase its size in the end
        this(world, initialPos, tokenId, 8.0f);
    }

    public PlayerToken(World world, Vector2 initialPos, String tokenId, float radius) {
        super(world, initialPos, radius);
        this.tokenId = tokenId;
    }

    @Override
    public void draw(SpriteBatch batch, Texture texture) {
        super.draw(batch, background);
        super.draw(batch, texture);
    }

    public String getTokenId() {
        return tokenId;
    }

}
