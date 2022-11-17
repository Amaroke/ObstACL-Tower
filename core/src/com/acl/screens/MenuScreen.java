package com.acl.screens;

import com.acl.ObstACLTower;
import com.acl.listeners.KeyboardListener;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen extends ScreenAdapter {
    private final ObstACLTower obstACLTower;
    private final Texture background;
    private final KeyboardListener keyboardListener;
    private final GameScreen gameScreen;
    private final OrthographicCamera camera;

    public MenuScreen(ObstACLTower obstACLTower, KeyboardListener keyboardListener, GameScreen gameScreen) {
        this.obstACLTower = obstACLTower;
        this.keyboardListener = keyboardListener;
        this.background = TextureFactory.getMenuBackgroundTexture();

        //Handling the camera change
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 260, 150);
        Gdx.input.setInputProcessor(this.keyboardListener);
        this.gameScreen = gameScreen;
    }

    @Override
    public void show() {
        obstACLTower.setCamera(camera);
        obstACLTower.getCamera().update();
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
        obstACLTower.getBatch().draw(this.background, 0, 16, camera.viewportWidth, camera.viewportHeight);
        obstACLTower.getBatch().end();
    }

}
