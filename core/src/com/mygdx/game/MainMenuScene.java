package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input;
import com.mygdx.engine.SceneManager;
import sun.jvm.hotspot.gc.shared.Space;

public class MainMenuScene implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture logo;
    private Music bgMusic;
    private Sound selectSound;
    private Sound chooseSound;
    private float volume;
    private int currentSelection = 0;
    private String[] menuItems = {"Start", "How to play", "Credits", "Quit"};
    private String[] debugOptions = {"Earth Scene", "Space Scene", "Jupiter Scene", "Neptune Scene"};
    private boolean debugMenuVisible;
    private SceneManager sceneManager;


    public MainMenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;


        debugMenuVisible = false;

        batch = new SpriteBatch();

        font = new BitmapFont();
        font.getData().setScale(1.5f);
        font.setColor(1, 1, 1, 1); // White color

        camera = new OrthographicCamera();
        viewport = new StretchViewport(800, 600, camera);
        camera.position.set(400, 300, 0);

        logo = new Texture("logo.png");
    }

    @Override
    public void show() {

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
        bgMusic.setLooping(true);
        bgMusic.play();

        chooseSound = Gdx.audio.newSound(Gdx.files.internal("menu_choose.mp3"));

        selectSound = Gdx.audio.newSound(Gdx.files.internal("menu_select.mp3"));


    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            debugMenuVisible = !debugMenuVisible;
            currentSelection = 0;  // Reset selection when switching menus
        }

        int itemsLength = debugMenuVisible ? debugOptions.length : menuItems.length;

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            chooseSound.play(0.3f);
            currentSelection = (currentSelection - 1 + menuItems.length) % menuItems.length;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            chooseSound.play(0.3f);
            currentSelection = (currentSelection + 1) % menuItems.length;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            selectOption();
        }
    }

    private void selectOption() {
        if (debugMenuVisible) {
            switch (currentSelection) {
                case 0: sceneManager.setScene(new EarthScene(sceneManager)); break;
                case 1: sceneManager.setScene(new SpaceScene(sceneManager)); break;
                case 2: sceneManager.setScene(new JupiterScene(sceneManager)); break;
                case 3: sceneManager.setScene(new NeptuneScene(sceneManager)); break;
                // Add more cases as necessary for other scenes
            }
        } else {
            switch (currentSelection) {
                case 0: // Start game or go to gameplay scene
                    sceneManager.setScene(new EarthScene(sceneManager));
                    break;
                case 1: // Options
                    // Implement options menu logic
                    break;
                case 2: Gdx.app.exit(); // Exit the game
                    break;
            }
        }
    }


    private void renderDebugMenu() {
        for (int i = 0; i < debugOptions.length; i++) {
            if (currentSelection == i) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(batch, debugOptions[i], 100, 200 - 30 * i);
        }
    }

    private void renderMainMenu() {
        for (int i = 0; i < menuItems.length; i++) {
            if (currentSelection == i) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(batch, menuItems[i], 300, 200 - 30 * i);
        }
    }


    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0.2f); // Clear the screen with black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        viewport.apply();

        batch.begin();

        float logoWidth = 300;  // Set the desired width
        float logoHeight = 300; // Set the desired height

        float logoX = (viewport.getWorldWidth() - logoWidth) / 2;
        float logoY = (viewport.getWorldHeight() - logoHeight) / 2 + 100;
        batch.draw(logo, logoX, logoY, logoHeight, logoWidth);

        float textWidth = font.getRegion().getRegionWidth();

        if (debugMenuVisible) {
            renderDebugMenu();
        } else {
            renderMainMenu();
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Handle pausing if necessary
    }

    @Override
    public void resume() {
        // Handle resuming from pause
    }

    @Override
    public void hide() {
        // Cleanup tasks when the screen is no longer visible
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        logo.dispose();
        bgMusic.dispose();
        selectSound.dispose();
    }
}