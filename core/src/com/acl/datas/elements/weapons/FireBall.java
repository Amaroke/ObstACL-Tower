package com.acl.datas.elements.weapons;

import com.acl.enums.Direction;
import com.acl.enums.UserData;
import com.acl.enums.WeaponType;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.acl.enums.Constantes.DMG_FIREBALL;
import static com.acl.enums.Constantes.SPD_FIREBALL;

public class FireBall extends Weapon {

    public FireBall(Vector2 v, Direction d) {
        super(v, d, new Animation<>(1f, TextureRegion.split(TextureFactory.getFireBallTexture(), 152, 152)[0]));
        velocity = SPD_FIREBALL;
        sensor = true;
    }

    @Override
    public void setSprite() {

    }

    @Override
    public void update() {
        switch (this.getDirection()) {
            case NORTH -> this.getBody().setLinearVelocity(0, velocity);
            case SOUTH -> this.getBody().setLinearVelocity(0, -velocity);
            case EAST -> this.getBody().setLinearVelocity(velocity, 0);
            case WEST -> this.getBody().setLinearVelocity(-velocity, 0);
        }
    }

    @Override
    public UserData getUserData() {
        return UserData.WEAPON;
    }

    @Override
    public WeaponType getType() {
        return WeaponType.FIREBALL;
    }

    @Override
    public int getDamage() {
        return DMG_FIREBALL;
    }
}
