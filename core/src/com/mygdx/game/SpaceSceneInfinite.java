package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.SimulationLifecycleManagement;
import com.mygdx.engine.iGameScene;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.ScoreManager;

public class SpaceSceneInfinite implements iGameScene {
    private SimulationLifecycleManagement simulation;
    private SceneManager sceneManager;
    private BitmapFont font;
    private SpriteBatch batch;
    private Texture playerTexture, bulletTexture, asteroidTexture, heartTexture, healthPowerUpTexture, backgroundTexture;
    private Music bgMusic;
    private Sound shootingSound;
    private boolean isPaused;

    public SpaceSceneInfinite(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        isPaused = false;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        playerTexture = new Texture("player.png");
        bulletTexture = new Texture("bullet.png");
        asteroidTexture = new Texture("asteroid.png");
        heartTexture = new Texture("heart.png");
        healthPowerUpTexture = new Texture("health_powerup.png");
        backgroundTexture = new Texture("space_bg.png");

        font = new BitmapFont();

        shootingSound = Gdx.audio.newSound(Gdx.files.internal("pew.mp3"));

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("jupiter.mp3"));
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.3f);
        bgMusic.play();

        float volume = 0.3f;
        simulation = new SimulationLifecycleManagement(playerTexture, bulletTexture, asteroidTexture,
                heartTexture, healthPowerUpTexture,
                shootingSound, volume, sceneManager);

    }
    @Override
    public void update(float dt) {
        if (!isPaused) {
            simulation.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isPaused = !isPaused;
            if (isPaused) {
                bgMusic.pause(); // Pause the music
            } else {
                bgMusic.play(); // Resume the music
            }
        }
        if (isPaused) {
            // Allow return to main menu only when the game is paused
            if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
                sceneManager.setScene(new MainMenuScene(sceneManager)); // Return to main menu
            }
        }

        batch.begin();
        batch.draw(backgroundTexture, 0,0);

        simulation.render(batch);
        if (isPaused) {
            font.draw(batch, "Game Paused. Press 'ESC' to resume.", 200, 230);
            font.draw(batch, "Current Score: " + ScoreManager.getScore(), 260, 270);
            font.draw(batch, "Press 'M' to return to Main Menu.", 220, 210);
        }
        else {
            font.draw(batch,"Score:" + ScoreManager.getScore() , 460, 430);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerTexture.dispose();
        bulletTexture.dispose();
        asteroidTexture.dispose();
        heartTexture.dispose();
        bulletTexture.dispose();
        asteroidTexture.dispose();
        heartTexture.dispose();
        healthPowerUpTexture.dispose();
        backgroundTexture.dispose();
        bgMusic.dispose();
        simulation.dispose();
        sceneManager.dispose();
        font.dispose();
        shootingSound.dispose();
    }

}
