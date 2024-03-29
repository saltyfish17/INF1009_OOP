package com.mygdx.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

public class SceneManager {
    private Stack<iGameScene> scenes;

    public SceneManager() {
        scenes = new Stack<>();
    }

    public void pushScene(iGameScene scene) {
        scenes.push(scene);
        scene.create();
    }

    public void popScene() {
        if (!scenes.isEmpty()) {
            scenes.pop().dispose();
        }
    }

    public void setScene(iGameScene scene) {
        while (!scenes.isEmpty()) {
            scenes.pop().dispose();
        }
        scenes.push(scene);
        scene.create();
    }

    public void update(float dt) {
        scenes.peek().update(dt);
    }

    public void render(SpriteBatch batch) {
        scenes.peek().render(batch);
    }

    public void dispose() {
        if (!scenes.isEmpty()) {
            scenes.peek().dispose();
        }
    }
}