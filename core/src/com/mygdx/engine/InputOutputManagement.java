package com.mygdx.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Player;

public class InputOutputManagement implements InputProcessor {
    private Player player;
    private EntityManagerNew entityManager; // Add this variable
    private Boolean spaceKeyWasPressed = false;

    public InputOutputManagement(Player player, EntityManagerNew entityManager) { // Modify the constructor
        this.player = player;
        this.entityManager = entityManager; // Initialize the entityManagement variable
    }

    // InputProcessor methods
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE && !spaceKeyWasPressed) {
            spaceKeyWasPressed = true;
            
            return true;
        }
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
    	switch (keycode) {
        case Input.Keys.LEFT:
            // Code to handle when the UP arrow key is released
            // Gdx.app.log("Key Released", "LEFT arrow key released");
            return true; // Event handled
        case Input.Keys.RIGHT:
            // Code to handle when the DOWN arrow key is released
           // Gdx.app.log("Key Released", "RIGHT arrow key released");
            return true; // Event handled
        case Input.Keys.UP:
            // Code to handle when the UP arrow key is released
            // Gdx.app.log("Key Released", "UP arrow key released");
            return true; // Event handled
        case Input.Keys.DOWN:
            // Code to handle when the DOWN arrow key is released
            // Gdx.app.log("Key Released", "DOWN arrow key released");
            return true; // Event handled
        case Input.Keys.SPACE:
        // Code to handle when the DOWN arrow key is released
        	spaceKeyWasPressed = false;
        // Gdx.app.log("Key Released", "SPACE arrow key released");
        	return true; // Event handled
        // Add more cases for other keys as needed
       
    }
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
