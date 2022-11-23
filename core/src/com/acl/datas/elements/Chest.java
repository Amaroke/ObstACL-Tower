package com.acl.datas.elements;

import com.acl.enums.UserData;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

import static com.acl.enums.Constantes.MAX_LOOT_CHEST;
import static com.acl.enums.Constantes.MIN_LOOT_CHEST;

public class Chest extends Element {
    private final int givenScore;

    public Chest(Vector2 v) {
        super(v);
        this.shape = new PolygonShape();

        Random random = new Random();
        this.givenScore = random.nextInt(MIN_LOOT_CHEST, MAX_LOOT_CHEST);

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
        return givenScore;
    }
}
