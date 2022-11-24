package com.acl.datas.elements.items;

import com.acl.Tower;
import com.acl.datas.elements.Element;
import com.acl.enums.UserData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class Item extends Element {
    public Item(Vector2 v) {
        super(v);
        this.sensor = true;
    }

    @Override
    public void configureBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(getPosition());
        this.setBodyDef(bodyDef);
    }

    @Override
    public UserData getUserData() {
        return UserData.ITEM;
    }

    public abstract void applyEffect(Tower tower);

    public boolean isAPotion() {
        return false;
    }

    public boolean isAGoldIngot() {
        return false;
    }

    @Override
    public boolean isItem() {
        return true;
    }
}
