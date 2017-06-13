package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class TokenSoccer extends ApplicationAdapter implements InputProcessor {
	private boolean DEBUG = false;
	private OrthographicCamera camera;
	private float width, height;
	private final float RADIUS = 7;
	private final float BALL_RADIUS = 3;
	private World world;
	private Box2DDebugRenderer b2dr;
	private ArrayList<Body> players = new ArrayList<Body>();
	private float lastX, lastY; // the latest x and y coords that were clicked by the mouse
	private boolean lastClickOnBody; // this could probably be done without
	private Body lastBody; // the last body that was clicked by the mouse


	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width / 2, height / 2);


		world = new World(new Vector2(0.0f, 0.0f), false);
		b2dr = new Box2DDebugRenderer();

		Gdx.input.setInputProcessor(this);

		createBoundary();
		players.add(createPlayer(width/4 - 50, height/4 - 50, RADIUS));
		players.add(createPlayer(width/4 - 50, height/4 + 50, RADIUS));
		players.add(createPlayer(width/4 + 50, height/4 - 50, RADIUS));
		players.add(createPlayer(width/4 + 50, height/4 + 50, RADIUS));
		createPlayer(width / 4, height / 4, BALL_RADIUS);

	}

	private void createBoundary(){
		FixtureDef fixtureDefVertical = new FixtureDef();
		fixtureDefVertical.restitution = 1.1f;
		fixtureDefVertical.friction = 0.8f;
		PolygonShape polygonShape = new PolygonShape();
		// polygonShape.setAsBox(2, 3 * height / 16);
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
	}

	public void update(float deltaTime) {
		world.step(deltaTime, 6, 2);
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width / 2, height / 2);
	}

	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
	}

	private Body createPlayer(float x, float y, float radius) {
		Body pBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x, y);
		def.linearDamping = 0.6f;
		def.fixedRotation = true;

		pBody = world.createBody(def);
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);
		pBody.createFixture(circleShape, 1);
		circleShape.dispose();
		return pBody;
	}

	private Body contains(float x, float y) {
		for (Body body : players) {
			float xPos = body.getPosition().x;
			float yPos = body.getPosition().y;
			if ((x - xPos) * (x - xPos) + (y - yPos) * (y - yPos) <= RADIUS * RADIUS) {
				return body;
			}
		}
		return null;
	}

	/**
	 * Called when a key was pressed
	 *
	 * @param keycode one of the constants
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	/**
	 * Called when a key was released
	 *
	 * @param keycode one of the constants
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * Called when a key was typed
	 *
	 * @param character The character
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Called when the screen was touched or a mouse button was pressed.
	 *
	 * @param screenX The x coordinate, origin is in the upper left corner
	 * @param screenY The y coordinate, origin is in the upper left corner
	 * @param pointer the pointer for the event.
	 * @param button  the button
	 * @return whether the input was processed
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float x = Gdx.input.getX();
		float y = height - Gdx.input.getY();
		// the next line is a bit of a hack and might not always work
		Body body = contains(x/2, y/2);
		lastX = x/2;
		lastY = y/2;
		if (body != null) {
			// body.applyLinearImpulse(1000, 1000, x, y, false);
			lastClickOnBody = true;
			lastBody = body;
		}
		else {
			lastClickOnBody = false;
			lastBody = null;
		}
		return true;
	}

	/**
	 * Called when a finger was lifted or a mouse button was released.
	 *
	 * @param screenX
	 * @param screenY
	 * @param pointer the pointer for the event.
	 * @param button  the button   @return whether the input was processed
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// get the length of the distance between touchdown and touchup
		// if the last click was on a token, make the shot
		// arctan ranges from -pi/2 to pi/2
		if (!lastClickOnBody) return true;
		float releaseX = Gdx.input.getX()/2;
		float releaseY = (height - Gdx.input.getY())/2;

		float len = Math.max(20, (float) Math.sqrt((releaseX - lastX) * (releaseX - lastX) + (releaseY - lastY) * (releaseY - lastY)));
		float slope = (releaseY - lastY)/(releaseX - lastX);
		if (Float.isNaN(slope)) {
			return true;
		}
		float angle = (float) Math.atan(slope);
		// check the quadrant in which touchup lies wrt touchdown
		if (releaseX >= lastX) {
			lastBody.applyLinearImpulse((float) (-1000 * len * Math.cos(angle)), (float) (-1000 * len * Math.sin(angle)), lastX, lastY, false);
		}
		else {
			lastBody.applyLinearImpulse((float) (1000 * len * Math.cos(angle)), (float) (1000 * len * Math.sin(angle)), lastX, lastY, false);
		}

		return true;
	}

	/**
	 * Called when a finger or the mouse was dragged.
	 *
	 * @param screenX
	 * @param screenY
	 * @param pointer the pointer for the event.  @return whether the input was processed
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
	 *
	 * @param screenX
	 * @param screenY
	 * @return whether the input was processed
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * Called when the mouse wheel was scrolled. Will not be called on iOS.
	 *
	 * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
