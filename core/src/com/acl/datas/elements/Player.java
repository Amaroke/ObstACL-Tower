package com.acl.datas.elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player extends Element {

    private final PolygonShape shape;

    public Player(Vector2 v) {
        super(v);

        setHeight(64);
        setWidth(64);

        //We define the shape of the player with 4 dots (vector2) as a square by height * width .
        shape = new PolygonShape();

        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(0f, 0f);
        vectors[1] = new Vector2(getWidth(), 0f);
        vectors[2] = new Vector2(getWidth(), getHeight());
        vectors[3] = new Vector2(0f, getHeight());

        this.shape.set(vectors);
    }

    @Override
    public void configureBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(getPosition());

        this.setBodyDef(bodyDef);
    }

    @Override
    public void setFixture() {
        if ((this.getBodyDef() != null) && (this.getBody() != null)) {
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = this.shape;
            fixtureDef.density = 0.5f;
            fixtureDef.restitution = 0.1f;
            fixtureDef.friction = 0.5f;
            getBody().createFixture(fixtureDef);
            getBody().setTransform(new Vector2(getPosition().x, getPosition().y), 0);
        }
        this.shape.dispose();
    }

    public void setMotion(Vector2 v) {
        this.getBody().setLinearVelocity(v);
    }
}
