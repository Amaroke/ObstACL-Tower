package com.acl.datas.elements.monsters;

import com.acl.datas.elements.Element;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class Monster extends Element {
    //TODO ajouter les statistiques communes aux monstres (hp, atk, ...)

    private PolygonShape shape;

    private float density;
    private float restitution;
    private float friction;

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
        }
        this.shape.dispose();
    }

    public void setMotion(Vector2 v) {
        this.getBody().setLinearVelocity(v);
    }

    public abstract void Move();

    public abstract void giveLoot();

    public void setShape(PolygonShape shape) {
        this.shape = shape;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }
}