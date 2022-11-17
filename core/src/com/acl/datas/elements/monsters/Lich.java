package com.acl.datas.elements.monsters;

import com.acl.enums.Direction;
import com.acl.enums.UserData;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Lich extends Monster {

    private final int givenScore;
    private final Animation<TextureRegion> animationNorth;
    private final Animation<TextureRegion> animationSouth;
    private final Animation<TextureRegion> animationWest;
    private final Animation<TextureRegion> animationEast;
    private Direction direction;

    public Lich(Vector2 v) {
        super(v);
        direction = Direction.EAST;
        animationNorth = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[0]);
        animationSouth = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[2]);
        animationWest = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[3]);
        animationEast = new Animation<>(1f, TextureRegion.split(TextureFactory.getLichTexture(), 48, 64)[1]);

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
        this.setDmg(10);
        this.givenScore = 120;
    }

    @Override
    public void setSprite() {

    }

    @Override
    public void Move() {
        // The lich monster move around the
        float monsterMovementForce = 10f;
        switch (this.getDirection()) {
            case NORTH -> setMotion(new Vector2(0, monsterMovementForce));
            case SOUTH -> setMotion(new Vector2(0, -monsterMovementForce));
            case EAST -> setMotion(new Vector2(monsterMovementForce, 0));
            case WEST -> setMotion(new Vector2(-monsterMovementForce, 0));
        }
    }

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
    public boolean isGuardian() {
        return false;
    }

    @Override
    public int giveLoot() {
        return 0;
    }

    public void chaneDirection() {
        switch (this.getDirection()) {
            case NORTH -> this.direction = Direction.EAST;
            case SOUTH -> this.direction = Direction.WEST;
            case EAST -> this.direction = Direction.SOUTH;
            case WEST -> this.direction = Direction.NORTH;
        }
    }

    @Override
    public UserData getUserData() {
        return UserData.LICH;
    }
}
