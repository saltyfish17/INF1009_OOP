package com.mygdx.engine;

 import java.util.List;

public class CollisionManagement {
    public void checkCollisions(EntityManagerNew entityManager) {
    	List<ParentEntity> entities = entityManager.getAllEntities();

        for (int i = 0; i < entities.size(); i++) {
        	ParentEntity entityA = entities.get(i);
        	Class<? extends ParentEntity> class1 = entityA.getClass();
        	
            for (int j = i + 1; j < entities.size(); j++) {
            	ParentEntity entityB = entities.get(j);
            	Class<? extends ParentEntity> class2 = entityB.getClass();
            	
            	if (class1 != class2) {
	                if (collides(entityA, entityB)) {
	                	entityA.handleCollision(entityB);
	                	entityB.handleCollision(entityA);
	                    break;
	                }
             	}
            }
        }
    }
    public boolean collides(ParentEntity object1, ParentEntity object2) {
        float object1X = object1.getX();
        float object1Y = object1.getY();
        float object1Width = object1.getTextureWidth() * object1.getScale();
        float object1Height = object1.getTextureHeight() * object1.getScale();

        float object2X = object2.getX();
        float object2Y = object2.getY();
        float object2Width = object2.getTextureWidth() * object2.getScale();
        float object2Height = object2.getTextureHeight() * object2.getScale();

        // Check for collision using bounding box collision detection
        return object1X < object2X + object2Width &&
               object1X + object1Width > object2X &&
               object1Y < object2Y + object2Height &&
               object1Y + object1Height > object2Y;
    }
 }
