package com.acl.datas.elements.monsters;

import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

public class BaseMonster extends Monster{

    public BaseMonster(Vector2 v) {
        super(v);

        setHeight(16);
        setWidth(16);

        //The base monster is represented by a square for now.
        PolygonShape shape = new PolygonShape();
        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(0f, 0f);
        vectors[1] = new Vector2(getWidth(), 0f);
        vectors[2] = new Vector2(getWidth(), getHeight());
        vectors[3] = new Vector2(0f, getHeight());

        shape.set(vectors);
        setShape(shape);

        setDensity(0.5f);
        setRestitution(0.1f);
        setFriction(0.5f);
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getChevalierTexture());
    }

    @Override
    public void Move() {
        //The base monster moves randomly up, left, right or down

        Random random = new Random();

        switch (random.nextInt(4)) {
            case 0 -> setMotion(new Vector2(0, 1f));
            case 1 -> setMotion(new Vector2(-1f, 0));
            case 2 -> setMotion(new Vector2(1f, 0));
            case 3 -> setMotion(new Vector2(0, -1f));
            default -> System.out.println("apprends à faire un random stp");
        }
    }

    public void giveLoot() {
        //The base monster gives gold coins and score
        //TODO : implement the loot system when possible (when gold coins / score are properly implemented)
    }
}