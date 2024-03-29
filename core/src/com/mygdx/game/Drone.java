package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.*;
import com.mygdx.game.Bullet.BulletType;

public class Drone extends ParentEntity {
	private static final int DRONE_SPEED = 2;
	private static final float DRONE_SCALE= 0.15f;
    private static final int DRONE_START_Y = 500;
    private static final float DRONE_SHOT_INTERVAL = 3f;
    
    private boolean isDestroyed;
    private boolean moveRight;
    private int pointsPerDrone;
    private Texture bulletTex;
    private GameEntityManager entityManager;
    private BulletFactory bulletFactory;
    private Sound shootingSound;
    private float shootingVolume;
    private Sound explosionSound; // Sound for explosion
    private float explosionVolume; // Volume for explosion sound
    private float shotInterval;
    private float shotTimer;
    private float randMoveTimer;
    private float randMoveDuration;

	public Drone(float startX, Texture tex, Texture bulletTex, GameEntityManager entityManager, Sound shootingSound, float shootingVolume, float explosionVolume) {
		super(startX, DRONE_START_Y, 0, 0, DRONE_SPEED, DRONE_SCALE, tex);
		this.setDestroyed(false);
		this.moveRight = true;
    	this.setPointsPerDrone(200);
    	this.entityManager = entityManager;
    	this.bulletFactory = new BulletFactory(bulletTex, entityManager, getId());
    	this.setBulletTex(bulletTex);
        this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3")); // Load explosion sound
        this.explosionVolume = explosionVolume;
        this.shootingSound = shootingSound;
        this.shootingVolume = shootingVolume;
        this.shotInterval = DRONE_SHOT_INTERVAL;
        this.shotTimer = 0;
        this.randMoveTimer = 0f;
        this.randMoveDuration = MathUtils.random(3f, 7f);
        setVelocityX(DRONE_SPEED);
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}


	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}


	public int getPointsPerDrone() {
		return pointsPerDrone;
	}


	public void setPointsPerDrone(int pointsPerDrone) {
		this.pointsPerDrone = pointsPerDrone;
	}

	public Texture getBulletTex() {
		return bulletTex;
	}


	public void setBulletTex(Texture bulletTex) {
		this.bulletTex = bulletTex;
	}


	public Sound getShootingSound() {
		return shootingSound;
	}


	public void setShootingSound(Sound shootingSound) {
		this.shootingSound = shootingSound;
	}


	public float getShootingVolume() {
		return shootingVolume;
	}


	public void setShootingVolume(float shootingVolume) {
		this.shootingVolume = shootingVolume;
	}


	public Sound getExplosionSound() {
		return explosionSound;
	}


	public void setExplosionSound(Sound explosionSound) {
		this.explosionSound = explosionSound;
	}


	public float getExplosionVolume() {
		return explosionVolume;
	}


	public void setExplosionVolume(float explosionVolume) {
		this.explosionVolume = explosionVolume;
	}


	public float getShotInterval() {
		return shotInterval;
	}


	public void setShotInterval(float shotInterval) {
		this.shotInterval = shotInterval;
	}


	public float getShotTimer() {
		return shotTimer;
	}


	public void setShotTimer(float shotTimer) {
		this.shotTimer = shotTimer;
	}


	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public float getRandMoveDuration() {
		return randMoveDuration;
	}

	public void setRandMoveDuration(float randMoveDuration) {
		this.randMoveDuration = randMoveDuration;
	}

	public float getRandMoveTimer() {
		return randMoveTimer;
	}

	public void setRandMoveTimer(float randMoveTimer) {
		this.randMoveTimer = randMoveTimer;
	}

	public void shoot() {
        bulletFactory.createEntity(1);
        if (shootingSound != null) {
            shootingSound.play(getShootingVolume());
        }
    }

	@Override
	public void update(float dt) {
		setY(getY() - getVelocityY());
		if (getY() <= 350) {
			// descend until certain height
			setVelocityY(0);
		}
		
		setRandMoveTimer(getRandMoveTimer() + dt);
		if (getX() < 0 && !isMoveRight()) {
			setMoveRight(true);
		} else if (getX() > 600 && isMoveRight()) {
			setMoveRight(false);
		}
		if (getRandMoveTimer() < getRandMoveDuration()) {
			// if within boundary, move on x axis
			setX(getX() + (moveRight ? getVelocityX():-getVelocityX()));
		} else {
			// switch directions if exceed time limit
			setMoveRight(!isMoveRight());
			setRandMoveDuration(MathUtils.random(3f, 7f));
			setRandMoveTimer(0);
		}
		
		shotTimer += dt;
		if (shotTimer >= shotInterval) {
			shoot();
			shotTimer = 0;
		}
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		batch.draw(getTexture(), getX(), getY(), getTextureWidth() * getScale(), getTextureHeight() * getScale());
	}

	@Override
	public Class<? extends ParentEntity> getEntityType() {
		return Drone.class;
	}

	@Override
	public void handleCollision(ParentEntity entityB) {
		//Explosion explosion = new Explosion(explosionFrames, 0.3f);
		if (entityB.getEntityType().equals(Bullet.class) && entityB instanceof Bullet) {
			if (((Bullet) entityB).getBulletType() == BulletType.PLAYER) {
				entityManager.removeEntity(this);
				setDestroyed(true);
				explosionSound.play(explosionVolume);
			}
		}
        if (entityB.getEntityType().equals(BlackHole.class) && entityB instanceof BlackHole) {
    		entityManager.removeEntity(this);
            setDestroyed(true); // Mark drone as destroyed
            explosionSound.play(explosionVolume); // Play explosion sound
            //explosion.trigger(getX(), getY());
         }
        if (entityB.getEntityType().equals(Drone.class) && entityB instanceof Drone) {
        	System.out.println("Drone collision");
        	setMoveRight(!isMoveRight());
        }
	}
}

