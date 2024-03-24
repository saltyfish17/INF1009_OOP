package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.EntityManagerNew;
import com.mygdx.engine.ParentEntity;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends ParentEntity {
    private static final int ASTEROID_SPEED = 2;
    private static final float ASTEROID_SCALE = 0.03f;
    
    private boolean isDestroyed;
    private int pointsPerAsteroid;
    private EntityManagerNew entityManager; 
    // constructors
    public Asteroid(Texture texture, EntityManagerNew entityManager) {
    	super(MathUtils.random(0, Gdx.graphics.getWidth() - texture.getWidth() * ASTEROID_SCALE), // random START_X value
    			MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() * 2), // random START_Y value
    			50, 50, ASTEROID_SPEED, ASTEROID_SCALE, texture); // width, height, speed, scale, texture
    	this.isDestroyed = false;
    	this.pointsPerAsteroid = 100;
    	this.entityManager = entityManager;
    }
    
    public Asteroid(float startX, float startY, float width, float height, Texture texture) {
        super(startX, startY, width, height,  ASTEROID_SPEED, ASTEROID_SCALE,  texture);
        this.isDestroyed = false;
        this.pointsPerAsteroid = 100;
    }

    public boolean getIsDestroyed() {
		return isDestroyed;
	}

	public void setIsDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public int getPointsPerAsteroid() {
		return pointsPerAsteroid;
	}

	public void setPointsPerAsteroid(int pointsPerAsteroid) {
		this.pointsPerAsteroid = pointsPerAsteroid;
	}

    @Override
    public void update() {
        setY(getY() - getSpeed());
    }

	@Override
	public void render(SpriteBatch batch, float dt) {
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
	}

	@Override
	public Class<? extends ParentEntity> getEntityType() {
		return Asteroid.class;
	}

	@Override
    public void handleCollision(ParentEntity entityB) {
        if (entityB.getEntityType().equals(Bullet.class) && entityB instanceof Bullet) {
            entityManager.removeEntity(this);
            isDestroyed = true; // Mark asteroid as destroyed
         }
        if (entityB.getEntityType().equals(Player.class) && entityB instanceof Player) {
            entityManager.removeEntity(this); // Remove the asteroid itself
            isDestroyed = true; // Mark asteroid as destroyed
         }
    }
}

