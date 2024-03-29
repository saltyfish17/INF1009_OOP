package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.iEntityFactory;

public class HealthFactory implements iEntityFactory<HealthPowerUp, GameEntityManager> {
	private Texture tex;
	private GameEntityManager entityManager;

	public HealthFactory(Texture tex, GameEntityManager entityManager) {
		this.tex = tex;
		this.entityManager = entityManager;
	}
	@Override
	public void createEntity(int i) {
		for (int count=0; count<1; count++) {
			HealthPowerUp healthPowerUp = new HealthPowerUp(tex, entityManager);
			entityManager.addEntity(healthPowerUp);
		}
	}

}
