package com.acl.datas.elements.weapons;

import com.acl.enums.Direction;
import com.acl.enums.UserData;
import com.acl.enums.WeaponType;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Sword extends Weapon {

    private int count = 0;
    private boolean retour = false;
    public Sword(Vector2 v, Direction d) {
        super(v, d, new Animation<>(1f, TextureRegion.split(TextureFactory.getFireBallTexture(), 152, 152)[0]));
        velocity = 100f;
        sensor = true;
    }

    @Override
    public void update() {
        if(count < 20 && !retour) {
            count++;
            switch (this.getDirection()) {
                case NORTH -> this.getBody().setLinearVelocity(0, velocity);
                case SOUTH -> this.getBody().setLinearVelocity(0, -velocity);
                case EAST -> this.getBody().setLinearVelocity(velocity, 0);
                case WEST -> this.getBody().setLinearVelocity(-velocity, 0);
            }
        } else {
            retour = true;
            count --;
            switch (this.getDirection()) {
                case NORTH -> this.getBody().setLinearVelocity(0, -velocity);
                case SOUTH -> this.getBody().setLinearVelocity(0, +velocity);
                case EAST -> this.getBody().setLinearVelocity(-velocity, 0);
                case WEST -> this.getBody().setLinearVelocity(+velocity, 0);
            }
        }
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getSwordTexture());
    }

    public void changeDirection() {
        switch (this.getDirection()) {
            case NORTH -> this.setDirection(Direction.SOUTH);
            case SOUTH -> this.setDirection(Direction.NORTH);
            case EAST ->  this.setDirection(Direction.WEST);
            case WEST ->  this.setDirection(Direction.EAST);
        }
    }

    public UserData getUserData() {
        return UserData.WEAPON;
    }

    @Override
    public boolean toDestroy() {
        return count <= 5 && retour;
    }

    @Override
    public WeaponType getType() {
        return WeaponType.SWORD;
    }


}
