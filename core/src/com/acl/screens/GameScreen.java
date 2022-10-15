package com.acl.screens;

import com.acl.ObstACLTower;
import com.acl.Tower;
import com.acl.listeners.KeyboardListener;
import com.acl.managers.Text;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {

    final private ObstACLTower obstACLTower;
    private final KeyboardListener keyboardListener = new KeyboardListener();
    private final Text text;

    public GameScreen(ObstACLTower obstACLTower) {
        this.obstACLTower = obstACLTower;
        this.obstACLTower.setTower(new Tower());
        this.text = new Text();

        Gdx.input.setInputProcessor(this.keyboardListener);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.keyboardListener);
    }

    @Override
    public void render(float delta) {
        // We clean screen
        ScreenUtils.clear(0, 0, 0, 1);
        //We display things
        obstACLTower.batch.begin();
        obstACLTower.batch.draw(TextureFactory.getBackTexture(), 150, 150);
        obstACLTower.batch.end();
        obstACLTower.batch.setProjectionMatrix(obstACLTower.getCamera().combined);
        // We move player
        this.obstACLTower.getTower().getPlayer().setMotion(this.keyboardListener.getMotion());
        // World step definition
        obstACLTower.getTower().getWorld().step(Gdx.graphics.getDeltaTime(), 6, 2);
        // We display score
        text.displayScore("Score = " + obstACLTower.getScore());
        // Print coordinate
        System.out.println(obstACLTower.getTower().getPlayer().getBody().getPosition());
    }


    @Override
    public void dispose() {

    }
}
