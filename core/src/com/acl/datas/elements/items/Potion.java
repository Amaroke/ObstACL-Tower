package com.acl.datas.elements.items;

import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Potion extends Item {
    public Potion(Vector2 v) {
        super(v);
        setHeight(14);
        setWidth(14);
        PolygonShape shape = new PolygonShape();
        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(4f, 2f);
        vectors[1] = new Vector2(getWidth() - 4, 2f);
        vectors[2] = new Vector2(getWidth() - 4, getHeight() - 2f);
        vectors[3] = new Vector2(4f, getHeight() - 2f);

        shape.set(vectors);
        setShape(shape);
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getFireBallTexture());
    }

    @Override
    public int applyEffect() {
        return 30;
    }

    @Override
    public boolean isAPotion() {
        return true;
    }
}
