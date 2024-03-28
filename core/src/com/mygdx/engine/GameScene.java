package com.mygdx.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public interface GameScene {
    void create();

    void update(float dt);

    void render(SpriteBatch batch);

    void dispose();

}