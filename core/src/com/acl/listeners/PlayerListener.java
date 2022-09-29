package com.acl.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class PlayerListener implements InputProcessor {

    private final Vector2 motion = new Vector2(0f, 0f);

    public Vector2 getMotion() {
        return motion;
    }

    @Override
    public boolean keyDown(int keycode) {
        // When pressing the escape key, the menu opens
        if (keycode == Input.Keys.ESCAPE) {
            //TODO menu display.
            return true;
        }
        // When pressing the UP, Z or W keys, the character goes up.
        if (keycode == Input.Keys.UP || keycode == Input.Keys.Z || keycode == Input.Keys.W) {
            motion.y = 1f;
            return true;
        }
        // When pressing the LEFT, Q or A keys, the character goes left.
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.Q || keycode == Input.Keys.A) {
            motion.x = -1f;
            return true;
        }
        // When pressing the RIGHT or D keys, the character goes right.
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            motion.x = 1f;
            return true;
        }
        // When pressing the DOWN or S keys, the character goes down.
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            motion.y = -1f;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // When releasing the UP, Z, W, DOWN or S keys, the character stops moving vertically.
        if (keycode == Input.Keys.UP || keycode == Input.Keys.Z || keycode == Input.Keys.W ||keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            motion.y = 0f;
            return true;
        }
        // When pressing the LEFT, Q, A, RIGHT or D keys, the character stops moving horizontally.
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.Q || keycode == Input.Keys.A || keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
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
}
