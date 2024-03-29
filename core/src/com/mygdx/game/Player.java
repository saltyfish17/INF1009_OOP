package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.ParentEntity;
import com.mygdx.engine.SceneManager;

public class Player extends ParentEntity {
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_START_X = 200;
    private static final int PLAYER_START_Y = 50;
    private static final int PLAYER_HEALTH = 5;
    private static final float PLAYER_SCALE = 0.3f;

    private Texture bulletTexture; // Add bulletTexture as a member variable
    private SceneManager sceneManager;
    private GameEntityManager entityManager; 
    private BulletFactory bulletFactory;
    private int health;
    private int score;
    private float volume;
    private Sound shootingSound;

    public Player(Texture playerTexture, Texture bulletTexture, GameEntityManager entityManager, Sound shootingSound, float volume) {
        super(PLAYER_START_X, PLAYER_START_Y, 0, 0, PLAYER_SPEED, PLAYER_SCALE, playerTexture);
        this.bulletTexture = bulletTexture; // Initialize bulletTexture
        this.health = PLAYER_HEALTH;
        this.score = 0;
        this.entityManager = entityManager;
        this.bulletFactory = new BulletFactory(bulletTexture, entityManager, getId());
        this.shootingSound = shootingSound;
        this.volume = volume;
    }


    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    
    // constructors
    public Player(Texture texture, GameEntityManager entityManager) {
    	super(PLAYER_START_X, PLAYER_START_Y, 0, 0, PLAYER_SPEED, PLAYER_SCALE, texture); {
    		this.bulletTexture = null;
    		this.health = PLAYER_HEALTH;
    		this.score = 0;
    		this.entityManager = entityManager;
    	}
    }
    
    public Player(Texture playerTexture, Texture bulletTexture, GameEntityManager entityManager) {
    	super(PLAYER_START_X, PLAYER_START_Y, 0, 0, PLAYER_SPEED, PLAYER_SCALE, playerTexture); 
    		this.bulletTexture = bulletTexture;
    		this.health = PLAYER_HEALTH;
    		this.score = 0;
    		this.entityManager = entityManager;
    }
    
    public Player(float width, float height, Texture texture, Texture bulletTexture, GameEntityManager entityManager, Sound shootingSound, float volume) {
        super(PLAYER_START_X, PLAYER_START_Y, width, height, PLAYER_SPEED, PLAYER_SCALE, texture);
        this.bulletTexture = bulletTexture; // Initialize bulletTexture
        this.health = PLAYER_HEALTH;
        this.score = 0;
        this.entityManager = entityManager;
        this.shootingSound = shootingSound;
        this.volume = volume;
    }

    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
		this.health = health;
	}

    public void shoot() {
        bulletFactory.createEntity(1);
        if (shootingSound != null) {
            shootingSound.play(volume);
        }
        System.out.println("Player shot");
    }

    public void hit() {
        health--;
    }
    
    @Override
    public void update(float dt) { 
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            setX(getX() - getSpeed());
            if (getX() == -50) {
            	setX(650);
            }
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            setX(getX() + getSpeed());
            if (getX() == 650) {
            	setX(-50);
            }
        }
        if (health <= 0) {
            // Check if sceneManager is initialized
            if (sceneManager != null) {
                sceneManager.setScene(new GameOverScene(sceneManager));
            }
        }
    }
    
	@Override
	public void render(SpriteBatch batch, float dt) { // to render Player entity
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
	}
	
	@Override
	public Class<? extends ParentEntity> getEntityType() { // to return Player entity type (check for Player entities)
		return Player.class;
	}

	@Override
	public void handleCollision(ParentEntity entityB) {
		if (entityB.getEntityType().equals(Asteroid.class) && entityB instanceof Asteroid) {
			hit();
        }
		if (entityB.getEntityType().equals(HealthPowerUp.class) && entityB instanceof HealthPowerUp) {
			setHealth(getHealth() + 1);
        }
	}
	
}
