package com.acl.datas.elements.weapons;

import com.acl.enums.UserData;
import com.acl.enums.Direction;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Sword extends Weapon {

    public Sword(Vector2 v, Direction d) {
        super(v, d);
    }

    @Override
    public void update() {
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getStairTexture());
    }

    public UserData getUserData() {
        return UserData.WEAPON;
    }
}
