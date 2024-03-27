package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.GameScene;
import com.mygdx.engine.SceneManager;

public class MainMenuScene implements GameScene {
    private SceneManager sceneManager;
    private BitmapFont font;
    private String message_team = "SPACEDEFENDER\nby Team 4";
    private String[] menuItems = {"Start", "Credits", "Quit"};
    private int currentSelection = 0;

    public MainMenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void create() {
        font = new BitmapFont();
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            currentSelection = (currentSelection - 1 + menuItems.length) % menuItems.length;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            currentSelection = (currentSelection + 1) % menuItems.length;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Handle selection
            switch (currentSelection) {
                case 0: // Start
                    sceneManager.setScene(new GameplayScene(sceneManager));
                    break;
                case 1: // Credits
                    // TODO: Implement transition to Credits scene
                    break;
                case 2: // Quit
                    Gdx.app.exit();
                    break;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, message_team, Gdx.graphics.getWidth() / 2f - message_team.length() * 4 - 20, Gdx.graphics.getHeight() / 2f + 150);
        for (int i = 0; i < menuItems.length; i++) {
            if (i == currentSelection) {
                font.setColor(Color.YELLOW); // Highlight selected item
            } else {
                font.setColor(Color.WHITE); // Other items are white
            }
            font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2f - 50, 300 - i * 30); // Adjust positioning as needed
        }
        batch.end();
    }
    @Override
    public void dispose() {
        font.dispose();
    }
}