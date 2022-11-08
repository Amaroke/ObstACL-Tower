package com.acl.datas.elements;

import com.acl.enums.UserData;
import com.acl.enums.Direction;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Sword extends Weapon {
    public float velocity;
    public int timeBeforeClear = 500;


    public Sword(Vector2 v, Direction d) {
        super(v, d);
        timeBeforeClear = 500;
    }

    @Override
    public void update() {
        timeBeforeClear -= 1;
        if (timeBeforeClear == 1) {
            //setPosition(-100,-100);
        }
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getStairTexture());
    }

    public UserData getUserData() {
        return UserData.WEAPON;
    }
}
