package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Token {
    private World world;
    public Vector2 initialPosition;
    public float radius;
    public Body body;
    static Texture background = new Texture("core/assets/ring-33.png");

    public Token(World world, Vector2 initialPosition, float radius) {
        this.world = world;
        this.initialPosition = initialPosition;
        this.radius = radius;
        this.body = createSoccerPlayer(initialPosition.x, initialPosition.y, radius);
    }

    private Body createSoccerPlayer(float x, float y, float radius) {
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

    public void changePosition(float newX, float newY) {
        body.setTransform(newX, newY, 0);
        body.setLinearVelocity(0, 0);
    }

    public boolean atRest() {
        return body.getLinearVelocity().epsilonEquals(0, 0, 1f);
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
