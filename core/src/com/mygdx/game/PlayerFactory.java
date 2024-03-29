package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.iEntityFactory;

public class PlayerFactory implements iEntityFactory<Player, GameEntityManager> {
	private Texture tex;
	private GameEntityManager entityManager;
	private Texture bulletTex;
	private Sound shootingSound;
	private float volume;
	
	public PlayerFactory(Texture tex, GameEntityManager entityManager, Texture bulletTex, Sound shootingSound, float volume) {
		this.tex = tex;
		this.entityManager = entityManager;
		this.bulletTex = bulletTex;
		this.shootingSound = shootingSound;
		this.volume = volume;
	}
	
	@Override
	public void createEntity(int i) {
		for (int count=0; count<i; count++) {
			Player player = new Player(this.tex, this.bulletTex, this.entityManager, this.shootingSound, this.volume);
			entityManager.addEntity(player);	
		}
	}
}
