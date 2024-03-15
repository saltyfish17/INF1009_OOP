package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.GameScene;
import com.mygdx.engine.SceneManager;

public class EndScene implements GameScene {
    private SceneManager sceneManager;
    private BitmapFont font;
    private String message = "Game Over!\nPress any key to restart";
    private float delayTimer;

    public EndScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void create() {
        font = new BitmapFont();
        delayTimer = 2.0f;
    }

    @Override
    public void update(float dt) {
        if (delayTimer > 0) {
            delayTimer -= dt;
            return;
        }
        for (int i = 0; i < 256; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                sceneManager.setScene(new MainMenuScene(sceneManager));
                break;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, message, Gdx.graphics.getWidth() / 2f - message.length() * 4, Gdx.graphics.getHeight() / 2f + 20);
        batch.end();
    }

    @Override
    public void dispose() {
        
    }
}