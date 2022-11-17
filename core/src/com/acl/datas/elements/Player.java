package com.acl.datas.elements;

import com.acl.enums.Direction;
import com.acl.enums.UserData;
import com.acl.enums.WeaponType;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player extends Element {
    // TODO Weapon can be swap
    private final WeaponType weaponType = WeaponType.SWORD;
    private Direction direction = Direction.NORTH;
    private int hp;

    public Player(Vector2 v) {
        super(v);
        setHeight(16);
        setWidth(16);

        // We define the shape of the player with 4 dots (vector2) as a square by height * width.
        shape = new PolygonShape();

        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(4f, 2f);
        vectors[1] = new Vector2(getWidth() - 4, 2f);
        vectors[2] = new Vector2(getWidth() - 4, getHeight() - 2f);
        vectors[3] = new Vector2(4f, getHeight() - 2f);

        hp = 100;

        this.shape.set(vectors);

        this.density = 1f;
        this.restitution = 1f;
        this.friction = 0f;
        this.setSprite();
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


    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getChevalierTexture());
    }

    public void setMotion(Vector2 v) {
        this.getBody().setLinearVelocity(v);
    }

    public void setDirection(Direction d) {
        this.direction = d;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    @Override
    public UserData getUserData() {
        return UserData.PLAYER;
    }

    public int getHp() {
        return hp;
    }

    public void receiveDamage(int amount) {
        this.hp -= amount;
    }

    public Direction getDirection() {
        return direction;
    }
}
