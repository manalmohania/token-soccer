package com.mygdx.game.Tokens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by manalmohania on 13/6/17.
 */
public class PlayerToken extends Token{
    public PlayerToken(World world, Vector2 initialPos){
        // 5 is the radius of the player -- will probably increase its size in the end
        super(world, initialPos, 5);
    }
}
