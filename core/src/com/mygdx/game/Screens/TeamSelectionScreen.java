package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Team;
import com.mygdx.game.TokenSoccer;

class TeamSelectionScreen implements Screen{

    private final Launcher game;
    private Stage stage;
    private Texture logo, spain, germany, italy, england, outline;
    private Batch batch;
    private OrthographicCamera camera;
    private Skin skin;
    private final int width = Gdx.graphics.getWidth();
    private final int height = Gdx.graphics.getHeight();
    private String code;
    private BitmapFont font;
    private ImageButton selectedButton;
    private char selectedTeam;
    private UIElements UIElements = new UIElements();

    /*
    * code -> "1 or 2" + "1 or 2" + "X or S or I or E or G" --> number of players + current player number
    * */

    TeamSelectionScreen(final Launcher game, final String code) {
        this.code = code;
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        String pathToScreen = "images/screen/";

        logo = new Texture(pathToScreen + "logo.png");
        spain = new Texture(pathToScreen + "flags/spanish-flag-small.png");
        germany = new Texture(pathToScreen + "flags/german-flag-small.png");
        italy = new Texture(pathToScreen + "flags/italian-flag-small.png");
        england = new Texture(pathToScreen + "flags/english-flag-small.png");
        outline = new Texture(pathToScreen + "rectangle-outline.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width / 2, height / 2);

        batch = new SpriteBatch();

        skin = UIElements.createSkin();
        font = new BitmapFont();

        final ImageButton spainButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(spain)));
        spainButton.addListener(teamClickListener('S', spainButton));
        final ImageButton germanyButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(germany)));
        germanyButton.addListener(teamClickListener('G', germanyButton));
        final ImageButton italyButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(italy)));
        italyButton.addListener(teamClickListener('I', italyButton));
        final ImageButton englandButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(england)));
        englandButton.addListener(teamClickListener('E', englandButton));

        selectedTeam = code.charAt(2) == 'S' ? 'E' : 'S';
        selectedButton = code.charAt(2) == 'S' ? englandButton : spainButton;

        spainButton.setPosition(width / 10, 2.5f * height / 6);
        germanyButton.setPosition(width / 2, 2.5f * height / 6);
        italyButton.setPosition(width / 10, height / 6);
        englandButton.setPosition(width / 2, 1 + height / 6);

        TextButton continueButton = new TextButton("Continue", skin);
        continueButton.setPosition(4 * width / 5, height / 20);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (code.charAt(0) == '1') {
                    game.setScreen(new TokenSoccer(game, false, Team.getTeamFromChar(selectedTeam), Team.getTeamFromChar(selectedTeam)));
                }
                else if(code.charAt(0) == '2' && code.charAt(1) == '2') {
                    // TODO: handle the case when the two teams selected are the same
                    game.setScreen(new TokenSoccer(game, true, Team.getTeamFromChar(code.charAt(2)), Team.getTeamFromChar(selectedTeam)));
                }
                else {
                    game.setScreen(new TeamSelectionScreen(game, "22" + selectedTeam));
                }
            }
        });
        stage.addActor(continueButton);
        stage.addActor(spainButton);
        stage.addActor(italyButton);
        stage.addActor(englandButton);
        stage.addActor(germanyButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.20f, 0.59f, 0.02f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(logo, width / 2 - logo.getWidth() / 2, 5 * height / 6 - logo.getHeight() / 2);
        font.draw(batch, "Select team for player " + code.charAt(1), width / 2 - 100, 8.5f * height / 12);
        batch.draw(outline, -25 + selectedButton.localToStageCoordinates(new Vector2(0, 0)).x ,
                -4 + selectedButton.localToStageCoordinates(new Vector2(0, 0)).y - outline.getHeight() / 4);
        batch.end();

        stage.act();
        stage.draw();
    }

    private ClickListener teamClickListener(final char team, final ImageButton teamButton){
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x1, float y) {
                selectedButton = teamButton;
                selectedTeam = team;
            }
        };
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        UIElements.dispose();
        logo.dispose();
        font.dispose();
        stage.dispose();
        game.dispose();
        spain.dispose();
        england.dispose();
        germany.dispose();
        italy.dispose();
        skin.dispose();
    }
}
