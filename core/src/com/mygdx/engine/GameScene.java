package com.mygdx.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameScene {
    void create();
    void update(float dt);
    void render(SpriteBatch batch);
    void dispose();
}