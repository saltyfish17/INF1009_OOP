package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.ParentEntity;

public class BlackHole extends ParentEntity {
	private static final int BLACKHOLE_SPEED = 4;
    private static final float BLACKHOLE_SCALE = 0.1f;

	public BlackHole(Texture tex, GameEntityManager entityManager) {
		super(MathUtils.random(0, Gdx.graphics.getWidth() - tex.getWidth() * BLACKHOLE_SCALE), // random START_X value
    			MathUtils.random(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() * 2), // random START_Y value
    			50, 50, BLACKHOLE_SPEED, BLACKHOLE_SCALE, tex);
	}

	@Override
	public void update(float dt) {
		setY(getY() - getSpeed());
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
	}

	@Override
	public Class<? extends ParentEntity> getEntityType() {
		// TODO Auto-generated method stub
		return BlackHole.class;
	}

	@Override
	public void handleCollision(ParentEntity entityB) {
		// TODO Auto-generated method stub
		
	}

}
