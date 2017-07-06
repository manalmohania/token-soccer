package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.game.TokenSoccer;

/**
 * Created by allen on 24/06/2017.
 */
public class MenuScreen implements Screen {
    private final Launcher game;
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private float width, height;
    private Batch batch;
    private BitmapFont font;
    private Texture logo, man;

    public MenuScreen (final Launcher game) {
        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width / 2, height / 2);

        this.skin = createSkin();
        TextButton singlePlayer = new TextButton("Single Player", skin);
        singlePlayer.setPosition(0, height/3);
        singlePlayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TokenSoccer(game, false));
            }
        });
        TextButton multiPlayer = new TextButton("Two Players", skin);
        multiPlayer.setPosition(0, height/5);
        multiPlayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TokenSoccer(game, true));
            }
        });
        stage.addActor(singlePlayer);
        stage.addActor(multiPlayer);

        font = new BitmapFont();
        batch = new SpriteBatch();
        logo = new Texture("images/screen/logo.png");
        man = new Texture("images/screen/man.png");
    }

    private Skin createSkin() {
        // Texture
        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap((int)width/4, (int)width/4, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        // Font
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        NinePatch ninePatch = new NinePatch(new Texture("images/screen/button-background.png"));
        ninePatch.setColor(Color.GREEN);

        // Button Style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = Color.ORANGE;
        textButtonStyle.up = new NinePatchDrawable(ninePatch);
        skin.add("default", textButtonStyle);

        pixmap.dispose();
        return skin;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.20f, 0.59f, 0.02f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // font.draw(batch, "Token Soccer", width/2, height);
        batch.draw(logo, width / 2 - logo.getWidth() / 2, 5 * height / 6 - logo.getHeight() / 2);
        batch.draw(man, 2 * width / 3 - man.getWidth() / 2, height / 3 - man.getHeight() / 2);
        batch.end();

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
    public void dispose() {
        skin.dispose();
        stage.dispose();
        game.dispose();
    }
}
