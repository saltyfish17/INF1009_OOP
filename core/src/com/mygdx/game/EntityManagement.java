package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManagement {
    private Player player;
    private List<Bullet> bullets;
    private List<Asteroid> asteroids;

    public EntityManagement(Player player) {
        this.player = player;
        bullets = new ArrayList<>();
        asteroids = new ArrayList<>();
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void addAsteroid(Asteroid asteroid) {
        asteroids.add(asteroid);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void update() {
        // Update player
        player.update();

        // Update bullets
        for (Iterator<Bullet> iter = bullets.iterator(); iter.hasNext();) {
            Bullet bullet = iter.next();
            bullet.update();
            if (bullet.getY() > 600) {
                iter.remove(); // Remove bullets that go off screen
            }
        }

        // Update asteroids
        for (Iterator<Asteroid> iter = asteroids.iterator(); iter.hasNext();) {
            Asteroid asteroid = iter.next();
            asteroid.update();
            if (asteroid.getY() + asteroid.getHeight() < 0) {
                iter.remove(); // Remove asteroids that go off screen
            }
        }
    }

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
}
