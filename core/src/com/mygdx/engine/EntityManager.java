package com.mygdx.engine;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class EntityManager {
	protected List<ParentEntity> entities;
	protected List<ParentEntity> removedEntities;
	
	public EntityManager() {
		this.entities = new ArrayList<ParentEntity>();
		this.removedEntities = new ArrayList<ParentEntity>();
		}
	
	public void addEntity(ParentEntity entity) {
		entities.add(entity);
	} 
	
	public void removeEntity(ParentEntity entity) {
		entities.remove(entity);
		removedEntities.add(entity);
	}
	
	public void removeRemovedEntity(ParentEntity entity) {
		removedEntities.remove(entity);
	}
	
	public void updateAllEntities(float dt) {
		List<ParentEntity> entitiesCopy = new ArrayList<>(entities);
		for (ParentEntity entity : entitiesCopy) {
			entity.update(dt);
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
	
	public List<ParentEntity> getRemovedEntities() {
		return removedEntities;
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
