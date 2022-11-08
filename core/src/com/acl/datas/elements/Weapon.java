package com.acl.datas.elements;

import com.acl.enums.Direction;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class Weapon extends Element {
    private final Direction direction;


    public Weapon(Vector2 v, Direction d) {
        super(v);
        this.shape = new PolygonShape();
        this.density = 1f;
        this.restitution = 0.1f;
        this.friction = 0.25f;
        Vector2[] points = {
                new Vector2(0, 0),
                new Vector2(0, 16),
                new Vector2(16, 16),
                new Vector2(16, 0)
        };
        this.shape.set(points);
        direction = d;
    }

    @Override
    public void configureBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(getPosition());

        this.setBodyDef(bodyDef);
    }

    @Override
    public void setSprite() {
    }

    public void update() {
    }

    public Direction getDirection() {
        return direction;
    }
}
