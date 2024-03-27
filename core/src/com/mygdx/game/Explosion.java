package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Explosion {
    private Animation<TextureRegion> animation;
    private float stateTime;
    private float x, y;
    private boolean active;

    public Explosion(Array<TextureRegion> frames, float frameDuration, float x, float y) {
        this.animation = new Animation<>(frameDuration, frames);
        this.stateTime = 0;
        this.x = x;
        this.y = y;
        this.active = false;
    }
    
    public Explosion(Array<TextureRegion> frames, float frameDuration) {
        this.animation = new Animation<>(frameDuration, frames);
        this.stateTime = 0;
        this.active = false;
    }
    
    public Explosion trigger(float x, float y) {
        this.x = x;
        this.y = y;
        this.stateTime = 0;
        this.active = true;
        return this;
    }

    public void update(float deltaTime) {
        if (active) {
            stateTime += deltaTime;
        }
    }

    public boolean isFinished() {
        return !active || animation.isAnimationFinished(stateTime);
    }

    public void render(SpriteBatch batch, float scale) {
        if (active) {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime, false);
            float width = currentFrame.getRegionWidth() * scale;
            float height = currentFrame.getRegionHeight() * scale;
            batch.draw(currentFrame, x, y, width, height);
        }
    }

    public void dispose() {
        // Dispose any resources if needed
    }
}
