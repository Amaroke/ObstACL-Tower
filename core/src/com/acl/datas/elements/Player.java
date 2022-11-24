package com.acl.datas.elements;

import com.acl.enums.Direction;
import com.acl.enums.UserData;
import com.acl.enums.WeaponType;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.acl.enums.Constantes.HP_PLAYER;

public class Player extends Element {
    private WeaponType weaponType = WeaponType.FIREBALL;
    private Direction direction = Direction.NORTH;
    private final Animation<TextureRegion> animationNorth;
    private final Animation<TextureRegion> animationSouth;
    private final Animation<TextureRegion> animationWest;
    private final Animation<TextureRegion> animationEast;
    private int hp;
    private boolean isMoving = false;

    public Player(Vector2 v) {
        super(v);
        setHeight(16);
        setWidth(16);

        // We define the shape of the player with 4 dots (vector2) as a square by height * width.
        shape = new PolygonShape();

        Vector2[] vectors = new Vector2[4];
        vectors[0] = new Vector2(4f, 0f);
        vectors[1] = new Vector2(getWidth() - 4,0f);
        vectors[2] = new Vector2(getWidth() - 4, getHeight() - 3f);
        vectors[3] = new Vector2(4f, getHeight() - 3f);

        hp = HP_PLAYER;

        this.shape.set(vectors);

        this.density = 1f;
        this.restitution = 1f;
        this.friction = 0f;
        this.setSprite();

        animationNorth = new Animation<>(1f, TextureRegion.split(TextureFactory.getPlayerTexture(), 48, 64)[0]);
        animationSouth = new Animation<>(1f, TextureRegion.split(TextureFactory.getPlayerTexture(), 48, 64)[2]);
        animationWest = new Animation<>(1f, TextureRegion.split(TextureFactory.getPlayerTexture(), 48, 64)[3]);
        animationEast = new Animation<>(1f, TextureRegion.split(TextureFactory.getPlayerTexture(), 48, 64)[1]);

    }

    public void changeDirection() {
        switch (this.getDirection()) {
            case NORTH -> this.direction = Direction.SOUTH;
            case SOUTH -> this.direction = Direction.NORTH;
            case EAST -> this.direction = Direction.WEST;
            case WEST -> this.direction = Direction.EAST;
        }
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
        this.hp = Math.max(hp - amount, 0);
    }

    public Direction getDirection() {
        return direction;
    }

    public void swapWeapon() {
        if (weaponType == WeaponType.FIREBALL) {
            weaponType = WeaponType.SWORD;
        } else {
            weaponType = WeaponType.FIREBALL;
        }
    }

    public Animation<TextureRegion> getAnimationNorth() {
        return animationNorth;
    }

    public Animation<TextureRegion> getAnimationSouth() {
        return animationSouth;
    }

    public Animation<TextureRegion> getAnimationWest() {
        return animationWest;
    }

    public Animation<TextureRegion> getAnimationEast() {
        return animationEast;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void heal(int hp) {
        this.hp += hp;
        if(this.hp > 100){
            this.hp = 100;
        }
    }
}
