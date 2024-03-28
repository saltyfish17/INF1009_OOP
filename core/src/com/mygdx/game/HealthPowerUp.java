package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.AIControlManagement;
import com.mygdx.engine.EntityManagerNew;
import com.mygdx.engine.ParentEntity;

public class HealthPowerUp extends ParentEntity {
	private static final int HEALTH_POWERUP_SPEED = 3;
	private static final float HEALTH_POWERUP_SCALE = 0.09f;
	private EntityManagerNew entityManager; 
    private Sound collectSound; // Sound for collecting the power-up
    private AIControlManagement aiControlManagement;

	public HealthPowerUp(Texture texture, EntityManagerNew entityManager) {
		super(MathUtils.random(0, Gdx.graphics.getWidth() - texture.getWidth() * HEALTH_POWERUP_SCALE), // random START_X value
    			MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() * 2), // random START_Y value
    			20, 20, HEALTH_POWERUP_SPEED, HEALTH_POWERUP_SCALE, texture); // width, height, speed, scale, texture
		this.entityManager = entityManager;
        this.collectSound = Gdx.audio.newSound(Gdx.files.internal("health.mp3")); // Load collect sound
        this.aiControlManagement = new AIControlManagement();
	}

	public HealthPowerUp(float startX, float startY, float width, float height, Texture texture) {
        super(startX, startY, width, height,  HEALTH_POWERUP_SPEED, HEALTH_POWERUP_SCALE, texture);   
	}
	
	@Override
	public void update() {
		aiControlManagement.updateHealthPowerUp(this);
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
	}

	@Override
	public Class<? extends ParentEntity> getEntityType() {
		return HealthPowerUp.class;
	}


	@Override
	public void handleCollision(ParentEntity entityB) {
		if (entityB.getEntityType().equals(Player.class) && entityB instanceof Player) {
            entityManager.removeEntity(this); 
            collectSound.play(); // Play collect sound

        }
	}
}
