package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.iGameScene;
import com.mygdx.engine.SceneManager;

public class CreditScene implements iGameScene {
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private BitmapFont font;
    private String[] creditsText = {
            "Game Developed By:",
            "MUHAMMAD FIRDAUZ 2302706",
            "LIM XUAN YU 2303457",
            "MUHAMMAD AKID 2302868",
            "TIMOTHY TAY KAI JIE 2303291",
            "FAIZUL ISLAM FAHIM 2302684",
            "\n",
            "Music by:",
            "Aphex Twin",
            "Flying Lotus",
            "\n\n\n",
            "Thank you for playing!"
    };
    private float scrollY = 0;
    private float scrollSpeed = 100;

    private float totalCreditsHeight;


    public CreditScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    private float getTotalCreditsHeight() {
        return creditsText.length * font.getLineHeight(); // Plus additional spacing if needed
    }

    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(1, 1, 1, 1); // White color

        batch = new SpriteBatch();

        totalCreditsHeight = getTotalCreditsHeight();
    }


    @Override
    public void update(float dt) {
        scrollY += scrollSpeed * dt;
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        float y = scrollY;
        for (String line : creditsText) {
            font.draw(batch, line, 100, y); // Adjust x-position as needed
            y -= font.getLineHeight();
        }
        if (scrollY > Gdx.graphics.getHeight() + totalCreditsHeight) {
            sceneManager.popScene(); // Go back to main menu
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            sceneManager.popScene();
        }
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}