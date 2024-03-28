package com.mygdx.engine;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputOutputManagement implements InputProcessor {
    private boolean spaceKeyWasPressed = false;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE && !spaceKeyWasPressed) {
            spaceKeyWasPressed = true;
            return true;
        } else if (keycode == Input.Keys.A) {
            // Handle A key
            return true;
        } else if (keycode == Input.Keys.D) {
            // Handle D key
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            spaceKeyWasPressed = false;
            return true;
        } else if (keycode == Input.Keys.A) {
            // Handle A key release
            return true;
        } else if (keycode == Input.Keys.D) {
            // Handle D key release
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
        // Handle touch down events
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Handle touch up events
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Handle touch dragged events
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // Handle mouse moved events
        /*System.out.println("Mouse moved to: " + screenX + ", " + screenY); */
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // Handle scroll events
        return false;
    }

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method 
		return false;
	}
}