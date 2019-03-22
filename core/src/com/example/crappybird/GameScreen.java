package com.example.crappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.example.crappybird.gameworld.GameRenderer;
import com.example.crappybird.gameworld.GameWorld;
import com.example.crappybird.helpers.InputHandler;

public class GameScreen implements Screen {
    public static final String TAG = "GameScreen";

    private GameRenderer renderer;
    private GameWorld world;
    private float runTime = 0f;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, midPointY, (int) gameHeight);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "attached.");
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "Resizing.");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
