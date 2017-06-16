package com.mygdx.game.Tokens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class BallToken extends Token {
    public BallToken(World world, Vector2 initialPos) {
        this(world, initialPos, 3.5f);
    }

    public BallToken(World world, Vector2 initialPos, float radius) {
        super(world, initialPos, radius);
    }
}
