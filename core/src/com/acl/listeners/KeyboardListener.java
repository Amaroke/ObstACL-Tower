package com.acl.listeners;

import com.acl.enums.Direction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.Scanner;

public class KeyboardListener implements InputProcessor {

    private final Vector2 motion = new Vector2(0f, 0f);
    private Boolean useWeapon = false;
    private Direction direction;
    private boolean debug = false;
    private boolean fullScreen = false;

    public Vector2 getMotion() {
        return motion;
    }

    public Direction getDirection() {
        return direction;
    }

    public Boolean getUseWeapon() {
        boolean weaponInUse = useWeapon;
        useWeapon = false;
        return weaponInUse;
    }

    @Override
    public boolean keyDown(int keycode) {

        // When pressing the escape key, the menu opens
        if (keycode == Input.Keys.ESCAPE) {
            fullScreen = !fullScreen;
            Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
            if (!fullScreen)
                Gdx.graphics.setWindowedMode(currentMode.width-200, currentMode.height-200);
            else
                Gdx.graphics.setFullscreenMode(currentMode);
            /*
            Scanner input = new Scanner(System.in);

            int choice = -1;
            while (choice == -1) {
                System.out.println("********* MENU PAUSE *********\n");
                System.out.print("1.) Exit \n");
                System.out.print("2.) Resume\n");
                System.out.print("\nEnter Your Menu Choice: ");

                choice = input.nextInt();

                switch (choice) {

                    case 1:
                        Gdx.app.exit();
                        break;

                    case 2:
                        break;
                }
            }*/

        }
        // When pressing the space bar, the weapon of the player is used
        if (keycode == Input.Keys.SPACE) {
            useWeapon = true;

        }
        // When pressing the UP, Z or W keys, the character goes up.
        float playerMovementForce = 30f;
        if (keycode == Input.Keys.UP || keycode == Input.Keys.Z || keycode == Input.Keys.W) {
            motion.y = playerMovementForce;
            direction = Direction.NORTH;
            return true;
        }
        // When pressing the LEFT, Q or A keys, the character goes left.
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.Q || keycode == Input.Keys.A) {
            motion.x = -playerMovementForce;
            direction = Direction.WEST;
            return true;
        }
        // When pressing the RIGHT or D keys, the character goes right.
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            motion.x = playerMovementForce;
            direction = Direction.EAST;
            return true;
        }
        // When pressing the DOWN or S keys, the character goes down.
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            motion.y = -playerMovementForce;
            direction = Direction.SOUTH;
            return true;
        }
        if (keycode == Input.Keys.SHIFT_LEFT) {
            debug = !debug;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // When releasing the UP, Z, W, DOWN or S keys, the character stops moving vertically.
        if (keycode == Input.Keys.UP || keycode == Input.Keys.Z || keycode == Input.Keys.W || keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            motion.y = 0f;
            return true;
        }
        // When pressing the LEFT, Q, A, RIGHT or D keys, the character stops moving horizontally.
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.Q || keycode == Input.Keys.A || keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            motion.x = 0f;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean isDebug() {
        return debug;
    }
}
