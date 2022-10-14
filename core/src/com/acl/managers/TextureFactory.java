package com.acl.managers;

public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    private TextureFactory() {

    }

    public static TextureFactory getInstance(){
        if (instance == null)
        {
            instance = new TextureFactory();
        }

        return instance;
    }

    /*
    private final static Texture backTexture = new Texture(Gdx.files.internal("xxxxxx"));

    public static Texture getBackTexture() {
        return backTexture;
    }
     */


}
