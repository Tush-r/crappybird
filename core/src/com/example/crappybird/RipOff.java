package com.example.crappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.example.crappybird.helpers.AssetLoader;

public class RipOff extends Game {
    public static final String TAG = "RipOff";

    @Override
    public void create() {
        Gdx.app.log(TAG, "Game created.");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
