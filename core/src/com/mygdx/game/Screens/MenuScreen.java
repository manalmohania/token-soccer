package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.TokenSoccer;

/**
 * Created by allen on 24/06/2017.
 */
public class MenuScreen implements Screen {
    final Launcher game;
    private OrthographicCamera camera;
    Stage stage;
    Skin skin;
    float width, height;

    public MenuScreen (final Launcher game) {
        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width / 2, height / 2);

        createSkin();
        TextButton startGameButton = new TextButton("Start game", skin);
        startGameButton.setPosition(width/2, height/2);
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("button pressed");
                game.setScreen(new TokenSoccer(game));
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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
