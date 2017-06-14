package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Tokens.BallToken;
import com.mygdx.game.Tokens.PlayerToken;
import com.mygdx.game.Tokens.Token;

import java.util.ArrayList;
import java.util.Random;

public class TokenSoccer extends ApplicationAdapter {
	private boolean DEBUG = false;
	private OrthographicCamera camera;
	private float width, height;
	private float p1_goal, p2_goal;
	private World world;
	private Box2DDebugRenderer b2dr;
	// TODO different colors
	private ArrayList<PlayerToken> p1_soccer_players = new ArrayList<PlayerToken>();
    private ArrayList<PlayerToken> p2_soccer_players = new ArrayList<PlayerToken>();
    private BallToken ball;
	private Player p1;
	private Player p2;
	private Players players;
	private Random random = new Random();
	private Texture ballTexture, p1Texture, p2Texture, woodTexture;
	private BitmapFont font;
    private SpriteBatch batch;

	@Override
	public void create() {
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();

		width = Gdx.graphics.getWidth();
		this.p1_goal = width / 16;
		this.p2_goal = 7 * width / 16;
		height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width / 2, height / 2);


		world = new World(new Vector2(0.0f, 0.0f), false);
		b2dr = new Box2DDebugRenderer();

		createBoundary();
		p1_soccer_players.add(new PlayerToken(world, new Vector2(width / 4 - 50, height / 4 - 50)));
		p1_soccer_players.add(new PlayerToken(world, new Vector2(width / 4 - 50, height / 4 + 50)));
		p2_soccer_players.add(new PlayerToken(world, new Vector2(width / 4 + 50, height / 4 - 50)));
		p2_soccer_players.add(new PlayerToken(world, new Vector2(width / 4 + 50, height / 4 + 50)));
		this.ball = new BallToken(world, new Vector2(width / 4, height /4));

        // TODO: the blue ball player seems to be bigger than the red one; make the sizes equals
		ballTexture = new Texture("ball.png");
		p1Texture = new Texture("red_circle.png");
		p2Texture = new Texture("blue_circle.png");
        woodTexture = new Texture("wood.png");

		// Temporarily putting here
		String name1 = "Bob";
		String name2 = "Bob2";
		Boolean isp1Bot = false;
		Boolean isp2Bot = false;
		createPlayers(name1, name2, isp1Bot, isp2Bot);

