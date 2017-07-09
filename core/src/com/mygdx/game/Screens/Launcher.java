package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by allen on 24/06/2017.
 */
public class Launcher extends com.badlogic.gdx.Game {

    SpriteBatch batch;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
