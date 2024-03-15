package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.ParentEntity;

public class Bullet extends ParentEntity {
    private static final int BULLET_SPEED = 10;
    private static final float BULLET_SCALE = 0.05f;
    
    // constructors
    public Bullet(float startX, float startY, float width, float height, Texture texture) {
        super(startX, startY, width, height, BULLET_SPEED, BULLET_SCALE, texture);
        this.scale = BULLET_SCALE;
    }

    // abstract methods from ParentEntity
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
	public void handleCollision(ParentEntity entityA, ParentEntity entityB) {
		// TODO Auto-generated method stub
		
	}
}
