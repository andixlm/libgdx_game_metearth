package com.ankreez.metearth.Game;

public class GameWorld {

    private float mWorldWidth;
    private float mWorldHeight;

    public GameWorld(float worldWidth, float worldHeight) {
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;
    }

    public void update(float delta) {

    }

    public float getGameWidth() {
        return mWorldWidth;
    }

    public float getGameHeight() {
        return mWorldHeight;
    }

}
