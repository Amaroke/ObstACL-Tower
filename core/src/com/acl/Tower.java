package com.acl;

import com.acl.datas.Player;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Tower {

    private World world;
    private Player player;

    public Tower() {
        createTower();
    }
    private void createTower() {
        world = new World(new Vector2(0, 0), true);
        player = new Player(new Vector2(0, 0));
        player.configureBodyDef();
        player.createBody(getWorld());
        player.setFixture();
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }


}
