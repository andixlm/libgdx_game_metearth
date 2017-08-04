package com.ankreez.metearth.Game;

import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Meteorite;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.Objects.Wormhole;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

    private static final byte METEORITES_COUNT = 7;

    private float mWorldWidth;
    private float mWorldHeight;

    private Earth mEarth;
    private Wormhole mWormhole;
    private Array<Meteorite> mMeteorites;

    public GameWorld(float worldWidth, float worldHeight) {
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;

        float earthWidth = mWorldWidth / 2.5f;
        float earthHeight = earthWidth;
        float earthPositionX = (mWorldWidth - earthWidth) / 2.0f;
        float earthPositionY = (mWorldHeight - earthHeight) / 2.0f;
        mEarth = new Earth(earthPositionX, earthPositionY, earthWidth, earthHeight);

        float portalRadius = (earthWidth / 2.0f) / 2.25f;
        float portalPositionX = (mWorldWidth - 2.0f * portalRadius) / 2.0f;
        float portalPositionY = (mWorldHeight - 4.0f * portalRadius) / 4.0f;
        mWormhole = new Wormhole(
                new Portal(portalPositionX, portalPositionY,
                        portalRadius),
                new Portal(portalPositionX, mWorldHeight - (portalPositionY + 2.0f * portalRadius),
                        portalRadius)
        );

        float meteoriteRadius = portalRadius / 2.0f;
        mMeteorites = new Array<Meteorite>(false, METEORITES_COUNT);
        for (int count = 0; count < METEORITES_COUNT; ++count) {
            mMeteorites.add(new Meteorite(meteoriteRadius, mWorldWidth, mWorldHeight));
        }
    }

    public void update(float delta) {
        for (Meteorite meteorite : mMeteorites) {
            meteorite.update(delta);
        }
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

    public Array<Meteorite> getMeteorites() {
        return mMeteorites;
    }

}
