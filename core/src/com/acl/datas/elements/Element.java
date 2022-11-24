package com.acl.datas.elements;

import com.acl.enums.UserData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Element {
    private Vector2 position;
    private Body body;
    protected Sprite sprite;
    private BodyDef bodyDef;
    private float height;
    private float width;
    protected PolygonShape shape;
    protected float density;
    protected float restitution;
    protected float friction;
    protected boolean sensor = false;

    public Element(Vector2 v) {
        this.position = v;
    }

    public abstract void configureBodyDef();

    public void setFixture() {
        if ((this.getBodyDef() != null) && (this.getBody() != null)) {
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = density;
            fixtureDef.restitution = restitution;
            fixtureDef.friction = friction;
            fixtureDef.isSensor = sensor;
            getBody().createFixture(fixtureDef);
            getBody().setUserData(getUserData());
        }
    }

    public void createBody(World world) {
        this.body = world.createBody(this.bodyDef);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Body getBody() {
        return body;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract void setSprite();

    public void draw(SpriteBatch sb) {
        sb.draw(this.getSprite(), this.getBody().getPosition().x, this.getBody().getPosition().y, this.getWidth(), this.getHeight());
    }

    public abstract UserData getUserData();

    public void setShape(PolygonShape shape) {
        this.shape = shape;
    }

    public boolean isGuardian() {
        return false;
    }

    public boolean isLich() {
        return false;
    }

    public boolean isAMonster() {
        return false;
    }

    public boolean isItem() {
        return false;
    }

    public boolean isPlayer() {
        return false;
    }
}
