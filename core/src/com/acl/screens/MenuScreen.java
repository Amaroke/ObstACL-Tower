package com.acl.screens;

import com.acl.ObstACLTower;
import com.acl.listeners.KeyboardListener;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen extends ScreenAdapter {
    private final ObstACLTower obstACLTower;
    private final Texture background;
    private final KeyboardListener keyboardListener;
    private final GameScreen gameScreen;

    public MenuScreen(ObstACLTower obstACLTower, KeyboardListener keyboardListener, GameScreen gameScreen) {
        this.obstACLTower = obstACLTower;
        this.keyboardListener = keyboardListener;
        this.background = TextureFactory.getMenuBackgroundTexture();
        Gdx.input.setInputProcessor(this.keyboardListener);
        this.gameScreen = gameScreen;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.keyboardListener);
    }

    @Override
    public void render(float delta) {
        if (!keyboardListener.isMenuOpen()) {
            obstACLTower.setScreen(gameScreen);
            this.dispose();
            return;
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        obstACLTower.getBatch().begin();
        obstACLTower.getBatch().draw(this.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        obstACLTower.getBatch().end();
    }

}
