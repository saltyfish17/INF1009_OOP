package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.*;

public class GameLogicManager {
    private EntityManagerNew entityManager;
    private Player player;
    private int numAsteroids = 10;
    private float elapsedTime = 0;
    private float asteroidElapsedTime = 0;
    private static final float FALL_INTERVAL = 0.5f; // Adjust this interval as needed
    private float healthElapsedTime = 0;
    private static final float HEALTH_FALL_INTERVAL = 5f; // Adjust this interval as needed
    private boolean spaceKeyWasPressed = false;
    private SceneManager sceneManager;

    public GameLogicManager(EntityManagerNew entityManager, SceneManager sceneManager) {
        this.entityManager = entityManager;
        this.sceneManager = sceneManager;
        initializeGame();
    }

    private void initializeGame() {
        player = new Player(50, 50, new Texture("player.png"), new Texture("bullet.png"), entityManager);
        entityManager.addEntity(player);

        for (int i = 0; i < numAsteroids; i++) {
            Asteroid asteroid = new Asteroid(new Texture("asteroid.png"), entityManager);
            entityManager.addEntity(asteroid);
        }
    }

    public void update(float dt) {
        // Check for collisions
        CollisionManagement collisionManagement = new CollisionManagement();
        collisionManagement.checkCollisions(entityManager);

        entityManager.updateAllEntities();

        asteroidElapsedTime += dt;
        healthElapsedTime += dt;

        // Spawn asteroids continuously for 15 seconds
        elapsedTime += dt;
        if (elapsedTime >= FALL_INTERVAL) {
            Asteroid asteroid = new Asteroid(new Texture("asteroid.png"), entityManager);
            entityManager.addEntity(asteroid);
            elapsedTime -= FALL_INTERVAL; // Reset the timer
        }

        // Spawn 1 health power up every ? seconds
        if (healthElapsedTime >= HEALTH_FALL_INTERVAL) {
            HealthPowerUp healthPowerUp = new HealthPowerUp(new Texture("health_powerup.png"), entityManager);
            entityManager.addEntity(healthPowerUp);

            healthElapsedTime -= HEALTH_FALL_INTERVAL;
        }

        // Check if space key is pressed and was not pressed in the previous frame
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !spaceKeyWasPressed) {
            // Set the flag to true to prevent multiple firings in the same frame
            spaceKeyWasPressed = true;

            // Add a single bullet to the EntityManager
            entityManager.addEntity(player.shoot());
        }

        // Check if space key is released
        if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // Reset the flag when space key is released
            spaceKeyWasPressed = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            sceneManager.pushScene(new PauseMenuScene(sceneManager));
        }
    }
    
}
