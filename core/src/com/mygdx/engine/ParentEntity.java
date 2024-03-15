/* ParentEntity class for sub-entities to inherit */

package com.mygdx.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ParentEntity {
	private static int nextId = 1; // Static counter to generate unique IDs for each ParentEntity
	
	protected int id; // unique id to differentiate entity (keep track of specific entity for event handling)
    protected float x, y, width, height, scale;
    protected int speed;
    protected Texture texture;
    protected boolean isActive; // to identify entities that need to be updated and rendered (to be implemented)

    public ParentEntity(float x, float y, float width, float height, int speed, float scale, Texture texture) {
    	this.id = nextId++;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.scale = scale;
        this.texture = texture;
        this.isActive = true;
    }
    	
    // to be implemented abstract methods
    public abstract void update();
    public abstract void render(SpriteBatch batch, float dt);
    public abstract Class<? extends ParentEntity> getEntityType();

    // getter setter methods
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public boolean getIsActive() {
    	return isActive;
    }
    
    public void setIsActive(boolean isActive) {
    	this.isActive = isActive;
    }
    
   
    public Texture getTexture() {
        return texture;
    }
    
    public float getTextureWidth() {
        return texture.getWidth(); // width = texture width size
    }

    public float getTextureHeight() {
        return texture.getHeight(); // height = texture height size
    }
    
    public abstract void handleCollision(ParentEntity entityA, ParentEntity entityB);
    
    // concrete Parent entity methods 
//    public boolean isOffScreen() {
//    	if (getY() < 0 || getY() > Gdx.graphics.getHeight() || getX() < 0 || getX() > Gdx.graphics.getWidth()) {
//    		return true;
//    	}
//    	
//    	return false;
//    }
}
