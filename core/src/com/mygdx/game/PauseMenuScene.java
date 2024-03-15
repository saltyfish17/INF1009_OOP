package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.GameScene;
import com.mygdx.engine.SceneManager;

public class PauseMenuScene implements GameScene {
    private SceneManager sceneManager;
    private BitmapFont font;
    private String message = "Game Paused\nPress any key to resume";

    public PauseMenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void create() {
        font = new BitmapFont();
    }

    @Override
    public void update(float dt) {
        // Check for any key press to resume the game
        for (int i = 0; i < 256; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if (sceneManager.getSceneCount() > 1) {
                    sceneManager.popScene();
                } else {
                    Gdx.app.log("PauseMenuScene", "There's only 1 scene right now");
                }
            }
        }
        /*for (int i = 0; i < 256; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                sceneManager.popScene();
            }
        }*/
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, message, Gdx.graphics.getWidth() / 2f - message.length() * 4 - 20, Gdx.graphics.getHeight() / 2f + 150);
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}