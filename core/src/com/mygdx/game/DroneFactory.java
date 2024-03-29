package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.ParentEntity;
import com.mygdx.engine.iEntityFactory;

public class DroneFactory implements iEntityFactory<Drone, GameEntityManager> {
	private static final float DISTANCE_BETWEEN_DRONES_X = 200f;
	
	private float explosionVolume;
	private Texture tex;
	private GameEntityManager entityManager;
	private Texture bulletTex;
	private Sound shootingSound;
	private float shootingVolume;
	
	public DroneFactory(Texture tex, GameEntityManager entityManager, Texture bulletTex, Sound shootingSound, float shootingVolume) {
		this.explosionVolume = 0.5f;
		this.tex = tex;
		this.entityManager = entityManager;
		this.bulletTex = bulletTex;
		this.shootingSound = shootingSound;
		this.shootingVolume = shootingVolume;
	}

	@Override
	public void createEntity(int i) {
		float firstDroneX = 110;
		for (int count = 0; count<i; count++) {
			float x = firstDroneX + (count * DISTANCE_BETWEEN_DRONES_X);
			Drone drone = new Drone(x, this.tex, this.bulletTex, this.entityManager, this.shootingSound, this.shootingVolume, this.explosionVolume);
			entityManager.addEntity(drone);
		}
	}
}
