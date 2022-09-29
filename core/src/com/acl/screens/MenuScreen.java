package com.acl.screens;

import com.acl.ObstACLTower;
import com.acl.Tower;
import com.acl.listeners.PlayerListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen extends ScreenAdapter {

    final private ObstACLTower obstACLTower;
    private final PlayerListener playerListener = new PlayerListener();

    public MenuScreen(ObstACLTower obstACLTower) {
        this.obstACLTower = obstACLTower;
        this.obstACLTower.setTower(new Tower());
        Gdx.input.setInputProcessor(this.playerListener);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        System.out.println("Caca");
    }

}
