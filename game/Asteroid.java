package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.engine.EntityManagerNew;
import com.mygdx.engine.ParentEntity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Asteroid extends ParentEntity {
    private static final int ASTEROID_SPEED = 4;
    private static final float ASTEROID_SCALE = 0.04f;
    
    private boolean isDestroyed;
    private int pointsPerAsteroid;
    private EntityManagerNew entityManager; 
    private Sound explosionSound; // Sound for explosion
    private float explosionVolume; // Volume for explosion sound


      //private Explosion explosion;
    //private Array<TextureRegion> explosionFrames;
    
    // constructors
    public Asteroid(Texture texture, EntityManagerNew entityManager,float explosionVolume) {
    	super(MathUtils.random(0, Gdx.graphics.getWidth() - texture.getWidth() * ASTEROID_SCALE), // random START_X value
    			MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() * 2), // random START_Y value
    			50, 50, ASTEROID_SPEED, ASTEROID_SCALE, texture); // width, height, speed, scale, texture
    	this.isDestroyed = false;
    	this.pointsPerAsteroid = 100;
    	this.entityManager = entityManager;
        this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3")); // Load explosion sound
        this.explosionVolume = explosionVolume;
    	//this.explosion = new Explosion(explosionFrames, 0.1f, getX(), getY()); // Initialize the explosion
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
		//Explosion explosion = new Explosion(explosionFrames, 0.3f);
        if (entityB.getEntityType().equals(Bullet.class) && entityB instanceof Bullet) {
            entityManager.removeEntity(this);
            isDestroyed = true; // Mark asteroid as destroyed
            explosionSound.play(explosionVolume); // Play explosion sound

            //explosion.trigger(getX(), getY());
         }
        if (entityB.getEntityType().equals(Player.class) && entityB instanceof Player) {
            entityManager.removeEntity(this); // Remove the asteroid itself
            isDestroyed = true; // Mark asteroid as destroyed
            explosionSound.play(explosionVolume); // Play explosion sound

            //explosion.trigger(getX(), getY());
         }
    }
}

