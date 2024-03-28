package com.mygdx.engine;

public class ScoreManager {
    private static int score = 0;

    public static void addScore(int points) {
        score += points;
    }

    public static int getScore() {
        return score;
    }

    public static void resetScore() {
        score = 0;
    }
}
