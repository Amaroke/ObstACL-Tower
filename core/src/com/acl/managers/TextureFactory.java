package com.acl.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    private TextureFactory() {

    }

    public static TextureFactory getInstance() {
        if (instance == null) {
            instance = new TextureFactory();
        }

        return instance;
    }


    private final static Texture backTexture = new Texture(Gdx.files.internal("floor.png"));

    public static Texture getBackTexture() {
        return backTexture;
    }


}
