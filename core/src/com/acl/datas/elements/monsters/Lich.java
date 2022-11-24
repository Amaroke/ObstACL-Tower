package com.acl.datas.elements.monsters;

import com.acl.enums.Direction;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

import static com.acl.enums.Constantes.*;

public class Lich extends Monster {
    private final Animation<TextureRegion> animationNorth;
    private final Animation<TextureRegion> animationSouth;
    private final Animation<TextureRegion> animationWest;
    private final Animation<TextureRegion> animationEast;
    private Direction direction;

    public Lich(Vector2 v) {
        super(v);
        int random = new Random().nextInt(4) + 1;
        switch (random) {
            case 1 -> direction = Direction.NORTH;
            case 2 -> direction = Direction.SOUTH;
            case 3 -> direction = Direction.WEST;
            case 4 -> direction = Direction.EAST;
        }

        animationNorth = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[0]);
        animationSouth = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[2]);
        animationWest = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[3]);
        animationEast = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[1]);

        setHeight(14);
        setWidth(14);

        PolygonShape shape = new PolygonShape();
        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(4f, 1f);
        vectors[1] = new Vector2(getWidth() - 4, 1f);
        vectors[2] = new Vector2(getWidth() - 4, getHeight() - 3f);
        vectors[3] = new Vector2(4f, getHeight() - 3f);

        shape.set(vectors);
        setShape(shape);

        this.setHp(HP_LICH);
        this.setDmg(DMG_LICH);

        sensor = true;
    }

    @Override
    public void setSprite() {
    }

    @Override
    public void Move() {
        // The lich monster move around the border
        switch (this.getDirection()) {
            case NORTH -> setMotion(new Vector2(0, SPD_LICH));
            case SOUTH -> setMotion(new Vector2(0, -SPD_LICH));
            case EAST -> setMotion(new Vector2(SPD_LICH, 0));
            case WEST -> setMotion(new Vector2(-SPD_LICH, 0));
        }
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public Animation<TextureRegion> getAnimationNorth() {
        return animationNorth;
    }

    public Animation<TextureRegion> getAnimationSouth() {
        return animationSouth;
    }

    public Animation<TextureRegion> getAnimationWest() {
        return animationWest;
    }

    public Animation<TextureRegion> getAnimationEast() {
        return animationEast;
    }

    @Override
    public boolean isLich() {
        return true;
    }

    @Override
    public int giveLoot() {
        return LOOT_LICH;
    }

    public void changeDirection() {
        switch (this.getDirection()) {
            case NORTH -> this.direction = Direction.EAST;
            case SOUTH -> this.direction = Direction.WEST;
            case EAST -> this.direction = Direction.SOUTH;
            case WEST -> this.direction = Direction.NORTH;
        }
    }
}
