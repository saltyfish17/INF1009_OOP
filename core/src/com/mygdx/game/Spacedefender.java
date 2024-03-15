package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.SceneManager;

public class Spacedefender extends ApplicationAdapter {
    private SpriteBatch batch;
    private SceneManager sceneManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager();
        sceneManager.pushScene(new MainMenuScene(sceneManager)); // Start with the main menu
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        sceneManager.update(dt);
        sceneManager.render(batch);
    }


    @Override
    public void dispose() {
        batch.dispose();
        sceneManager.dispose();
    }
}
