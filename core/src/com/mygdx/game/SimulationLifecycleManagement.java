package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.engine.*;

public class SimulationLifecycleManagement {
    private EntityManagerNew entityManager;
    private Player player;
    private Texture playerTexture, bulletTexture, asteroidTexture, heartTexture, healthPowerUpTexture;;
    private ExplosionManager explosionManager;

    private float elapsedTime = 0;
    private float asteroidElapsedTime = 0;
    private static final float FALL_INTERVAL = 0.5f;
    private float healthElapsedTime = 0;
    private static final float HEALTH_FALL_INTERVAL = 5f;
    private boolean spaceKeyWasPressed = false;
    private int numAsteroids = 6;

    public SimulationLifecycleManagement(Texture playerTexture, Texture bulletTexture, Texture asteroidTexture,
                                         Texture heartTexture, Texture healthPowerUpTexture, Sound shootingSound,
                                         float volume, SceneManager sceneManager) {
        this.asteroidTexture = asteroidTexture;
        this.heartTexture = heartTexture;
        this.healthPowerUpTexture = healthPowerUpTexture;
        this.entityManager = new EntityManagerNew();
        this.player = new Player(playerTexture, bulletTexture, entityManager, shootingSound, volume);
        this.player.setSceneManager(sceneManager);
        this.explosionManager = new ExplosionManager();
        this.explosionManager.createExplosionFrames();

        initializeEntities();
    }
    public int getPlayerHealth() {
        return player.getHealth();
    }
    private void initializeEntities() {
        entityManager.addEntity(player);
        for (int i = 0; i < numAsteroids; i++) {
            entityManager.addEntity(new Asteroid(asteroidTexture, entityManager, 0.5f));
        }
    }

    public void update(float dt) {
        handleInput();
        spawnEntities(dt);

        CollisionManagement collisionManagement = new CollisionManagement();
        collisionManagement.checkCollisions(entityManager);
        explosionManager.updateExplosions(entityManager);
        entityManager.updateAllEntities();

    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !spaceKeyWasPressed) {
            spaceKeyWasPressed = true;
            entityManager.addEntity(player.shoot());
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            spaceKeyWasPressed = false;
        }
    }

    private void spawnEntities(float dt) {
        elapsedTime += dt;
        if (elapsedTime >= FALL_INTERVAL) {
            entityManager.addEntity(new Asteroid(asteroidTexture, entityManager, 0.5f));
            elapsedTime = 0;
        }

        healthElapsedTime += dt;
        if (healthElapsedTime >= HEALTH_FALL_INTERVAL) {
            entityManager.addEntity(new HealthPowerUp(healthPowerUpTexture, entityManager));
            healthElapsedTime = 0;
        }
    }

    public void render(SpriteBatch batch) {
        entityManager.renderAllEntities(batch, Gdx.graphics.getDeltaTime());
        drawPlayerHealth(batch);
        explosionManager.renderExplosions(batch, 0.5f);
    }

    private void drawPlayerHealth(SpriteBatch batch) {
        float heartIconWidth = 20;
        float heartIconHeight = 20;
        for (int i = 0; i < player.getHealth(); i++) {
            batch.draw(heartTexture, 600 + i * (heartIconWidth + 5), Gdx.graphics.getHeight() - heartIconHeight + 50,
                    heartIconWidth, heartIconHeight);
        }
    }


    public void dispose() {
        asteroidTexture.dispose();
        heartTexture.dispose();
        healthPowerUpTexture.dispose();
        explosionManager.dispose();
    }
}