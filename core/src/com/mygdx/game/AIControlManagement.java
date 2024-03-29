package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.AIBehaviour;
import com.mygdx.engine.ParentEntity;

public class AIControlManagement implements AIBehaviour{
    private static final int HEALTH_POWERUP_SPEED = 3;

    @Override
    public void updateAI(ParentEntity entity) {
        if (entity instanceof HealthPowerUp) {
            HealthPowerUp healthPowerUp = (HealthPowerUp) entity;

            healthPowerUp.setY(healthPowerUp.getY() - HEALTH_POWERUP_SPEED);

            if (MathUtils.randomBoolean()) {
                healthPowerUp.setX(healthPowerUp.getX() + HEALTH_POWERUP_SPEED);
            } else {
                healthPowerUp.setX(healthPowerUp.getX() - HEALTH_POWERUP_SPEED);
            }
        } else {
            throw new IllegalArgumentException("Unsupported entity type for AI control");
        }
    }
}
