package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.GameScene;
import com.mygdx.engine.SceneManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.engine.ScoreManager;

import java.util.Arrays;

public class MainMenuScene implements GameScene {
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private BitmapFont font;
    private String[] menuItems = {"Start", "Level Select", "Credits", "Quit"};
    private int currentSelection = 0;
    private Texture logo, background;
    private Music bgMusic;
    private Sound selectSound;
    private Sound chooseSound;
    private boolean debugMenuVisible;

    public MainMenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        debugMenuVisible = false;
    }

    private void handleMainMenuInput() {
        if (!debugMenuVisible) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                chooseSound.play(0.3f);
                currentSelection = (currentSelection - 1 + menuItems.length) % menuItems.length;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                chooseSound.play(0.3f);
                currentSelection = (currentSelection + 1) % menuItems.length;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                selectMainMenuOption();
            }
        }
    }

      private void selectMainMenuOption() {
        selectSound.play(1);
        switch (currentSelection) {
            case 0: // Start
                ScoreManager.resetScore();
                sceneManager.setScene(new CutSceneEarth(sceneManager));
                break;
            case 1: // Level Select
                ScoreManager.resetScore();
                sceneManager.pushScene(new LevelSelect(sceneManager));
                break;
            case 2: // Credit
                ScoreManager.resetScore();
                sceneManager.pushScene(new CreditScene(sceneManager));
                break;
            case 3: // Quit
                Gdx.app.exit();
                break;
        }
    }

    private void renderMainMenu(SpriteBatch batch) {
        for (int i = 0; i < menuItems.length; i++) {
            if (i == currentSelection) {
                font.setColor(Color.RED); // Highlight selected item
            } else {
                font.setColor(Color.WHITE); // Other items are white
            }
            font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2f - 20, 170 - i * 20);
        }
    }

    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(1, 1, 1, 1); // White color

        batch = new SpriteBatch();

        logo = new Texture("logo.png");
        background = new Texture("menu_bg.png");

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
        bgMusic.setLooping(true);
        bgMusic.play();

        chooseSound = Gdx.audio.newSound(Gdx.files.internal("menu_choose.mp3"));

        selectSound = Gdx.audio.newSound(Gdx.files.internal("menu_select.mp3"));

    }

    @Override
    public void update(float dt) {
        handleMainMenuInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float logoWidth = 300;  // Set the desired width
        float logoHeight = 300; // Set the desired height

        batch.begin();
        batch.draw(background,0,0);
        batch.draw(logo, 180,180,logoWidth,logoHeight);

        renderMainMenu(batch);

        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        logo.dispose();
        background.dispose();
        bgMusic.dispose();
        selectSound.dispose();
        chooseSound.dispose();
    }

}