package com.mygdx.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class SceneManager {
    private Game game;

    public SceneManager(Game game) {
        this.game = game;
    }

    public void setScene(Screen screen) {
        if (game.getScreen() != null) {
            game.getScreen().dispose();
        }
        game.setScreen(screen);
    }

    public void resize(int width, int height) {
        if (game.getScreen() != null) {
            game.getScreen().resize(width, height);
        }
    }
}
