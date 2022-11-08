package com.acl.datas.elements.weapons;

import com.acl.enums.Direction;
import com.acl.enums.UserData;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class FireBall extends Weapon {
    public float velocity;
    private final Animation<TextureRegion> animation;


    public FireBall(Vector2 v, Direction d) {
        super(v, d);
        velocity = 50f;
        sensor = true;
        animation = new Animation<>(1f, TextureRegion.split(TextureFactory.getFireBallTexture(), 152, 152)[0]);
    }

    @Override
    public void update() {
        switch (this.getDirection()) {
            case NORTH -> this.getBody().setLinearVelocity(0,velocity);
            case SOUTH -> this.getBody().setLinearVelocity(0, -velocity);
            case EAST -> this.getBody().setLinearVelocity(velocity,0);
            case WEST -> this.getBody().setLinearVelocity(-velocity,0);
        }
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    @Override
    public UserData getUserData() {
        return UserData.WEAPON;
    }
}
