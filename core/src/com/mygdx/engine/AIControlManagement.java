package com.mygdx.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.HealthPowerUp;

public class AIControlManagement {
    private static final int HEALTH_POWERUP_SPEED = 3;
  

    public void updateHealthPowerUp(HealthPowerUp healthPowerUp) {
        // Move the health power-up downward
        healthPowerUp.setY(healthPowerUp.getY() - HEALTH_POWERUP_SPEED);

        // Randomly move left or right
        if (MathUtils.randomBoolean()) {
            healthPowerUp.setX(healthPowerUp.getX() + HEALTH_POWERUP_SPEED);
        } else {
            healthPowerUp.setX(healthPowerUp.getX() - HEALTH_POWERUP_SPEED);
        }
    }
}
