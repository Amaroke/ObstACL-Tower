package com.acl.datas.elements.monsters;

import com.acl.datas.elements.Element;
import com.acl.enums.UserData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class Monster extends Element {
    private int hp;
    private int dmg;

    public Monster(Vector2 v) {
        super(v);
    }

    @Override
    public void configureBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(getPosition());
        this.setBodyDef(bodyDef);
    }

    public void setMotion(Vector2 v) {
        this.getBody().setLinearVelocity(v);
    }

    public abstract void changeDirection();

    public abstract void Move();

    public abstract int giveLoot();

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void receiveDamage(int amount) {
        this.hp -= amount;
    }

    @Override
    public UserData getUserData() {
        return UserData.MONSTER;
    }
}