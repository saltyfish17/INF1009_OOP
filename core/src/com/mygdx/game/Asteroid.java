package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Asteroid extends GameObject {
    private static final int ASTEROID_SPEED = 2;
    private Texture asteroidTexture; // Define the asteroidTexture variable

    public Asteroid(float startX, float startY, Texture texture) {
        super(startX, startY, texture);
        this.asteroidTexture = texture; // Initialize the asteroidTexture
    }

    @Override
    public void update() {
        setY(getY() - ASTEROID_SPEED);
    }

    public Texture getAsteroidTexture() {
        return asteroidTexture;
    }
}
