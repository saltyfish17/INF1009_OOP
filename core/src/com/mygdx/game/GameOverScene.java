package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.GameScene;
import com.mygdx.engine.SceneManager;

public class GameOverScene implements GameScene {
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private BitmapFont font;
    private String[] menuItems = {"Restart", "Return to Main Menu", "Quit"};
    private int currentSelection = 0;
    private Texture gameOver;
    private Music bgMusic;
    private Sound chooseSound;


    public GameOverScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(1, 1, 1, 1); // White color

        batch = new SpriteBatch();
        gameOver = new Texture("gameover.jpg");

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3"));
        bgMusic.setLooping(false);
        bgMusic.play();

        chooseSound = Gdx.audio.newSound(Gdx.files.internal("menu_choose.mp3"));
    }

    private void menuOptions() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            chooseSound.play(0.3f);
            currentSelection = (currentSelection - 1 + menuItems.length) % menuItems.length;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            chooseSound.play(0.3f);
            currentSelection = (currentSelection + 1) % menuItems.length;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

            // Handle selection
            switch (currentSelection) {
                case 0: // Restart
                    sceneManager.setScene(new EarthScene(sceneManager));
                    break;
                case 1: // Return to MainMenu
                    sceneManager.setScene(new MainMenuScene(sceneManager));
                    break;
                case 2: // Quit
                    Gdx.app.exit();
                    break;
            }
        }
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuOptions();

        float logoWidth = 300;  // Set the desired width
        float logoHeight = 300; // Set the desired height

        batch.begin();
        batch.draw(gameOver,0,250);


        for (int i = 0; i < menuItems.length; i++) {
            if (i == currentSelection) {
                font.setColor(Color.RED); // Highlight selected item
            } else {
                font.setColor(Color.WHITE); // Other items are white
            }
            font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2f - 60, 170 - i * 20); // Adjust positioning as needed
            }
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        gameOver.dispose();
        bgMusic.dispose();
        chooseSound.dispose();

    }

}