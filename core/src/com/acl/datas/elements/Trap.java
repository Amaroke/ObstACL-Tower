package com.acl.datas.elements;

import com.acl.enums.UserData;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.acl.enums.Constantes.DMG_TRAP;

public class Trap extends Element {
    private final int dealDamage;

    public Trap(Vector2 v) {
        super(v);
        this.shape = new PolygonShape();

        this.sensor = true;
        this.dealDamage = DMG_TRAP;
        Vector2[] points = {new Vector2(0, 0), new Vector2(0, 16), new Vector2(16, 16), new Vector2(16, 0)};
        this.shape.set(points);
        this.setSprite();
    }

    @Override
    public void configureBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(getPosition());

        this.setBodyDef(bodyDef);
    }

    @Override
    public void setSprite() {
        this.sprite = new Sprite(TextureFactory.getTrapTexture());
    }

    @Override
    public UserData getUserData() {
        return UserData.TRAP;
    }

    public int getDealDamage() {
        return dealDamage;
    }
}
