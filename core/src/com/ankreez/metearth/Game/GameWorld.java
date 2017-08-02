package com.ankreez.metearth.Game;

import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.Objects.Wormhole;

public class GameWorld {

    private float mWorldWidth;
    private float mWorldHeight;

    private Earth mEarth;
    private Wormhole mWormhole;

    public GameWorld(float worldWidth, float worldHeight) {
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;

        float earthWidth = mWorldWidth / 4.0f;
        float earthHeight = earthWidth;
        float earthPositionX = (mWorldWidth - earthWidth) / 2.0f;
        float earthPositionY = (mWorldHeight - earthHeight) / 2.0f;
        mEarth = new Earth(earthPositionX, earthPositionY, earthWidth, earthHeight);

        float portalRadius = earthWidth / 2.0f;
        float portalPositionX = (mWorldWidth - portalRadius) / 2.0f;
        float portalPositionY = (mWorldHeight - 2.0f * portalRadius) / 4.0f;
        mWormhole = new Wormhole(new Portal(portalPositionX, portalPositionY, portalRadius),
                new Portal(portalPositionX, mWorldHeight - portalPositionY, portalRadius));

    }

    public void update(float delta) {

    }

    public float getWorldWidth() {
        return mWorldWidth;
    }

    public float getWorldHeight() {
        return mWorldHeight;
    }

    public Earth getEarth() {
        return mEarth;
    }

    public Wormhole getWormhole() {
        return mWormhole;
    }

    public Portal getNextPortal() {
        return mWormhole.getNextPortal();
    }

}
