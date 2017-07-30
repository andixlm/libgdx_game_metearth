package com.ankreez.metearth.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameRenderer {

    private GameWorld mGameWorld;
    private OrthographicCamera mCamera;

    public GameRenderer(GameWorld gameWorld) {
        mGameWorld = gameWorld;

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, mGameWorld.getWorldWidth(), mGameWorld.getWorldHeight());
    }

    public void render(float delta) {

    }

}
