package com.mygdx.engine;

import com.mygdx.game.Asteroid;
import com.mygdx.game.Bullet;
import com.mygdx.game.Player;

import java.util.List;
import java.util.ArrayList;

public class CollisionManagement {
    public void checkCollisions(EntityManagerNew entityManager) {
    	List<ParentEntity> entities = entityManager.getAllEntities();

        for (int i = 0; i < entities.size(); i++) {
        	ParentEntity entityA = entities.get(i);
            for (int j = i + 1; j < entities.size(); j++) {
            	ParentEntity entityB = entities.get(j);
                if (collides(entityA, entityB)) {
                    handleCollision(entityA, entityB);
                    break;
                }
            }
        }
    
    	// Get all Bullet, Asteroid and Player entities
    	
    	List<Bullet> bullets = entityManager.getBulletEntities();
    	List<Asteroid> asteroids = entityManager.getAsteroidEntities();
        Player player = entityManager.getPlayerEntity(); 

        // Iterate over bullets and asteroids
        // Check for collision between asteroid and bullet
        for (Asteroid asteroid : asteroids) {
            for (Bullet bullet : bullets) {
            	// if bullet collide with asteroid
                if (collides(bullet, asteroid)) {
                    // Handle collision
                	// entityManager.removeEntity(asteroid);
                	entityManager.removeEntity(asteroid);
                	entityManager.removeEntity(bullet);
                	player.setScore(player.getScore() + asteroid.getPointsPerAsteroid());
                	// System.out.println(player.getScore());
                	
                    
                    break; // Exit inner loop since bullet can collide with only one asteroid
                }
            }
        }
        
        // Iterate over asteroids
        // Check for collision between asteroid and player
        for (Asteroid asteroid : asteroids) {
                if (collides(player, asteroid)) {
                    // Handle collision
                    // For example, you might decrease player health, play a sound, or end the game
                	
                	// entityManager.getAsteroids().remove(asteroid);
                	// asteroid.destroyAsteroid();
                	entityManager.removeEntity(asteroid);
                	player.hit();
                    // System.out.println("Player collided with an asteroid!");
                    break; // No need to check other asteroids if the player has collided with one
                }
        }
}
    public boolean collides(ParentEntity object1, ParentEntity object2) {
        // Check collision between object1 and object2
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
    
    public void handleCollision(ParentEntity entityA, ParentEntity entityB) {
    }
    
   
}
