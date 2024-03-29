package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.iEntityFactory;

public class AsteroidFactory implements iEntityFactory<Asteroid, GameEntityManager> {
	private Texture tex;
	private GameEntityManager entityManager;
	
	public AsteroidFactory(Texture tex, GameEntityManager entityManager) {
		this.tex = tex;
		this.entityManager = entityManager;
	}
	
	@Override
	public void createEntity(int i) {
		for (int count = 0; count < i; count++) {
			Asteroid asteroid = new Asteroid(this.tex, this.entityManager, 0.5f);
			entityManager.addEntity(asteroid);
		}
	}

}
