package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player extends GameObject {
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_START_X = 200;
    private static final int PLAYER_START_Y = 50;
    private static final int PLAYER_HEALTH = 3;

    private int health;
    private Texture bulletTexture; // Add bulletTexture as a member variable

    public Player(Texture texture, Texture bulletTexture) {
        super(PLAYER_START_X, PLAYER_START_Y, texture);
        this.bulletTexture = bulletTexture; // Initialize bulletTexture
        health = PLAYER_HEALTH;
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            setX(getX() - PLAYER_SPEED);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            setX(getX() + PLAYER_SPEED);
        }
    }

    public Bullet shoot() {
        float bulletWidth = 10; // Set the desired width of the bullet
        float bulletHeight = 20; // Set the desired height of the bullet
        return new Bullet(getX() + getWidth() / 2, getY() - bulletHeight, bulletWidth, bulletHeight);
    }




    public void hit() {
        health--;
    }

    public int getHealth() {
        return health;
    }
}
