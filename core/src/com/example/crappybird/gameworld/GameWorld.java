package com.example.crappybird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.example.crappybird.gamebojects.Bird;
import com.example.crappybird.gamebojects.ScrollHandler;
import com.example.crappybird.helpers.AssetLoader;


public class GameWorld {

    private Bird bird;
    private ScrollHandler scrollHandler;
    private Rectangle ground;
    private int score = 0, midPointY;
    private GameState currentState;


    public GameWorld(int midPoint) {
        bird = new Bird(33, midPoint - 5, 17, 12);
        scrollHandler = new ScrollHandler(this, midPoint + 63);
        ground = new Rectangle(0, midPoint + 66, 136, 11);
        currentState = GameState.READY;
        midPointY = midPoint;
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }
    }

    public void updateReady(float delta) {

    }

    public void updateRunning(float delta) {
        Gdx.app.log("GameWorld", "update");
        bird.update(delta);
        scrollHandler.update(delta);

        if (scrollHandler.collides(bird) && bird.isAlive()) {
            scrollHandler.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;
        }
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scrollHandler.onRestart();
        currentState = GameState.READY;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public int getScore() {
        return score;
    }

    public enum GameState {

        READY, RUNNING, GAMEOVER

    }
}
