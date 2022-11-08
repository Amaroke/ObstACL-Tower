package com.acl.datas.elements;

import com.acl.managers.Direction;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Weapon extends Element {
    private final Direction direction;

    public Weapon(Vector2 v, Direction d) {
        super(v);
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
    public void setFixture() {
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
