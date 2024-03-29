package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.engine.iGameScene;
import com.mygdx.engine.SceneManager;

public class CutSceneJupiter implements iGameScene {
    private SceneManager sceneManager;
    private BitmapFont font;
    private String fullText1 = "You are approaching Jupiter, 2nd planet closest to Earth.\n5th planet in the solar system.\nDestroy as many enemy as possible!";
    private String[] words;
    private int currentWordIndex = 0;
    private StringBuilder displayedText = new StringBuilder();
    private Music bgMusic;

    private boolean isTextAnimationComplete = false;

    public CutSceneJupiter(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    private void startTextAnimation() {
        float delay = 0.1f; // Delay in seconds between words

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (currentWordIndex < words.length) {
                    displayedText.append(words[currentWordIndex]).append(" ");
                    currentWordIndex++;
                } else {
                    isTextAnimationComplete = true;
                    this.cancel(); // Cancel the timer when all words are displayed
                }
            }
        }, 0, delay); // 0 initial delay, repeat every 'delay' seconds
    }

    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(1, 1, 1, 1); // White color

        words = fullText1.split(" ");

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("loading.mp3"));
        bgMusic.setLooping(false);
        bgMusic.play();

        startTextAnimation();

    }


    @Override
    public void update(float dt) {
        if (isTextAnimationComplete) {
            // Only schedule the scene change once
            isTextAnimationComplete = false;

            // Schedule a new task to change the scene after a delay
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    sceneManager.setScene(new JupiterScene(sceneManager));
                }
            }, 2); // Delay in seconds, e.g., 2 seconds
        }
    }
    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, displayedText.toString(), 100, 300); // Adjust position as needed
        batch.end();
    }
    @Override
    public void dispose() {
        font.dispose();
        bgMusic.dispose();

    }

}