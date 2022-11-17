package com.acl.datas.elements;

import com.acl.enums.UserData;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

public class Chest extends Element {
    private final int givenScore;

    public Chest(Vector2 v) {
        super(v);
        this.shape = new PolygonShape();
        this.density = 1f;
        this.restitution = 0.1f;
        this.friction = 0.25f;

        Random random = new Random();
        this.givenScore = random.nextInt(50, 200);

        Vector2[] points = {new Vector2(2, 2), new Vector2(12, 2), new Vector2(12, 12), new Vector2(2, 12), new Vector2(4, 14), new Vector2(10, 14)};
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
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getChestTexture());
    }

    @Override
    public UserData getUserData() {
        return UserData.CHEST;
    }

    public int giveLoot() {
        //TODO Soon gold & more...
        return givenScore;
    }
}
