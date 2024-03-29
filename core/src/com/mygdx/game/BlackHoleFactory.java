package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.iEntityFactory;

public class BlackHoleFactory implements iEntityFactory<BlackHole, GameEntityManager> {
	private Texture tex;
	private GameEntityManager entityManager;
		
	public BlackHoleFactory(Texture tex, GameEntityManager entityManager) {
		this.tex = tex;
		this.entityManager = entityManager;
	}

	@Override
	public void createEntity(int i) {
		for (int count = 0; count < i; count++) {
			BlackHole blackhole= new BlackHole(this.tex, this.entityManager);
			entityManager.addEntity(blackhole); 
		}
	}

}
