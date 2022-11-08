package com.acl.datas.elements.monsters;

import com.acl.enums.UserData;
import com.acl.datas.elements.Element;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Monster extends Element {
    private int hp;

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

    @Override
    public void setFixture() {
        if ((this.getBodyDef() != null) && (this.getBody() != null)) {
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = this.shape;
            fixtureDef.density = this.density;
            fixtureDef.restitution = this.restitution;
            fixtureDef.friction = this.friction;
            getBody().createFixture(fixtureDef);
            getBody().setTransform(new Vector2(getPosition().x, getPosition().y), 0);
            getBody().setUserData(getUserData());
        }
        this.shape.dispose();
    }

    public void setMotion(Vector2 v) {
        this.getBody().setLinearVelocity(v);
    }

    public abstract void Move();

    public abstract void giveLoot();

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void dealDamage(int amount) {
        this.hp -= amount;
    }

    @Override
    public UserData getUserData() {
        return UserData.MONSTER;
    }
}