package com.acl.datas.elements.monsters;

import com.acl.enums.Direction;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

import static com.acl.enums.Constantes.*;

public class Guardian extends Monster {

    private Direction direction;
    private final Animation<TextureRegion> animationNorth;
    private final Animation<TextureRegion> animationSouth;
    private final Animation<TextureRegion> animationWest;
    private final Animation<TextureRegion> animationEast;

    public Guardian(Vector2 v) {
        super(v);
        int random = new Random().nextInt(4) + 1;
        switch (random) {
            case 1 -> direction = Direction.NORTH;
            case 2 -> direction = Direction.SOUTH;
            case 3 -> direction = Direction.WEST;
            case 4 -> direction = Direction.EAST;
        }

        animationNorth = new Animation<>(1f, TextureRegion.split(TextureFactory.getGuardianTexture(), 48, 64)[0]);
        animationSouth = new Animation<>(1f, TextureRegion.split(TextureFactory.getGuardianTexture(), 48, 64)[2]);
        animationWest = new Animation<>(1f, TextureRegion.split(TextureFactory.getGuardianTexture(), 48, 64)[3]);
        animationEast = new Animation<>(1f, TextureRegion.split(TextureFactory.getGuardianTexture(), 48, 64)[1]);

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

        this.setHp(HP_GUARDIAN);
        this.setDmg(DMG_GUARDIAN);

        sensor = true;
    }

    @Override
    public void setSprite() {
    }

    @Override
    public void Move() {
        // The guardian monster move horizontally or vertically only
        switch (this.getDirection()) {
            case NORTH -> setMotion(new Vector2(0, SPD_GUARDIAN));
            case SOUTH -> setMotion(new Vector2(0, -SPD_GUARDIAN));
            case EAST -> setMotion(new Vector2(SPD_GUARDIAN, 0));
            case WEST -> setMotion(new Vector2(-SPD_GUARDIAN, 0));
        }
    }

    public void changeDirection() {
        switch (this.getDirection()) {
            case NORTH -> this.direction = Direction.SOUTH;
            case SOUTH -> this.direction = Direction.NORTH;
            case EAST -> this.direction = Direction.WEST;
            case WEST -> this.direction = Direction.EAST;
        }
    }

    public int giveLoot() {
        return LOOT_GUARDIAN;
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
    public boolean isGuardian() {
        return true;
    }

    @Override
    public boolean isLich() {
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

}