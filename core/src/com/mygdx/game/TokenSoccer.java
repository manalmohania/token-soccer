package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Players.HumanPlayer;
import com.mygdx.game.Players.RandomBot;
import com.mygdx.game.Tokens.*;
import java.util.ArrayList;
import java.util.Random;

// TODO - create the goal images
// TODO - ensure that ball does not stick to side walls
// TODO - next shot played only after system at rest
// TODO - some more refactoring
// TODO - menus
// TODO - sound
// TODO - network stuff - TCP

public class TokenSoccer extends com.badlogic.gdx.Game {
	private boolean DEBUG = false;
	private OrthographicCamera camera;
	private float width, height;  // these are world coordinates
	private float p1Goal, p2Goal; // so are these
	private World world;
	private Box2DDebugRenderer b2dr;
	private ArrayList<PlayerToken> p1Tokens;
    private ArrayList<PlayerToken> p2Tokens;
	private Random random = new Random();
	private Texture ballTexture, p1Texture, p2Texture, woodHTexture, woodVTexture, fieldTexture, goalRight, goalLeft;
	private BitmapFont font;
    private SpriteBatch batch;
    private Game gameElements;


    /*
    * The game will NOT work right now if player 1 is a bot. I'll make those changes later today.
    * Also, right now it appears as if the bot is making a move simultaneously with the human. That will be fixed once
    * Allen ensures that moves are made once the system is at rest
    * */

	@Override
	public void create() {
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();

		width = Gdx.graphics.getWidth();
		p1Goal = width / 16;
		p2Goal = 7 * width / 16;
		height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width / 2, height / 2);

		world = new World(new Vector2(0.0f, 0.0f), false);
		b2dr = new Box2DDebugRenderer();

		createBoundary();
		p1Tokens = new ArrayList<PlayerToken>();
		p1Tokens.add(new PlayerToken(world, new Vector2(width / 4 - 50, height / 4 - 50), "10"));
		p1Tokens.add(new PlayerToken(world, new Vector2(width / 4 - 50, height / 4 + 50), "11"));

		p2Tokens = new ArrayList<PlayerToken>();
		p2Tokens.add(new PlayerToken(world, new Vector2(width / 4 + 50, height / 4 - 50), "20"));
		p2Tokens.add(new PlayerToken(world, new Vector2(width / 4 + 50, height / 4 + 50), "21"));

		ballTexture = new Texture("core/assets/ball.png");
		p1Texture = new Texture("core/assets/spain-32.png");
		p2Texture = new Texture("core/assets/germany-32.png");
        woodHTexture = new Texture("core/assets/wood_480x25.png");
        woodVTexture = new Texture("core/assets/wood_30x160.png");
		fieldTexture = new Texture("core/assets/field-480x360.png");
		goalRight = new Texture("core/assets/goal-60x100.png");

		// Temporarily putting here
		String name1 = "Bob";
		String name2 = "Bob2";
		createPlayers(name1, name2);



