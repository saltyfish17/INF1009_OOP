package com.mygdx.game;

import java.util.List;
import java.util.ArrayList;

public class CollisionManagement {
    public void checkCollisions(EntityManagement entityManagement) {
        List<Bullet> bulletsCopy = new ArrayList<>(entityManagement.getBullets());
        List<Asteroid> asteroidsCopy = new ArrayList<>(entityManagement.getAsteroids());

        // Iterate over the copies of the lists
        for (Bullet bullet : bulletsCopy) {
            for (Asteroid asteroid : asteroidsCopy) {
                if (collides(bullet, asteroid)) {
                    // Handle collision
                    entityManagement.getBullets().remove(bullet);
                    entityManagement.getAsteroids().remove(asteroid);
                    break; // Exit inner loop since bullet can collide with only one asteroid
                }
            }
        }
    }

    private boolean collides(GameObject object1, GameObject object2) {
        // Check collision between object1 and object2
        float object1X = object1.getX();
        float object1Y = object1.getY();
        float object1Width = object1.getWidth();
        float object1Height = object1.getHeight();

        float object2X = object2.getX();
        float object2Y = object2.getY();
        float object2Width = object2.getWidth();
        float object2Height = object2.getHeight();

        // Check for collision using bounding box collision detection
        return object1X < object2X + object2Width &&
               object1X + object1Width > object2X &&
               object1Y < object2Y + object2Height &&
               object1Y + object1Height > object2Y;
    }
}
