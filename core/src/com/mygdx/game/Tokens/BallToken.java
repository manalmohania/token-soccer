package com.mygdx.game.Tokens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by manalmohania on 13/6/17.
 */
public class BallToken extends Token{

    public BallToken(World world, Vector2 initialPos){
        // 3 is the radius of the ball -- will probably increase its size in the end
        super(world, initialPos, 3);
    }
}
