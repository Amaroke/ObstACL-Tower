package com.acl.datas.elements;

import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Chest extends Element{
    private final PolygonShape shape;
    private final float density;
    private final float restitution;
    private final float friction;
    public Chest(Vector2 v) {
        super(v);
        this.shape = new PolygonShape();
        this.density = 1f;
        this.restitution = 0.1f;
        this.friction = 0.25f;
        Vector2[] points = {
                new Vector2(0, 0),
                new Vector2(0, 100),
                new Vector2(100, 100),
                new Vector2(100, 0)
        };
        this.shape.set(points);
        this.setSprite();
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
        if ((this.getBodyDef() != null) && (this.getBody() != null)) {
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = density;
            fixtureDef.restitution = restitution;
            fixtureDef.friction = friction;
            getBody().createFixture(fixtureDef);
        }
        this.shape.dispose();
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getChestTexture());
    }
}