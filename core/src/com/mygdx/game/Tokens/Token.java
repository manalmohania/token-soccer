package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Token {
    private Vector2 initialPosition;
    private float radius;
    private Body body;
    static Texture background = new Texture("images/tokens/ring-33.png");

    Token(Vector2 initialPosition, float radius, World world) {
        this.initialPosition = initialPosition;
        this.radius = radius;
        this.body = createSoccerPlayer(world, initialPosition.x, initialPosition.y, radius);
    }

    private Body createSoccerPlayer(World world, float x, float y, float radius) {
        Body pBody;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.05f;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        def.linearDamping = 0.3f;
        def.fixedRotation = true;

        pBody = world.createBody(def);
        pBody.createFixture(fixtureDef);
        circleShape.dispose();
        return pBody;
    }

    public void changePosition(float newX, float newY) {
        body.setTransform(newX, newY, 0);
        body.setLinearVelocity(0, 0);
    }

    public boolean atRest() {
        return body.getLinearVelocity().epsilonEquals(0, 0, 5f);
    }

    public Vector2 getInitialPosition() {
        return initialPosition;
    }

    public float getRadius() {
        return radius;
    }

    public Body getBody() {
        return body;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, body.getPosition().x * 2 - texture.getWidth()/2, body.getPosition().y * 2 - texture.getHeight()/2);
    }

    public static void dispose() {
        background.dispose();
    }
}
