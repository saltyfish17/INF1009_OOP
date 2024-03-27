package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.engine.*;

public class GameplayScene implements GameScene {
    private SceneManager sceneManager;
    private BitmapFont font;
    private SpriteBatch batch;
    private Texture playerTexture, bulletTexture, asteroidTexture, heartTexture, healthPowerUpTexture;
    private OrthographicCamera camera;
    private Player player;
    private EntityManagerNew entityManager;
    private ExplosionManager explosionManager;
    
    private int numAsteroids = 6;
    private float elapsedTime = 0;
    private float asteroidElapsedTime = 0;
    private static final float FALL_INTERVAL = 0.5f; // Adjust this interval as needed
    private boolean spaceKeyWasPressed = false;
    private float healthElapsedTime = 0;
    private static final float HEALTH_FALL_INTERVAL = 5f; // Adjust this interval as needed

    public GameplayScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        playerTexture = new Texture("player.png");
        bulletTexture = new Texture("bullet.png");
        asteroidTexture = new Texture("asteroid.png");
        heartTexture = new Texture("heart.png");
        healthPowerUpTexture = new Texture("health_powerup.png");

        font = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        
        entityManager = new EntityManagerNew();
        
        player = new Player(50, 50, playerTexture, bulletTexture, entityManager);
       
        player.setSceneManager(sceneManager);
        
        entityManager.addEntity(player); 
        
        new InputOutputManagement(player, entityManager);

        Gdx.input.setInputProcessor(new InputOutputManagement(player, entityManager));

        for (int i = 0; i < numAsteroids; i++) {        	
        	// spawn new Asteroid
            Asteroid asteroid = new Asteroid(asteroidTexture, entityManager);
            // add asteroid to entityManager
            entityManager.addEntity(asteroid); 
        }
    
        explosionManager = new ExplosionManager();
        explosionManager.createExplosionFrames();
    }
    
    @Override
    public void render(SpriteBatch batch) {
        float dt = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        	entityManager.renderAllEntities(batch, dt);

	        float heartIconWidth =20;
	        float heartIconHeight = 20;
	        for (int i = 0; i < player.getHealth(); i++) {
	            batch.draw(heartTexture, 600 + i * (heartIconWidth + 5), Gdx.graphics.getHeight() - heartIconHeight + 50,
	                    heartIconWidth, heartIconHeight); // Draw scaled heart icon
	        }
	
	        font.draw(batch, "Score: " + player.getScore(), 600, 500);
	        
	        explosionManager.renderExplosions(batch, 0.5f);
	        
        batch.end();

        update(dt);
    }

    @Override
    public void update(float dt) {
        
        CollisionManagement collisionManagement = new CollisionManagement();
        collisionManagement.checkCollisions(entityManager);
        
        explosionManager.updateExplosions(entityManager);
        
        entityManager.updateAllEntities();
        
        asteroidElapsedTime = Gdx.graphics.getDeltaTime();
        healthElapsedTime += Gdx.graphics.getDeltaTime();
   
        // Spawn asteroids continuously for 15 seconds
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (elapsedTime >= FALL_INTERVAL) {
        //    spawnAsteroid();
        	Asteroid asteroid = new Asteroid(asteroidTexture, entityManager);
        	entityManager.addEntity(asteroid);
            elapsedTime -= FALL_INTERVAL; // Reset the timer
        }
        
        // Spawn 1 health power up every ? seconds
        if (healthElapsedTime >= HEALTH_FALL_INTERVAL) {
        	HealthPowerUp healthPowerUp = new HealthPowerUp(healthPowerUpTexture, entityManager);
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

    @Override
    public void dispose() {
        batch.dispose();
        playerTexture.dispose();
        bulletTexture.dispose();
        asteroidTexture.dispose();
        sceneManager.dispose();
        heartTexture.dispose();
        explosionManager.dispose();
    }
}
