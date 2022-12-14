package com.acl.screens;

import com.acl.ObstACLTower;
import com.acl.Tower;
import com.acl.datas.elements.Element;
import com.acl.datas.elements.Player;
import com.acl.datas.elements.monsters.Guardian;
import com.acl.datas.elements.monsters.Lich;
import com.acl.datas.elements.weapons.Weapon;
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
    private Text text;
    private int timeBetweenRender = 0;
    private final OrthographicCamera camera;
    private boolean fullScreen = false;

    public GameScreen(ObstACLTower obstACLTower) {
        this.obstACLTower = obstACLTower;
        this.obstACLTower.setTower(new Tower());
        this.setText();
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

        // We check if menu is open.
        if (keyboardListener.isMenuOpen()) {
            obstACLTower.setScreen(new MenuScreen(obstACLTower, keyboardListener, this));
            return;
        }

        boolean fullScreen = keyboardListener.isFullScreen();
        if (this.fullScreen != fullScreen) {
            this.setText();
            this.fullScreen = fullScreen;
        }
        // We update the game
        this.obstACLTower.getTower().update(keyboardListener);

        // We clean screen.
        ScreenUtils.clear(0, 0, 0, 1);

        // We display things.
        obstACLTower.batch.begin();
        timeBetweenRender += 1;

        if (keyboardListener.isDebug()) {
            new Box2DDebugRenderer().render(obstACLTower.getTower().getWorld(), obstACLTower.getCamera().combined);
        } else {

            // We draw the background.
            obstACLTower.batch.draw(TextureFactory.getBackTexture(), 0, 0);

            // We draw elements
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
                } else if (e.isItem()) {
                    e.setSprite();
                    obstACLTower.batch.draw(e.getSprite(), e.getBody().getPosition().x, e.getBody().getPosition().y, 0, 0, 7f, 7f, 1f, 1f, 0);
                } else {
                    e.setSprite();
                    obstACLTower.batch.draw(e.getSprite(), e.getBody().getPosition().x, e.getBody().getPosition().y);
                }
            }

            // We draw weapons
            for (Weapon w : obstACLTower.getTower().getWeapons()) {
                w.update();
                w.setSprite();
                TextureRegion t;
                switch (w.getType()) {
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

            Player p = obstACLTower.getTower().getPlayer();
            TextureRegion t;
            if (p.isMoving() && !obstACLTower.getTower().isGamePaused()) {
                t = new TextureRegion(p.getAnimationNorth().getKeyFrame(timeBetweenRender / 10f, true));
                switch (p.getDirection()) {
                    case SOUTH ->
                            t = new TextureRegion(p.getAnimationSouth().getKeyFrame(timeBetweenRender / 10f, true));
                    case EAST -> t = new TextureRegion(p.getAnimationEast().getKeyFrame(timeBetweenRender / 10f, true));
                    case WEST -> t = new TextureRegion(p.getAnimationWest().getKeyFrame(timeBetweenRender / 10f, true));
                }
            } else {
                t = new TextureRegion(p.getAnimationNorth().getKeyFrame(1));
                switch (p.getDirection()) {
                    case SOUTH -> t = new TextureRegion(p.getAnimationSouth().getKeyFrame(1));
                    case EAST -> t = new TextureRegion(p.getAnimationEast().getKeyFrame(1));
                    case WEST -> t = new TextureRegion(p.getAnimationWest().getKeyFrame(1));
                }

            }
            obstACLTower.batch.draw(t, p.getBody().getPosition().x, p.getBody().getPosition().y, 0, 0, 14f, 14f, 1f, 1f, 0);
        }

        // We strop to draw things.
        obstACLTower.batch.end();
        obstACLTower.batch.setProjectionMatrix(obstACLTower.getCamera().combined);

        // World step definition
        obstACLTower.getTower().getWorld().step(Gdx.graphics.getDeltaTime(), 6, 2);

        // We display score
        text.displayScore("Score = " + obstACLTower.getTower().getScore());
        text.displayHP("Health = " + obstACLTower.getTower().getPlayer().getHp());
        if (obstACLTower.getTower().isGamePaused()) {
            if (this.obstACLTower.getTower().isVictory()) {
                text.displayStageClear("Stage Clear");
            }
            if (this.obstACLTower.getTower().isDefeat()) {
                text.displayDie("You lose \n Your score = " + obstACLTower.getTower().getScore());
            }
        }
    }

    public void setText() {
        this.text = new Text();
    }
}

