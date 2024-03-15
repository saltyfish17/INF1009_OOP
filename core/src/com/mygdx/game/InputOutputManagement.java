package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputOutputManagement implements InputProcessor {
    private Player player;
    private EntityManagement entityManagement; // Add this variable

    public InputOutputManagement(Player player, EntityManagement entityManagement) { // Modify the constructor
        this.player = player;
        this.entityManagement = entityManagement; // Initialize the entityManagement variable
    }

    // InputProcessor methods
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            entityManagement.addBullet(player.shoot());
            return true;
        }
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        // Handle key up events
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // Handle key typed events
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
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // Handle scroll events
        return false;
    }

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
}