		Events eventHandler = new Events(players);
		Gdx.input.setInputProcessor(eventHandler);
	}

	private void createPlayers(String name1, String name2, Boolean isp1Bot, Boolean isp2Bot) {
		// indicates if bot player
		this.p1 = new Player(name1, isp1Bot, p1_soccer_players);
		this.p2 = new Player(name2, isp2Bot, p2_soccer_players);
		this.players = new Players(p1, p2);
	}

	private void createBoundary(){
		FixtureDef fixtureDefVertical = new FixtureDef();
		fixtureDefVertical.restitution = 1.1f;
		fixtureDefVertical.friction = 0.8f;
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(2, height/16);
		fixtureDefVertical.shape = polygonShape;

		FixtureDef fixtureDefHorizontal = new FixtureDef();
		fixtureDefHorizontal.restitution = 1.1f;
		fixtureDefHorizontal.friction = 0.8f;
		PolygonShape polygonShape2 = new PolygonShape();
		polygonShape2.setAsBox(3 * width / 16, 2);
		fixtureDefHorizontal.shape = polygonShape2;

		BodyDef leftDefLower = new BodyDef();
		leftDefLower.type = BodyDef.BodyType.StaticBody;
		leftDefLower.position.set(width / 16, height / 8);
		Body leftLower = world.createBody(leftDefLower);

		BodyDef leftDefUpper = new BodyDef();
		leftDefUpper.type = BodyDef.BodyType.StaticBody;
		leftDefUpper.position.set(width / 16, 3 * height / 8);
		Body leftUpper = world.createBody(leftDefUpper);

		BodyDef rightDefLower = new BodyDef();
		rightDefLower.type = BodyDef.BodyType.StaticBody;
		rightDefLower.position.set(7 * width / 16 , height/ 8);
		Body rightLower = world.createBody(rightDefLower);

		BodyDef rightDefUpper = new BodyDef();
		rightDefUpper.type = BodyDef.BodyType.StaticBody;
		rightDefUpper.position.set(7 * width / 16 , 3 * height/ 8);
		Body rightUpper = world.createBody(rightDefUpper);

		BodyDef upDef = new BodyDef();
		upDef.type = BodyDef.BodyType.StaticBody;
		upDef.position.set(width / 4,  7 * height / 16);
		Body up = world.createBody(upDef);

		BodyDef downDef = new BodyDef();
		downDef.type = BodyDef.BodyType.StaticBody;
		downDef.position.set(width / 4, height / 16);
		Body down = world.createBody(downDef);

		leftLower.createFixture(fixtureDefVertical);
		leftUpper.createFixture(fixtureDefVertical);
		rightLower.createFixture(fixtureDefVertical);
		rightUpper.createFixture(fixtureDefVertical);
		up.createFixture(fixtureDefHorizontal);
		down.createFixture(fixtureDefHorizontal);
		polygonShape.dispose();
	}

	@Override
	public void render() {
		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		b2dr.render(world, camera.combined);

		batch.begin();
		//TODO some function that correctly positons
		font.draw(batch, p1.getName(), 0,height);
		font.draw(batch, "Score:" + p1.getScore(), 0, height-20);
		font.draw(batch, p2.getName(), width-100,height);
		font.draw(batch, "Score:" + p2.getScore(), width-100, height-20);
		font.draw(batch, "Timer:" + players.getTimer().getTimeRemaining(), width/2 - 100, height);
        ball.draw(batch, ballTexture);
		for (int i = 0; i < p1.tokens.size(); i++) {
		    p1.tokens.get(i).draw(batch, p1Texture);
            p2.tokens.get(i).draw(batch, p2Texture);
		}
		batch.end();
	}

	public void update(float deltaTime) {
		world.step(deltaTime, 6, 2);

        if (ball.token.getPosition().x < p1_goal) {
            p2.scoreGoal();
            batch.begin();
            font.draw(batch, "Score:" + p2.getScore(), width-100, height-20);
            batch.end();
            reset();
        }
        if (ball.token.getPosition().x > p2_goal) {
            p1.scoreGoal();
            batch.begin();
            font.draw(batch, "Score:" + p1.getScore(), 0, height-20);
            batch.end();
            reset();
        }
		for (Token token : p1_soccer_players) {
			if (token.token.getPosition().x < p1_goal || token.token.getPosition().x > p2_goal) {
				token.changePosition(p1_goal + random.nextFloat() * (p2_goal - p1_goal), random.nextFloat() * (6 * height / 16) + height / 16);
			}
		}

		for (Token token : p2_soccer_players) {
			if (token.token.getPosition().x < p1_goal || token.token.getPosition().x > p2_goal) {
				token.changePosition(p1_goal + random.nextFloat() * (p2_goal - p1_goal), random.nextFloat() * (6 * height / 16) + height / 16);
			}
		}

		if (!players.getTimer().timeRemaining()) {
			players.toggleTurns();
		}
	}

	private void reset() {
		ball.changePosition(ball.initialPosition.x, ball.initialPosition.y);
		for (int i = 0; i < 2; i++) {
			Token p1 = p1_soccer_players.get(i);
			Token p2 = p2_soccer_players.get(i);
			p1.changePosition(p1.initialPosition.x, p1.initialPosition.y);
			p2.changePosition(p2.initialPosition.x, p2.initialPosition.y);
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width / 2, height / 2);
	}

	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
		batch.dispose();
		ballTexture.dispose();
	}
}
