package com.ankreez.metearth.Game;

import com.ankreez.metearth.Objects.Earth;

public class GameWorld {

    private float mWorldWidth;
    private float mWorldHeight;

    private Earth mEarth;

    public GameWorld(float worldWidth, float worldHeight) {
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;

        float earthWidth = mWorldWidth / 4.0f;
        float earthHeight = earthWidth;
        mEarth = new Earth((mWorldWidth - earthWidth) / 2.0f, (mWorldHeight - earthHeight) / 2.0f,
                earthWidth, earthHeight);
    }

    public void update(float delta) {

    }

    public float getGameWidth() {
        return mWorldWidth;
    }

    public float getGameHeight() {
        return mWorldHeight;
    }

    public Earth getEarth() {
        return mEarth;
    }

}
