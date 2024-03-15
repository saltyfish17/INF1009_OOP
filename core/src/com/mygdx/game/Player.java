package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.ParentEntity;
import com.mygdx.engine.SceneManager;
import com.mygdx.engine.SimulationLifecycleManagement;
import com.mygdx.engine.CollisionManagement;

public class Player extends ParentEntity {
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_START_X = 200;
    private static final int PLAYER_START_Y = 50;
    private static final int PLAYER_HEALTH = 10;
    private static final float PLAYER_SCALE = 0.3f;
    
    private SimulationLifecycleManagement simulationLifecycleManagement;
    private Texture bulletTexture; // Add bulletTexture as a member variable
    private SceneManager sceneManager;
    private int health;
    private int score;
    

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    
    // constructors
    public Player(Texture texture) {
    	super(PLAYER_START_X, PLAYER_START_Y, 0, 0, PLAYER_SPEED, PLAYER_SCALE, texture); {
    		this.bulletTexture = null;
    		this.health = PLAYER_HEALTH;
    		this.score = 0;
    	}
    }
    
    public Player(Texture playerTexture, Texture bulletTexture) {
    	super(PLAYER_START_X, PLAYER_START_Y, 0, 0, PLAYER_SPEED, PLAYER_SCALE, playerTexture); 
    		this.bulletTexture = bulletTexture;
    		this.health = PLAYER_HEALTH;
    		this.score = 0;
    }
    
    public Player(float width, float height, Texture texture, Texture bulletTexture) {
        super(PLAYER_START_X, PLAYER_START_Y, width, height, PLAYER_SPEED, PLAYER_SCALE, texture);
        this.bulletTexture = bulletTexture; // Initialize bulletTexture
        this.health = PLAYER_HEALTH;
        this.score = 0;
    }

	// getter setter methods
    public Texture getBulletTexture() {
		return bulletTexture;
	}

	public void setBulletTexture(Texture bulletTexture) {
		this.bulletTexture = bulletTexture;
	}

    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// Player class methods
    public Bullet shoot() {
        float bulletWidth = 10; // Set the desired width of the bullet
        float bulletHeight = 10; // Set the desired height of the bullet
        
        // System.out.println("Shot:" + new Bullet(bulletHeight, bulletHeight, bulletHeight, bulletHeight, bulletTexture));
        
        return new Bullet(getX(), getY() - bulletHeight, bulletWidth, bulletHeight, getBulletTexture());
         }

    public void hit() {
        health--;
    }
    
    // abstract methods from ParentEntity
    @Override
    public void update() { // to update Player entity
    	SimulationLifecycleManagement simulationLifecycleManagement = new SimulationLifecycleManagement();
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            setX(getX() - getSpeed());
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            setX(getX() + getSpeed());
        }
        if (health <= 0) {
            // Check if sceneManager is initialized
            if (sceneManager != null) {
                sceneManager.setScene(new EndScene(sceneManager));
            }
        }
    }
    
	@Override
	public void render(SpriteBatch batch, float dt) { // to render Player entity
		// batch.draw(getTexture(), getX(), getY(), getWidth() * getScale(), getHeight() * getScale());
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
		// System.out.println("Here");
	}
	
	@Override
	public Class<? extends ParentEntity> getEntityType() { // to return Player entity type (check for Player entities)
		return Player.class;
	}

	@Override
	public void handleCollision(ParentEntity entityA, ParentEntity entityB) {
		
	}
	
}
