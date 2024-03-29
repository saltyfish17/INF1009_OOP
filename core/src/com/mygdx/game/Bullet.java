package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.*;

public class Bullet extends ParentEntity {
    private EntityManager entityManager;
    private BulletType bulletType;
    public enum BulletType {
    	PLAYER,
    	DRONE
    }
   
    // constructors
    public Bullet(float startX, float startY, float width, float height, int speed, float scale, Texture texture, EntityManager entityManager, Bullet.BulletType bulletType) {
        super(startX, startY, width, height, speed, scale, texture);
        this.entityManager = entityManager;
        this.bulletType = bulletType;
        }
    
	public BulletType getBulletType() {
		return bulletType;
	}

	public void setBulletType(BulletType bulletType) {
		this.bulletType = bulletType;
	}

	@Override
    public void update(float dt) {
		if (getBulletType() == BulletType.PLAYER) {
	        setY(getY() + getSpeed());
		} else if (getBulletType() == BulletType.DRONE) {
			setY(getY() - getSpeed());
		}
    }

	@Override
	public void render(SpriteBatch batch, float dt) {
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
	}

	@Override
	public Class<? extends ParentEntity> getEntityType() {
		return Bullet.class;
	}

	@Override
	public void handleCollision(ParentEntity entityB) {
		
		Player player = entityManager.getEntitiesByType(Player.class).stream().findFirst().orElse(null);
        if (entityB.getEntityType().equals(Asteroid.class) && entityB instanceof Asteroid) {
			entityManager.removeEntity(this);
			ScoreManager.addScore(((Asteroid) entityB).getPointsPerAsteroid());
        }
        if (entityB.getEntityType().equals(Drone.class) && entityB instanceof Drone) {
			entityManager.removeEntity(this);
			ScoreManager.addScore(((Drone) entityB).getPointsPerDrone());
	    }
	}
}
