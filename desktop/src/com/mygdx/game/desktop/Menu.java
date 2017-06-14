package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.*;

/**
 * Created by allen on 14/06/17.
 */
public class Menu extends ApplicationAdapter {

    Stage stage;
    Skin skin;
    float width;
    float height;
    Game game;

    Menu(Game game) {
        this.game = game;
    }

    @Override
    public void create() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        createSkin();
        TextButton startGameButton = new TextButton("Start game", skin);
        startGameButton.setPosition(width/2, height/2);
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("button pressed");
                //TODO Link up menu to game
                //game.setScreen();
            }
        });
        stage.addActor(startGameButton);

        BitmapFont font = new BitmapFont();
        Batch batch = new SpriteBatch();
        batch.begin();
        font.draw(batch, "Token Soccer", width/2, height);
        batch.end();
    }

    private void createSkin() {
        // Texture
        this.skin = new Skin();
        Pixmap pixmap = new Pixmap((int)width/4, (int)width/4, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        // Font
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        // Button Style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    @Override
    public void render() {
        // For white background
        //Gdx.gl.glClearColor(1,1,1,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }
}

