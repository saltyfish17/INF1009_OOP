package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.SceneManager;

public class Spacedefender extends Game {
    private SpriteBatch batch;
    private SceneManager sceneManager;
    private BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager(this);
        sceneManager.setScene(new MainMenuScene(sceneManager)); // Start with the main menu
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
