package com.acl.screens;

import com.acl.ObstACLTower;
import com.acl.Tower;
import com.acl.datas.elements.Element;
import com.acl.datas.elements.FireBall;
import com.acl.datas.elements.Weapon;
import com.acl.listeners.KeyboardListener;
import com.acl.enums.Direction;
import com.acl.managers.Text;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {

    final private ObstACLTower obstACLTower;
    private final KeyboardListener keyboardListener = new KeyboardListener();
    private final Text text;
    private int timeBetweenRender = 0;

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
        timeBetweenRender += 1;
        if (keyboardListener.isDebug()) {
            new Box2DDebugRenderer().render(obstACLTower.getTower().getWorld(), obstACLTower.getCamera().combined);
        } else {
            obstACLTower.batch.draw(TextureFactory.getBackTexture(), 0, 0);
        }
        for (Element e : obstACLTower.getTower().getElements()) {
            e.setSprite();
            obstACLTower.batch.draw(e.getSprite(), e.getBody().getPosition().x, e.getBody().getPosition().y);
        }
        for (Weapon w : obstACLTower.getTower().getPlayer().getWeapons()) {
            w.update();
            w.setSprite();
            TextureRegion t;
            switch (obstACLTower.getTower().getPlayer().getWeaponType()) {
                case FIREBALL -> {
                    t = new TextureRegion((((FireBall) w).getAnimation().getKeyFrame(timeBetweenRender, true)));
                    switch (w.getDirection()) {
                        case NORTH ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 0);
                        case SOUTH ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 8, 0, 16f, 16f, 1f, 1f, 180);
                        case EAST ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 16, 0, 16f, 16f, 1f, 1f, 270);
                        case WEST ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 90);

                    }
                }
                case SWORD -> {
                    t = new TextureRegion(TextureFactory.getSwordTexture());
                    switch (w.getDirection()) {
                        case NORTH ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 0);
                        case SOUTH ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 8, 0, 16f, 16f, 1f, 1f, 180);
                        case EAST ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 16, 0, 16f, 16f, 1f, 1f, 270);
                        case WEST ->
                                obstACLTower.batch.draw(t, w.getPosition().x, w.getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 90);

                    }
                }
            }


        }
        // We get weapon use
        this.obstACLTower.getTower().getPlayer().setWeapon(this.keyboardListener.getUseWeapon());
        obstACLTower.getTower().getPlayer().draw(obstACLTower.batch);
        obstACLTower.batch.end();
        obstACLTower.batch.setProjectionMatrix(obstACLTower.getCamera().combined);
        // We move player
        this.obstACLTower.getTower().getPlayer().setMotion(this.keyboardListener.getMotion());
        this.obstACLTower.getTower().getPlayer().setDirection(this.keyboardListener.getDirection() != null ? this.keyboardListener.getDirection() : Direction.NORTH);
        // World step definition
        obstACLTower.getTower().getWorld().step(Gdx.graphics.getDeltaTime(), 6, 2);
        // We display score
        text.displayScore("Score = " + obstACLTower.getScore());
        text.displayVie("Vie = " + obstACLTower.getVie());
        // Print coordinate
        //System.out.println("La position:" + obstACLTower.getTower().getPlayer().getBody().getPosition());
    }


    @Override
    public void dispose() {

    }
}
