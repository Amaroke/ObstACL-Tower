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
    private final static Texture chevalierTexture = new Texture(Gdx.files.internal("chevalier_cheri.png"));
    private final static Texture chestTexture = new Texture(Gdx.files.internal("chest.png"));
    private final static Texture breakableTexture = new Texture(Gdx.files.internal("barrel.png"));
    private final static Texture wallTexture = new Texture(Gdx.files.internal("wall.png"));
    private final static Texture stairTexture = new Texture(Gdx.files.internal("stair.png"));
    private final static Texture trapTexture = new Texture(Gdx.files.internal("trap.png"));


    public static Texture getBackTexture() {
        return backTexture;
    }

    public static Texture getBreakableTexture() {
        return breakableTexture;
    }

    public static Texture getChestTexture() {
        return chestTexture;
    }

    public static Texture getWallTexture() {
        return wallTexture;
    }

    public static Texture getTrapTexture() {
        return trapTexture;
    }

    public static Texture getStairTexture() {
        return stairTexture;
    }

    public static Texture getChevalierTexture() {
        return chevalierTexture;
    }

}
