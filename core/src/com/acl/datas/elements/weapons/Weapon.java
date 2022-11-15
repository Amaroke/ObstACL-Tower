package com.acl.datas.elements.weapons;

import com.acl.datas.elements.Element;
import com.acl.enums.Direction;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class Weapon extends Element {
    private Direction direction;
    public float velocity;
    private final Animation<TextureRegion> animation;
    public Weapon(Vector2 v, Direction d, Animation<TextureRegion> animation) {
        super(v);
        this.animation = animation;
        this.shape = new PolygonShape();
        this.density = 0f;
        this.restitution = 0f;
        this.friction = 0f;
        Vector2[] points = {new Vector2(0, 0), new Vector2(0, 16), new Vector2(16, 16), new Vector2(16, 0)};
        this.shape.set(points);
        direction = d;
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
    public abstract void setSprite();

    public abstract void update();

    public Direction getDirection() {
        return direction;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean toDestroy() {
        return false;
    }
}
