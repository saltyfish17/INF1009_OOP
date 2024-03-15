package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class GameObject {
    protected float x;
    protected float y;
    protected Texture texture;
    
    public float getWidth() {
        return getTexture().getWidth(); // Assuming the width is determined by the player's texture
    }

    public float getHeight() {
        return getTexture().getHeight(); // Assuming the height is determined by the player's texture
    }

    public GameObject(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }
    
    public Bullet shoot() {
        float bulletWidth = 10; // Set the desired width of the bullet
        float bulletHeight = 20; // Set the desired height of the bullet
        return new Bullet(getX() + getWidth() / 2, getY() - bulletHeight, bulletWidth, bulletHeight);
    }

    public abstract void update();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }
}
