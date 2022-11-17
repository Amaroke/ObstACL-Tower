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
    private final Texture backButton;
    private final Texture startButton;
    private final Texture quitButton;
    private final Texture selectArrow;
    private final KeyboardListener keyboardListener;
    private final GameScreen gameScreen;
    private final OrthographicCamera camera;

    public MenuScreen(ObstACLTower obstACLTower, KeyboardListener keyboardListener, GameScreen gameScreen) {
        this.obstACLTower = obstACLTower;
        this.background = TextureFactory.getMenuBackgroundTexture();
        this.backButton = TextureFactory.getMenuBackButton();
        this.startButton = TextureFactory.getMenuStartButton();
        this.quitButton = TextureFactory.getMenuExitButton();
        this.selectArrow = TextureFactory.getMenuSelectArrow();

        //Handling the camera change
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 260, 150);
        //this.keyboardListener = new MenuKeyboardListener();
        Gdx.input.setInputProcessor(keyboardListener);
        this.keyboardListener = keyboardListener;
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
        if (keyboardListener.isConfirmed()) {
            switch (keyboardListener.getCurrentChoice()) {
                case 0 -> {
                    keyboardListener.setMenuOpen(false);
                    keyboardListener.setConfirmed(false);
                    keyboardListener.setCurrentChoice(0);
                    return;
                }
                case 1 -> {
                    keyboardListener.setMenuOpen(false);
                    keyboardListener.setConfirmed(false);
                    keyboardListener.setCurrentChoice(0);
                    obstACLTower.getTower().getWorld().dispose();
                    obstACLTower.getTower().createTower(1, 0);
                    return;
                }
                case 2 -> Gdx.app.exit();
            }
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        obstACLTower.getBatch().begin();
        obstACLTower.getBatch().draw(this.background, 0, 16, camera.viewportWidth, camera.viewportHeight);
        obstACLTower.getBatch().draw(this.backButton, camera.viewportWidth/2 - 20, 16 + (camera.viewportHeight / 4) * 3, 40, 10);
        obstACLTower.getBatch().draw(this.startButton, camera.viewportWidth/2 - 20, 16 + (camera.viewportHeight / 4) * 2, 40, 10);
        obstACLTower.getBatch().draw(this.quitButton, camera.viewportWidth/2 - 20, 16 + (camera.viewportHeight / 4), 40, 10);

        //Displaying the arrow
        float y_arrow = switch (keyboardListener.getCurrentChoice()) {
            case 0 -> (camera.viewportHeight / 4) * 3;
            case 1 -> (camera.viewportHeight / 4) * 2;
            case 2 -> (camera.viewportHeight / 4);
            default -> 0f;
        };
        obstACLTower.getBatch().draw(this.selectArrow, camera.viewportWidth/2 - 30, 16 + y_arrow, 10, 10);

        obstACLTower.getBatch().end();
    }

}
