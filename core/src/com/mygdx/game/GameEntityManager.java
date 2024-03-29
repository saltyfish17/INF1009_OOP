package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.engine.EntityManager;
import com.mygdx.engine.ParentEntity;

public class GameEntityManager extends EntityManager{

	public GameEntityManager() {
		super();
	}
	
	public Player getPlayerEntity() {
		for (ParentEntity entity : entities) {
			if (entity instanceof Player) {
				return (Player) entity;
			}
		}
		return null;
	}
	
	public List<Asteroid> getAsteroidEntities() {
		List<Asteroid> asteroids = new ArrayList<Asteroid>();
		for (ParentEntity entity : entities) {
			if (entity instanceof Asteroid) {
				asteroids.add((Asteroid) entity);
			}
		}
		return asteroids;
	}
	
	public List<HealthPowerUp> getHealthEntities() {
		List<HealthPowerUp> healthPowerUps= new ArrayList<HealthPowerUp>();
		for (ParentEntity entity : entities) {
			if (entity instanceof Asteroid) {
				healthPowerUps.add((HealthPowerUp) entity);
			}
		}
		return healthPowerUps;
	}
	
	public List<Drone> getDroneEntities() {
		List<Drone> drones = new ArrayList<Drone>();
		for (ParentEntity entity : entities) {
			if (entity instanceof Drone) {
				drones.add((Drone) entity);
			}
		}
		return drones;
	}
}
