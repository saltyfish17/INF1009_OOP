package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.ScoreManager;

public class EarthScene implements Screen {
    private SceneManager sceneManager;
    private SimulationLifecycleManagement simulation;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Music bgMusic;
    private Texture playerTexture, bulletTexture, asteroidTexture, heartTexture, healthPowerUpTexture, backgroundTexture;
    private Sound shootingSound;
    private BitmapFont font;
    private boolean isPaused;

    public EarthScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        isPaused = false;

        // Load textures and sound
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        playerTexture = new Texture("player.png");
        bulletTexture = new Texture("bullet.png");
        asteroidTexture = new Texture("asteroid.png");
        heartTexture = new Texture("heart.png");
        healthPowerUpTexture = new Texture("health_powerup.png");
        shootingSound = Gdx.audio.newSound(Gdx.files.internal("pew.mp3"));
        backgroundTexture = new Texture("earth_bg.png");


        // Implementing SimulationLifecycleManagement
        float volume = 0.3f;
        simulation = new SimulationLifecycleManagement(playerTexture, bulletTexture, asteroidTexture,
                heartTexture, healthPowerUpTexture,
                shootingSound, volume, sceneManager);

        //Camera and Viewport configuration
        camera = new OrthographicCamera();
        viewport = new StretchViewport(800, 600, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        // Background music
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("earth.mp3"));
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.3f);
        bgMusic.play();
    }

    @Override
    public void show() {
        // This method will be called when this screen becomes the current screen.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        viewport.apply();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

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
        batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        font.draw(batch, "Score: " + ScoreManager.getScore(), Gdx.graphics.getWidth() / 2f + 320f, Gdx.graphics.getHeight() / 4f + 450f);
        if (!isPaused) {
            simulation.update(delta); // Update game logic only when not paused
        }
        simulation.render(batch); // Render the current state always
        if (isPaused) {
            font.draw(batch, "Game Paused. Press ESC to resume.", Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f);
            font.draw(batch, "Current Score: " + ScoreManager.getScore(), Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f + 50);
        }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }


    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        // This method will be called when the game resumes from pause.
    }

    @Override
    public void hide() {
        // This method will be called when this screen is no longer the current screen.
    }

    @Override
    public void dispose() {
        batch.dispose();
        bgMusic.dispose();
        simulation.dispose();
        font.dispose();
        backgroundTexture.dispose();
        // Dispose of other resources if necessary.
    }
}
