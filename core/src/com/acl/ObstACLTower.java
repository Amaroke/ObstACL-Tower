package com.acl;

import com.acl.managers.SoundsManager;
import com.acl.screens.GameScreen;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObstACLTower extends Game implements ApplicationListener {

    private Tower tower;
    public SpriteBatch batch;
    private OrthographicCamera camera;
    private SoundsManager soundsManager;

    @Override
    public void create() {
        setScreen(new GameScreen(this));
        batch = new SpriteBatch();
        this.soundsManager = new SoundsManager();
        this.soundsManager.soundBackground();
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