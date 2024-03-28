package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.engine.EntityManagerNew;

public class ExplosionManager {
    private Array<TextureRegion> explosionFrames;
    private Array<Explosion> explosions;
    private int numExplosionFrames = 13;
    
    private Array<TextureRegion> heartExplosionFrames;
    private Array<Explosion> heartExplosions;
    private int numHeartExplosionFrames = 16;

    public void createExplosionFrames() {
        explosionFrames = new Array<>();
        heartExplosionFrames = new Array<>();
        
		for (int i = 0; i < numExplosionFrames; i++) {
            explosionFrames.add(new TextureRegion(new Texture("explosion/explosion_frame_" + i + ".gif"))); 
        }
		for (int i = 0; i < numHeartExplosionFrames; i++) { 
            heartExplosionFrames.add(new TextureRegion(new Texture("hearts_exploding/frame_0" + i + "_delay-0.08s.gif"))); 
        }

        explosions = new Array<>();
        heartExplosions = new Array<>();
    }

    public void renderExplosions(SpriteBatch batch, float scale) {
        for (Explosion exp : explosions) {
            exp.render(batch, scale);
        }
        for (Explosion exp : heartExplosions) {
            exp.render(batch, scale);
        }
    }

    public void updateExplosions(EntityManagerNew entityManager) {
    	float dt = Gdx.graphics.getDeltaTime();
    	
        for (int i = 0; i < entityManager.getRemovedEntities().size(); i++) {
            if (entityManager.getRemovedEntities().get(i) instanceof Asteroid && ((Asteroid) entityManager.getRemovedEntities().get(i)).getIsDestroyed()) {
                float collisionX = ((Asteroid) entityManager.getRemovedEntities().get(i)).getX();
                float collisionY = ((Asteroid) entityManager.getRemovedEntities().get(i)).getY();
                triggerExplosion(collisionX - 30, collisionY - 50);
                entityManager.removeRemovedEntity(entityManager.getRemovedEntities().get(i));
            }
        }
        
        for (int i = 0; i < entityManager.getRemovedEntities().size(); i++) {
            if (entityManager.getRemovedEntities().get(i) instanceof HealthPowerUp) {
                float collisionX = ((HealthPowerUp) entityManager.getRemovedEntities().get(i)).getX();
                float collisionY = ((HealthPowerUp) entityManager.getRemovedEntities().get(i)).getY();
                triggerHeartExplosion(collisionX - 30, collisionY - 50);
                entityManager.removeRemovedEntity(entityManager.getRemovedEntities().get(i));
            }
        }
        
        // Remove finished explosions
        for (int i = explosions.size - 1; i >= 0; i--) {
            Explosion explosion = explosions.get(i);
            explosion.update(dt);
            if (explosion.isFinished()) {
                explosions.removeIndex(i); 
            }
        }
        for (int i = heartExplosions.size - 1; i >= 0; i--) {
            Explosion explosion = heartExplosions.get(i);
            explosion.update(dt);
            if (explosion.isFinished()) {
                heartExplosions.removeIndex(i); 
            }
        }
    }

    private void triggerExplosion(float x, float y) {
        Explosion explosion = new Explosion(explosionFrames, 0.1f);
        explosion.trigger(x, y);
        explosions.add(explosion);
    }
    
    private void triggerHeartExplosion(float x, float y) {
        Explosion explosion = new Explosion(heartExplosionFrames, 0.1f);
        explosion.trigger(x, y);
        explosions.add(explosion);
    }

    public void dispose() {
        for (TextureRegion frame : explosionFrames) {
            frame.getTexture().dispose();
        }
        for (TextureRegion frame : heartExplosionFrames) {
            frame.getTexture().dispose();
        }
    }
}