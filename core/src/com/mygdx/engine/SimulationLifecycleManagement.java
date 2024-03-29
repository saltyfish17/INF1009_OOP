package com.mygdx.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.*;

public class SimulationLifecycleManagement {
    private GameEntityManager entityManager;
    private Player player;
    private Texture asteroidTexture, heartTexture, healthPowerUpTexture, droneTexture, blackHoleTexture;
    private ExplosionManager explosionManager;
    private DroneFactory droneFactory;
    private AsteroidFactory asteroidFactory;
    private HealthFactory healthFactory;
    private PlayerFactory playerFactory;
    private BlackHoleFactory blackHoleFactory;

    private float elapsedTime = 0;
    private static final float FALL_INTERVAL = 0.8f;
    private float healthElapsedTime = 0;
    private float droneElapsedTime = 0;
    private static final float HEALTH_FALL_INTERVAL = 5f;
    private static final float DRONE_SPAWN_INTERVAL = 7f;
    private boolean spaceKeyWasPressed = false;
    private int numAsteroids = 8;

    public SimulationLifecycleManagement(Texture playerTexture, Texture bulletTexture, Texture asteroidTexture,
                                         Texture heartTexture, Texture healthPowerUpTexture, Texture droneTexture, Texture blackHoleTexture, Sound shootingSound,
                                         float volume, SceneManager sceneManager) {
        this.asteroidTexture = asteroidTexture;
        this.heartTexture = heartTexture;
        this.healthPowerUpTexture = healthPowerUpTexture;
        this.droneTexture = droneTexture;
        this.blackHoleTexture = blackHoleTexture;
        this.entityManager = new GameEntityManager();
        this.droneFactory = new DroneFactory(droneTexture, entityManager, bulletTexture, shootingSound, volume);
        this.asteroidFactory = new AsteroidFactory(asteroidTexture, entityManager);
        this.healthFactory = new HealthFactory(healthPowerUpTexture, entityManager);
        this.blackHoleFactory = new BlackHoleFactory(blackHoleTexture, entityManager);
        this.playerFactory = new PlayerFactory(playerTexture, entityManager, bulletTexture, shootingSound, volume);
        this.explosionManager = new ExplosionManager();
        this.explosionManager.createExplosionFrames();

        playerFactory.createEntity(1);
        this.player = entityManager.getPlayerEntity();
        this.player.setSceneManager(sceneManager);

        initializeEntities();
    }
    public SimulationLifecycleManagement(Texture playerTexture, Texture bulletTexture, Texture asteroidTexture,
			Texture heartTexture, Texture healthPowerUpTexture, Sound shootingSound, float volume,
			SceneManager sceneManager) {
    	this.asteroidTexture = asteroidTexture;
        this.heartTexture = heartTexture;
        this.healthPowerUpTexture = healthPowerUpTexture;
        this.entityManager = new GameEntityManager();
        this.asteroidFactory = new AsteroidFactory(asteroidTexture, entityManager);
        this.healthFactory = new HealthFactory(healthPowerUpTexture, entityManager);
        this.playerFactory = new PlayerFactory(playerTexture, entityManager, bulletTexture, shootingSound, volume);
        this.explosionManager = new ExplosionManager();
        this.explosionManager.createExplosionFrames();

        playerFactory.createEntity(1);
        this.player = entityManager.getPlayerEntity();
        this.player.setSceneManager(sceneManager);

        initializeEntities();
	}
    private void initializeEntities() {
    	asteroidFactory.createEntity(numAsteroids);
    }

    public void update(float dt) {
        handleInput();
        spawnEntities(dt);

        CollisionManagement collisionManagement = new CollisionManagement();
        collisionManagement.checkCollisions(entityManager);
        explosionManager.updateExplosions(entityManager);
        entityManager.updateAllEntities(dt);

    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !spaceKeyWasPressed) {
            spaceKeyWasPressed = true;
            player.shoot();
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            spaceKeyWasPressed = false;
        }
    }

    private void spawnEntities(float dt) {
        elapsedTime += dt;
        if (elapsedTime >= FALL_INTERVAL) {
        	asteroidFactory.createEntity(3);
            elapsedTime = 0;
            if (this.blackHoleFactory != null) {
            blackHoleFactory.createEntity(1);
            }
        }

        healthElapsedTime += dt;
        if (healthElapsedTime >= HEALTH_FALL_INTERVAL) {
        	healthFactory.createEntity(1);
            healthElapsedTime = 0;
        }

        droneElapsedTime += dt;
        if (this.droneFactory != null) {
	        if (droneElapsedTime >= DRONE_SPAWN_INTERVAL) {
	        	droneFactory.createEntity(3);
	        	droneElapsedTime = 0;
	        }
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
            batch.draw(heartTexture, 460 + i * (heartIconWidth + 5), 440,
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