package com.acl.datas.elements.monsters;

import com.acl.enums.Direction;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

import static com.acl.enums.Constantes.*;

public class Slime extends Monster {

    private int nbMovBeforeChange = 0;

    public Slime(Vector2 v) {
        super(v);

        setHeight(16);
        setWidth(16);

        // The base monster is represented by a square for now.
        PolygonShape shape = new PolygonShape();
        Vector2[] vectors = new Vector2[8];
        vectors[0] = new Vector2(4f, 2f);
        vectors[1] = new Vector2(2f, 4f);

        vectors[2] = new Vector2(getWidth() - 4, 2f);
        vectors[3] = new Vector2(getWidth() - 2, 4f);

        vectors[4] = new Vector2(getWidth() - 4, getHeight() - 2);
        vectors[5] = new Vector2(getWidth() - 2, getHeight() - 4);

        vectors[6] = new Vector2(2f, getHeight() - 4);
        vectors[7] = new Vector2(4f, getHeight() - 2);
        shape.set(vectors);
        setShape(shape);

        this.density = 0.5f;
        this.restitution = 0.1f;
        this.friction = 0.5f;

        this.setHp(HP_SLIME);
        this.setDmg(DMG_SLIME);
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getSlimeTexture());
    }

    @Override
    public void Move() {
        // The base monster moves randomly up, left, right or down for a period of time that is random as well.
        if (nbMovBeforeChange == 0) {
            Random random = new Random();

            switch (random.nextInt(4)) {
                case 0 -> setMotion(new Vector2(0, SPD_SLIME));
                case 1 -> setMotion(new Vector2(-SPD_SLIME, 0));
                case 2 -> setMotion(new Vector2(SPD_SLIME, 0));
                case 3 -> setMotion(new Vector2(0, -SPD_SLIME));
            }
            nbMovBeforeChange = random.nextInt(50, 200);
        } else {
            nbMovBeforeChange -= 1;
        }
    }

    public int giveLoot() {
        // The base monster gives gold coins and score
        return LOOT_SLIME;
    }

    @Override
    public void changeDirection() {
    }

    @Override
    public Direction getDirection() {
        return null;
    }
}