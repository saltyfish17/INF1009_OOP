package com.mygdx.engine;

import java.util.ArrayList;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Asteroid;
import com.mygdx.game.Bullet;
import com.mygdx.game.Player;

public class EntityManagerNew {
	private List<ParentEntity> entities = new ArrayList<>();
	
	public void addEntity(ParentEntity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(ParentEntity entity) {
		entities.remove(entity);
		// System.out.println("Removed:" + entity);
	}
	
	public void updateAllEntities() {
		for (ParentEntity entity : entities) {
			entity.update();
		}
	}
	
	public void renderAllEntities(SpriteBatch batch, float dt) {
		int i = 1;
		for (ParentEntity entity : entities) {
			entity.render(batch, dt);
	}
	}
	
	public List<ParentEntity> getAllEntities() {
		return entities;
	}
	
	public ParentEntity getEntityById(int id) {
		for (ParentEntity entity : entities) {
			if (entity.getId() == id) {
				return entity; // return entity with matching id
			}
		}
		
		return null; // return null if id not found
	}
	
	public List<? extends ParentEntity> getEntitiesOfType(Class<? extends ParentEntity> entityType) {
        List<? extends ParentEntity> entitiesOfType = new ArrayList<>();
        for (ParentEntity entity : entities) {
            if (entityType.isInstance(entity)) {
                entitiesOfType.add(entity);
            }
        }
        return entitiesOfType;
    }
	
	public List<Asteroid> getAsteroidEntities() {
		List<Asteroid> asteroids = new ArrayList<>();
		for (ParentEntity entity : entities) {
			if (entity.getEntityType().equals(Asteroid.class) && entity instanceof Asteroid) {
				asteroids.add((Asteroid) entity);
			}
		}
		
		return asteroids;
	}
	
	public Player getPlayerEntity() {
		for (ParentEntity entity : entities) {
			if (entity.getEntityType().equals(Player.class) && entity instanceof Player) {
				return (Player) entity;
			}
		}
		return null;
	}
	
	public List<Bullet> getBulletEntities() {
		List<Bullet> bullets = new ArrayList<>();
		for (ParentEntity entity : entities) {
			if (entity.getEntityType().equals(Bullet.class) && entity instanceof Bullet) {
				bullets.add((Bullet) entity);
			}
		}
		return bullets;
	}
	
}
