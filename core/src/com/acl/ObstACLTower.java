package com.acl;

import com.acl.screens.GameScreen;
import com.badlogic.gdx.Game;

public class ObstACLTower extends Game {

    private Tower tower;
    private int score;

    @Override
    public void create() {
        setScreen(new GameScreen(this));
        score = 0;
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

    public int getScore() {
        return this.score;
    }
}