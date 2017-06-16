package com.mygdx.game.Tokens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class BallToken extends Token {
    public BallToken(Vector2 initialPos, World world) {
        this(initialPos, 3.5f, world);
    }

    public BallToken(Vector2 initialPos, float radius, World world) {
        super(initialPos, radius, world);
    }
}
