package com.acl.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    public static TextureFactory getInstance() {
        if (instance == null) {
            instance = new TextureFactory();
        }

        return instance;
    }

    private final static Texture backTexture = new Texture(Gdx.files.internal("floor.png"));
    private final static Texture chevalierTexture = new Texture(Gdx.files.internal("knight_cheri.png"));
    private final static Texture chestTexture = new Texture(Gdx.files.internal("chest.png"));
    private final static Texture breakableTexture = new Texture(Gdx.files.internal("barrel.png"));
    private final static Texture wallTexture = new Texture(Gdx.files.internal("wall.png"));
    private final static Texture stairTexture = new Texture(Gdx.files.internal("stair.png"));
    private final static Texture trapTexture = new Texture(Gdx.files.internal("trap.png"));
    private final static Texture fireBallTexture = new Texture((Gdx.files.internal("fireBall.png")));
    private final static Texture swordTexture = new Texture((Gdx.files.internal("sword.png")));
    private final static Texture slimeTexture = new Texture((Gdx.files.internal("slime.png")));
    private final static Texture guardianTexture = new Texture((Gdx.files.internal("guardian.png")));
    private final static Texture menuBackgroundTexture = new Texture((Gdx.files.internal("obstACL_tower.png")));
    private final static Texture menuBackButton = new Texture((Gdx.files.internal("button_back.png")));
    private final static Texture menuStartButton = new Texture((Gdx.files.internal("button_start.png")));
    private final static Texture menuQuitButton = new Texture((Gdx.files.internal("button_exit.png")));
    private final static Texture menuSelectArrow = new Texture((Gdx.files.internal("select_arrow.png")));
    private final static Texture lichTexture = new Texture((Gdx.files.internal("dead_lich.png")));
    private final static Texture doorTexture = new Texture((Gdx.files.internal("door.png")));

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

    public static Texture getFireBallTexture() {
        return fireBallTexture;
    }

    public static Texture getSwordTexture() {
        return swordTexture;
    }

    public static Texture getSlimeTexture() {
        return slimeTexture;
    }

    public static Texture getGuardianTexture() {
        return guardianTexture;
    }

    public static Texture getMenuBackgroundTexture() {
        return menuBackgroundTexture;
    }

    public static Texture getMenuBackButton() {
        return menuBackButton;
    }

    public static Texture getMenuStartButton() {
        return menuStartButton;
    }

    public static Texture getMenuExitButton() {
        return menuQuitButton;
    }
    public static Texture getMenuSelectArrow() {
        return menuSelectArrow;
    }
    public static Texture getLichTexture() {
        return lichTexture;
    }

    public static Texture getDoorTexture() {
        return doorTexture;
    }
}
