package com.acl.datas.elements;

import com.acl.datas.UserData;
import com.acl.managers.Direction;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class FireBall extends Weapon {
    public float velocity;
    private final Animation<TextureRegion> animation;


    public FireBall(Vector2 v, Direction d) {
        super(v, d);
        velocity = 1f;
        animation = new Animation<>(1f, TextureRegion.split(TextureFactory.getFireBallTexture(), 152, 152)[0]);
    }

    @Override
    public void update() {
        switch (this.getDirection()) {
            case NORTH -> this.getPosition().y += velocity;
            case SOUTH -> this.getPosition().y -= velocity;
            case EAST -> this.getPosition().x += velocity;
            case WEST -> this.getPosition().x -= velocity;
        }
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    @Override
    public UserData getUserData() {
        return UserData.FIREBALL;
    }
}
