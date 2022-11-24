package com.acl.datas.elements.items;

import com.acl.Tower;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.acl.enums.Constantes.LOOT_GOLD_INGOT;

public class GoldIngot extends Item {
    public GoldIngot(Vector2 v) {
        super(v);
        setHeight(7);
        setWidth(7);

        PolygonShape shape = new PolygonShape();
        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(0f, 0f);
        vectors[1] = new Vector2(getWidth(), 0f);
        vectors[2] = new Vector2(getWidth(), getHeight());
        vectors[3] = new Vector2(0f, getHeight());

        shape.set(vectors);
        setShape(shape);
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getGoldIngotTexture());
    }

    @Override
    public void applyEffect(Tower tower) {
        tower.setScore(tower.getScore() + LOOT_GOLD_INGOT);
    }

    @Override
    public boolean isAGoldIngot() {
        return true;
    }
}
