package com.acl;

import com.acl.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObstACLTower extends Game {

    private Tower tower;
    public SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        setScreen(new GameScreen(this));
        batch = new SpriteBatch();
    }

    @Override
    public void dispose() {

    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower monde) {
        this.tower = monde;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}