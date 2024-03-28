package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.ScoreManager;
import jdk.tools.jmod.Main;

public class GameOver implements Screen {
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture gameOver;

    public GameOver(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        batch = new SpriteBatch();
        font = new BitmapFont();
        gameOver = new Texture("gameover.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        batch.begin();
        batch.draw(gameOver, Gdx.graphics.getWidth() / 2f - gameOver.getWidth() / 2, Gdx.graphics.getHeight() / 2f);
        font.draw(batch, "Score: " + ScoreManager.getScore(), Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f + 50);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            // Restart the game (e.g., go to GameplayScene or reset the current game state)
            sceneManager.setScene(new MainMenuScene(sceneManager));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            // Return to the main menu
            sceneManager.setScene(new MainMenuScene(sceneManager));
        }
    }

    // Implement the other Screen interface methods (resize, show, hide, dispose, etc.)

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        gameOver.dispose();
    }
}
