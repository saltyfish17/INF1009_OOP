package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.ParentEntity;
import com.mygdx.engine.iEntityFactory;

public class BulletFactory implements iEntityFactory<Bullet, GameEntityManager> {
    private static final int BULLET_SPEED = 10;
    private static final float BULLET_SCALE = 0.05f;
    
    private Texture tex;
    private GameEntityManager entityManager;
    private int entityId; // the BulletFactory belongs to this entityId only

	public BulletFactory(Texture tex, GameEntityManager entityManager, int entityId) {
    	this.tex = tex;
    	this.entityManager = entityManager;
    	this.entityId = entityId;
    }
	
    public int getEntityId() {
		return entityId;
	}

	@Override
	public void createEntity(int i) {
		ParentEntity entity = entityManager.getEntityById(getEntityId());
		float bulletWidth = 5; // Set the desired width of the bullet
        float bulletHeight = 10; // Set the desired height of the bullet
        
        if (entity instanceof Player && entity.getEntityType().equals(Player.class)) {
        	for (int count=0; count<i; count++) {
            	Bullet bullet = new Bullet(entity.getX()+22, entity.getY()+25, bulletWidth, bulletHeight, BULLET_SPEED, BULLET_SCALE, this.tex, this.entityManager, Bullet.BulletType.PLAYER);
        		entityManager.addEntity(bullet);
            }
        } else if (entity instanceof Drone && entity.getEntityType().equals(Drone.class)) {
        	Bullet bullet = new Bullet(entity.getX()+22, entity.getY()-25, bulletWidth, bulletHeight, BULLET_SPEED, BULLET_SCALE, this.tex, this.entityManager, Bullet.BulletType.DRONE);
    		entityManager.addEntity(bullet);
    		System.out.println(bullet.getBulletType());
        }
        
	}

}