		Events eventHandler = new Events(gameElements);
		Gdx.input.setInputProcessor(eventHandler);

	}

	private void createPlayers(String name1, String name2) {
		// indicates if bot player
		gameElements = new Game(
                new HumanPlayer(name1, p1Tokens),
                new RandomBot(name2, p2Tokens),
                new BallToken(world, new Vector2(width / 4, height /4)));
	}

	private void createBoundary(){
		FixtureDef fixtureDefVertical = new FixtureDef();
		fixtureDefVertical.restitution = 1.1f;
		fixtureDefVertical.friction = 0.8f;
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(2 , height/16 );
		fixtureDefVertical.shape = polygonShape;

		FixtureDef fixtureDefHorizontal = new FixtureDef();
		fixtureDefHorizontal.restitution = 1.1f;
		fixtureDefHorizontal.friction = 0.8f;
		PolygonShape polygonShape2 = new PolygonShape();
		polygonShape2.setAsBox(3 * width / 16 , 2 );
		fixtureDefHorizontal.shape = polygonShape2;

		BodyDef leftDefLower = new BodyDef();
		leftDefLower.type = BodyDef.BodyType.StaticBody;
		leftDefLower.position.set(width / 16 , height / 8 );
		Body leftLower = world.createBody(leftDefLower);

		BodyDef leftDefUpper = new BodyDef();
		leftDefUpper.type = BodyDef.BodyType.StaticBody;
		leftDefUpper.position.set(width / 16 , 3 * height / 8 );
		Body leftUpper = world.createBody(leftDefUpper);

		BodyDef rightDefLower = new BodyDef();
		rightDefLower.type = BodyDef.BodyType.StaticBody;
		rightDefLower.position.set(7 * width / 16 , height/ 8 );
		Body rightLower = world.createBody(rightDefLower);

		BodyDef rightDefUpper = new BodyDef();
		rightDefUpper.type = BodyDef.BodyType.StaticBody;
		rightDefUpper.position.set(7 * width / 16, 3 * height/ 8);
		Body rightUpper = world.createBody(rightDefUpper);

		BodyDef upDef = new BodyDef();
		upDef.type = BodyDef.BodyType.StaticBody;
		upDef.position.set(width / 4 ,  7 * height / 16 );
		Body up = world.createBody(upDef);

		BodyDef downDef = new BodyDef();
		downDef.type = BodyDef.BodyType.StaticBody;
		downDef.position.set(width / 4 , height / 16 );
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
		//TODO some function that correctly positions
		font.draw(batch, gameElements.getPlayer2().getName(), 0, height);
		font.draw(batch, "Score:" + gameElements.getPlayer1().getScore(), 0, height-20);
		font.draw(batch, gameElements.getPlayer2().getName(), width-100,height);
		font.draw(batch, "Score:" + gameElements.getPlayer2().getScore(), width-100, height-20);
		font.draw(batch, "Timer:" + gameElements.getTimer().getTimeRemaining(), width/2 - 100, height);
		batch.draw(fieldTexture, 2 * width / 4 - fieldTexture.getWidth()/2, 2 * height / 4 - fieldTexture.getHeight() / 2);
		batch.draw(goalRight, 2 * 7 * width / 16, 2 * 2 * height / 8 - goalRight.getHeight()/2);
        gameElements.getBallToken().draw(batch, ballTexture);
		for (int i = 0; i < gameElements.getPlayer1().getTokens().size(); i++) {
		    gameElements.getPlayer1().getTokens().get(i).draw(batch, p1Texture);
            gameElements.getPlayer2().getTokens().get(i).draw(batch, p2Texture);
		}
		batch.draw(woodHTexture, 2 * width / 4 - woodHTexture.getWidth()/2, 2 * 7 * height / 16 - 4);
        batch.draw(woodHTexture, 2 * width / 4 - woodHTexture.getWidth()/2, 2 * height / 16 - woodHTexture.getHeight() + 5);
        batch.draw(woodVTexture, 2 * width / 16 - woodVTexture.getWidth() + 4, 2 * height / 8 - woodVTexture.getHeight()/2);
        batch.draw(woodVTexture, 2 * width / 16 - woodVTexture.getWidth() + 4, 2* 3 * height / 8 - woodVTexture.getHeight()/2);
        batch.draw(woodVTexture, 2 * 7 * width / 16 - 5, 2 * height / 8 - woodVTexture.getHeight() / 2);
        batch.draw(woodVTexture, 2 * 7 * width / 16 - 5, 2 * 3 * height / 8 - woodVTexture.getHeight() / 2);
        batch.end();
	}

	private void update(float deltaTime) {
		world.step(deltaTime, 6, 2);

        if (gameElements.getBallToken().body.getPosition().x < p1Goal) {
            gameElements.getPlayer2().scoreGoal();
            batch.begin();
            font.draw(batch, "Score:" + gameElements.getPlayer2().getScore(), width-100, height-20);
            batch.end();
            reset();
        }
        if (gameElements.getBallToken().body.getPosition().x > p2Goal) {
            gameElements.getPlayer1().scoreGoal();
            batch.begin();
            font.draw(batch, "Score:" + gameElements.getPlayer1().getScore(), 0, height-20);
            batch.end();
            reset();
        }
		for (Token token : p1Tokens) {
			if (token.body.getPosition().x < p1Goal || token.body.getPosition().x > p2Goal) {
				token.changePosition(p1Goal + random.nextFloat() * (p2Goal - p1Goal), random.nextFloat() * (6 * height / 16) + height / 16);
			}
		}

		for (Token token : p2Tokens) {
			if (token.body.getPosition().x < p1Goal || token.body.getPosition().x > p2Goal) {
				token.changePosition(p1Goal + random.nextFloat() * (p2Goal - p1Goal), random.nextFloat() * (6 * height / 16) + height / 16);
			}
		}

		if (!gameElements.getTimer().expired()) {
			gameElements.toggleTurns();
		}
	}

	private void reset() {
		gameElements.getBallToken().changePosition(gameElements.getBallToken().initialPosition.x, gameElements.getBallToken().initialPosition.y);
		for (int i = 0; i < 2; i++) {
			Token p1 = p1Tokens.get(i);
			Token p2 = p2Tokens.get(i);
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
        p1Texture.dispose();
        p2Texture.dispose();
        woodVTexture.dispose();
        woodHTexture.dispose();
        fieldTexture.dispose();
        Token.dispose();
	}
}
