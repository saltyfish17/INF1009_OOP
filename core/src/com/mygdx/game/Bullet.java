package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.*;


public class Bullet extends ParentEntity {
    private static final int BULLET_SPEED = 10;
    private static final float BULLET_SCALE = 0.05f;
    private EntityManagerNew entityManager;
   
    // constructors
    public Bullet(float startX, float startY, float width, float height, Texture texture, EntityManagerNew entityManager) {
        super(startX, startY, width, height, BULLET_SPEED, BULLET_SCALE, texture);
        this.scale = BULLET_SCALE;
        this.entityManager = entityManager;
        }
    
	@Override
    public void update() {
        setY(getY() + getSpeed());
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
			ScoreManager.addScore(100);
	    }
	}
}
