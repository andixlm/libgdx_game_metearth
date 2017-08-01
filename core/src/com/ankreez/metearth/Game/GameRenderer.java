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

    public GameWorld getGameWorld() {
        return mGameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        mGameWorld = gameWorld;
    }

    public OrthographicCamera getCamera() {
        return mCamera;
    }

    public void setCamera(OrthographicCamera camera) {
        mCamera = camera;
    }

}
