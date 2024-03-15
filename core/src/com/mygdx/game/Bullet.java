package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Bullet extends GameObject {
    private static final int BULLET_SPEED = 10;
    private float width; // Add width and height fields
    private float height;

    public Bullet(float startX, float startY, float width, float height) {
        super(startX, startY, new Texture("bullet.png"));
        this.width = width; // Initialize width and height
        this.height = height;
    }

    @Override
    public void update() {
        setY(getY() + BULLET_SPEED);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
