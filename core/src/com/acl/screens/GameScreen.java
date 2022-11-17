package com.acl.screens;

import com.acl.ObstACLTower;
import com.acl.Tower;
import com.acl.datas.elements.Element;
import com.acl.datas.elements.monsters.Guardian;
import com.acl.datas.elements.monsters.Lich;
import com.acl.datas.elements.weapons.Weapon;
import com.acl.enums.Direction;
import com.acl.listeners.KeyboardListener;
import com.acl.managers.Text;
import com.acl.managers.TextureFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {

    final private ObstACLTower obstACLTower;
    private final KeyboardListener keyboardListener = new KeyboardListener();
    private final Text text;
    private int timeBetweenRender = 0;

    private int weaponCooldown = 0;
    private int pauseTime = 100;
    private final OrthographicCamera camera;

    public GameScreen(ObstACLTower obstACLTower) {
        this.obstACLTower = obstACLTower;
        this.obstACLTower.setTower(new Tower());
        this.text = new Text();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16 * 16, 9 * 16);
        camera.position.y += 16;
        Gdx.input.setInputProcessor(this.keyboardListener);
    }

    @Override
    public void show() {
        obstACLTower.setCamera(camera);
        obstACLTower.getCamera().update();
        Gdx.input.setInputProcessor(this.keyboardListener);
    }

    @Override
    public void render(float delta) {
        if (keyboardListener.isMenuOpen()) {
            obstACLTower.setScreen(new MenuScreen(obstACLTower, keyboardListener, this));
            return;
        }
        if (!this.obstACLTower.getTower().isGamePaused()) {
            this.obstACLTower.getTower().update();
            // We clean screen.
            ScreenUtils.clear(0, 0, 0, 1);
            // We display things.
            obstACLTower.batch.begin();
            timeBetweenRender += 1;
            weaponCooldown--;
            if (keyboardListener.isDebug()) {
                new Box2DDebugRenderer().render(obstACLTower.getTower().getWorld(), obstACLTower.getCamera().combined);
            } else {
                obstACLTower.batch.draw(TextureFactory.getBackTexture(), 0, 0);
            }
            if (!keyboardListener.isDebug()) {
                for (Element e : obstACLTower.getTower().getElements()) {
                    if (e.isGuardian()) {
                        TextureRegion t = new TextureRegion((((Guardian) e).getAnimationNorth().getKeyFrame(timeBetweenRender / 10f, true)));
                        switch (((Guardian) e).getDirection()) {
                            case SOUTH ->
                                    t = new TextureRegion((((Guardian) e).getAnimationSouth().getKeyFrame(timeBetweenRender / 10f, true)));
                            case EAST ->
                                    t = new TextureRegion((((Guardian) e).getAnimationEast().getKeyFrame(timeBetweenRender / 10f, true)));
                            case WEST ->
                                    t = new TextureRegion((((Guardian) e).getAnimationWest().getKeyFrame(timeBetweenRender / 10f, true)));

                        }
                        obstACLTower.batch.draw(t, e.getBody().getPosition().x, e.getBody().getPosition().y, 0, 0, 14f, 14f, 1f, 1f, 0);

                    } else if (e.isLich()) {
                        TextureRegion t = new TextureRegion((((Lich) e).getAnimationNorth().getKeyFrame(timeBetweenRender / 10f, true)));
                        switch (((Lich) e).getDirection()) {
                            case SOUTH ->
                                    t = new TextureRegion((((Lich) e).getAnimationSouth().getKeyFrame(timeBetweenRender / 10f, true)));
                            case EAST ->
                                    t = new TextureRegion((((Lich) e).getAnimationEast().getKeyFrame(timeBetweenRender / 10f, true)));
                            case WEST ->
                                    t = new TextureRegion((((Lich) e).getAnimationWest().getKeyFrame(timeBetweenRender / 10f, true)));

                        }
                        obstACLTower.batch.draw(t, e.getBody().getPosition().x, e.getBody().getPosition().y, 0, 0, 14f, 14f, 1f, 1f, 0);

                    } else {
                        e.setSprite();
                        obstACLTower.batch.draw(e.getSprite(), e.getBody().getPosition().x, e.getBody().getPosition().y);
                    }
                }
                for (Weapon w : obstACLTower.getTower().getWeapons()) {
                    w.update();
                    w.setSprite();
                    TextureRegion t;
                    switch (obstACLTower.getTower().getPlayer().getWeaponType()) {
                        case FIREBALL -> {
                            t = new TextureRegion(w.getAnimation().getKeyFrame(timeBetweenRender, true));
                            switch (w.getDirection()) {
                                case NORTH ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x, w.getBody().getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 0);
                                case SOUTH ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x + 16, w.getBody().getPosition().y + 16, 0, 0, 16f, 16f, 1f, 1f, 180);
                                case EAST ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x, w.getBody().getPosition().y + 16, 0, 0, 16f, 16f, 1f, 1f, 270);
                                case WEST ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x + 16, w.getBody().getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 90);
                            }
                        }
                        case SWORD -> {
                            t = new TextureRegion(w.getSprite());
                            switch (w.getDirection()) {
                                case NORTH ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x + 16, w.getBody().getPosition().y + 16, 0, 0, 16f, 16f, 1f, 1f, 180);
                                case SOUTH ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x, w.getBody().getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 0);
                                case EAST ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x + 16, w.getBody().getPosition().y, 0, 0, 16f, 16f, 1f, 1f, 90);
                                case WEST ->
                                        obstACLTower.batch.draw(t, w.getBody().getPosition().x, w.getBody().getPosition().y + 16, 0, 0, 16f, 16f, 1f, 1f, 270);

                            }
                        }
                    }
                }

            }
            // We get weapon use
            if (this.keyboardListener.getUseWeapon()) {
                if (weaponCooldown <= 0) {
                    this.weaponCooldown = 30; //TODO A INCLURE DANS LES CONSTANTES
                    this.obstACLTower.getTower().createWeapon();
                }
            }
            if (!keyboardListener.isDebug()) {
                obstACLTower.getTower().getPlayer().draw(obstACLTower.batch);
            }
            obstACLTower.batch.end();
            obstACLTower.batch.setProjectionMatrix(obstACLTower.getCamera().combined);
            // We move player
            this.obstACLTower.getTower().getPlayer().setMotion(this.keyboardListener.getMotion());
            this.obstACLTower.getTower().getPlayer().setDirection(this.keyboardListener.getDirection() != null ? this.keyboardListener.getDirection() : Direction.NORTH);
            // World step definition
            obstACLTower.getTower().getWorld().step(Gdx.graphics.getDeltaTime(), 6, 2);
            // We display score
            text.displayScore("Score = " + obstACLTower.getTower().getScore());
            text.displayHP("Health = " + obstACLTower.getTower().getPlayer().getHp());
        } else {
            if (this.obstACLTower.getTower().isVictory()) {
                text.displayStageClear("Stage Clear");
            }
            if (this.obstACLTower.getTower().isDefeat()) {
                text.displayDie("You lose \n Your score = " + obstACLTower.getTower().getScore());
            }
            if (pauseTime == 0) {
                Tower tower = this.obstACLTower.getTower();
                this.pauseTime = 100;
                tower.getWorld().dispose();

                if (tower.isVictory()) {
                    tower.createTower(tower.getNbLevel() == 3 ? 1 : tower.getNbLevel() + 1, tower.getScore());
                } else {
                    tower.createTower(1, 0);
                }
            }
            pauseTime--;
        }
    }
}
