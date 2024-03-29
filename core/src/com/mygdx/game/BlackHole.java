package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.ParentEntity;

public class BlackHole extends ParentEntity {
	private static final int BLACKHOLE_SPEED = 1;
    private static final float BLACKHOLE_SCALE = 0.1f;
    private static final float BLACKHOLE_RADIUS = 100f;
    
    private Circle attractionArea;
    private float attractionForce;
    private GameEntityManager entityManager;

	public BlackHole(Texture tex, GameEntityManager entityManager) {
		super(MathUtils.random(0, Gdx.graphics.getWidth() - tex.getWidth() * BLACKHOLE_SCALE), // random START_X value
    			MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() * 2), // random START_Y value
    			50, 50, BLACKHOLE_SPEED, BLACKHOLE_SCALE, tex);
		this.attractionForce = 0.5f;
		this.attractionArea = new Circle(getX(), getY(), BLACKHOLE_RADIUS);
		this.entityManager = entityManager;
	}

	public Circle getAttractionArea() {
		return attractionArea;
	}

	public void setAttractionArea(Circle attractionArea) {
		this.attractionArea = attractionArea;
	}

	public float getAttractionForce() {
		return attractionForce;
	}

	public void setAttractionForce(float attractionForce) {
		this.attractionForce = attractionForce;
	}

	@Override
	public void update(float dt) {
		setY(getY() - getVelocityY());
		attractionArea.setPosition(getX(), getY());
		List<ParentEntity> nearbyEntities = entityManager.getEntitiesWithinArea(attractionArea);
		
		for (ParentEntity entity : nearbyEntities) {
			float dx = getX() - entity.getX();
            float dy = getY() - entity.getY();
            float distanceSquared = dx * dx + dy * dy;
            float distance = (float) Math.sqrt(distanceSquared);
            
            if (distance > 0) {
            	dx /= distance;
            	dy /= distance;
            }
            entity.applyForce(-(dx * attractionForce), -(dy * attractionForce));
		}
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
	}

	@Override
	public Class<? extends ParentEntity> getEntityType() {
		return BlackHole.class;
	}

	@Override
	public void handleCollision(ParentEntity entityB) {
		if (entityB.getEntityType().equals(Drone.class) && entityB instanceof Drone) {
			setScale(getScale()*1.3f);	
		}	
	}

}
