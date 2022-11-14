package com.acl.datas.elements.monsters;

import com.acl.enums.Direction;
import com.acl.enums.UserData;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

public class Guardian extends Monster {

    private Direction direction;
    private final int givenScore;
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

        // The base monster is represented by a square for now.
        PolygonShape shape = new PolygonShape();
        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(0f, 0f);
        vectors[1] = new Vector2(getWidth(), 0f);
        vectors[2] = new Vector2(getWidth(), getHeight());
        vectors[3] = new Vector2(0f, getHeight());

        shape.set(vectors);
        setShape(shape);

        this.density = 0.5f;
        this.restitution = 0.1f;
        this.friction = 0.5f;

        this.setHp(20);
        this.setDmg(30);
        this.givenScore = 100;
    }

    @Override
    public void setSprite() {
    }

    @Override
    public void Move() {
        // The guardian monster move horizontally or vertically only
        float monsterMovementForce = 10f;
        switch (this.getDirection()) {
            case NORTH -> setMotion(new Vector2(0, monsterMovementForce));
            case SOUTH -> setMotion(new Vector2(0, -monsterMovementForce));
            case EAST -> setMotion(new Vector2(monsterMovementForce, 0));
            case WEST -> setMotion(new Vector2(-monsterMovementForce, 0));
        }
    }

    public void chaneDirection() {
        switch (this.getDirection()) {
            case NORTH -> this.direction = Direction.SOUTH;
            case SOUTH -> this.direction = Direction.NORTH;
            case EAST ->  this.direction = Direction.WEST;
            case WEST ->  this.direction = Direction.EAST;
        }
    }

    public int giveLoot() {
        // The base monster gives gold coins and score
        return givenScore;
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

    public Direction getDirection() {
        return direction;
    }

    @Override
    public UserData getUserData() {
        return UserData.GUARDIAN;
    }
}