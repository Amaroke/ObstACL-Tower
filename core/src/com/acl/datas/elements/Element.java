package com.acl.datas.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Element {
    private Vector2 position;
    private Body body;
    protected Sprite sprite;
    private BodyDef bodyDef;
    private float height;
    private float width;

    public Element(Vector2 v) {
        this.position = v;
    }

    public abstract void configureBodyDef();

    public abstract void setFixture();

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

    public void setBody(Body body) {
        this.body = body;
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
}
