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
	}
	
	public void updateAllEntities() {
		for (ParentEntity entity : entities) {
			entity.update();
		}
	}
	
	public void renderAllEntities(SpriteBatch batch, float dt) {
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
	
	public <T extends ParentEntity> List<T> getEntitiesByType(Class<T> entityType) {
	    List<T> entitiesOfType = new ArrayList<>();
	    for (ParentEntity entity : entities) {
	        if (entityType.isInstance(entity)) {
	            entitiesOfType.add(entityType.cast(entity));
	        }
	    }
	    return entitiesOfType;
	}
}
