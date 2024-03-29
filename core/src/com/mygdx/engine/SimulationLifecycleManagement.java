package com.mygdx.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.engine.*;
import com.mygdx.game.Asteroid;
import com.mygdx.game.ExplosionManager;
import com.mygdx.game.HealthPowerUp;
import com.mygdx.game.Player;

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

    float GAME_WIDTH = Gdx.graphics.getWidth();
    float GAME_HEIGHT = Gdx.graphics.getHeight();

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

        checkPlayerBounds(player);

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
            batch.draw(heartTexture, 460 + i * (heartIconWidth + 5), 440,
                    heartIconWidth, heartIconHeight);
        }
    }

    private void checkPlayerBounds(Player player) {
        float playerWidth = player.getTextureWidth() * player.getScale();
        float playerHeight = player.getTextureHeight() * player.getScale();

        // Check left boundary
        if (player.getX() < 0) {
            player.setX(0);
        }
        // Check right boundary
        else if (player.getX() + playerWidth > GAME_WIDTH) {
            player.setX(GAME_WIDTH - playerWidth);
        }

        // Check bottom boundary
        if (player.getY() < 0) {
            player.setY(0);
        }
        // Check top boundary
        else if (player.getY() + playerHeight > GAME_HEIGHT) {
            player.setY(GAME_HEIGHT - playerHeight);
        }
    }

    public void dispose() {
        asteroidTexture.dispose();
        heartTexture.dispose();
        healthPowerUpTexture.dispose();
        explosionManager.dispose();
    }
}