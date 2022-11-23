package com.acl.datas.elements;

import com.acl.enums.UserData;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.acl.enums.Constantes.LOOT_BREAKABLE;

public class BreakableObject extends Element {
    public BreakableObject(Vector2 v) {
        super(v);
        this.shape = new PolygonShape();
        Vector2[] points = {new Vector2(4f, 2f), new Vector2(8f, 2f), new Vector2(2f, 4f), new Vector2(10f, 4f), new Vector2(4f, 14f), new Vector2(8f, 14f), new Vector2(2f, 12f), new Vector2(10f, 12f),};
        this.shape.set(points);
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
        this.sprite = new Sprite(TextureFactory.getBreakableTexture());
    }

    @Override
    public UserData getUserData() {
        return UserData.BREAKABLE;
    }

    public int giveLoot() {
        return LOOT_BREAKABLE;
    }
}
