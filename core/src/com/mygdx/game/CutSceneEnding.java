package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.engine.GameScene;
import com.mygdx.engine.SceneManager;

public class CutSceneEnding implements GameScene {
    private SceneManager sceneManager;
    private BitmapFont font;
    private String fullText1 = "Neptune is also hostile, in fact, everything in space is hostile.\nOnly Earth has habitable climate for atmosphere and life.\n Due to climate change in the year 3034, it's very inhospitable.\nYou keep pressing forward in space hoping to find life...\n\nTHE END";
    private String[] words;
    private int currentWordIndex = 0;
    private StringBuilder displayedText = new StringBuilder();
    private Music bgMusic;

    private boolean isTextAnimationComplete = false;

    public CutSceneEnding(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    private void startTextAnimation() {
        float delay = 0.3f; // Delay in seconds between words

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

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("ending.mp3"));
        bgMusic.setLooping(false);
        bgMusic.setVolume(0.3f)
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
                    sceneManager.setScene(new MainMenuScene(sceneManager));
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
