package com.ankreez.metearth.Objects;

import com.ankreez.metearth.Helpers.AssetHelper;

import java.util.Iterator;

public class Space implements Iterable<Star> {

    private static final int STARS_AMOUNT = 13;

    private static final float STAR_RADIUS = 2.5f;

    private Star[] mStars;

    public Space(float worldWidth, float worldHeight) {
        mStars = new Star[STARS_AMOUNT];

        initStars();
        resetStars(worldWidth, worldHeight);
    }

    private void initStars() {
        for (int idx = 0; idx < STARS_AMOUNT; ++idx) {
            mStars[idx] = new Star(0.0f, 0.0f, STAR_RADIUS, STAR_RADIUS);
        }
    }

    private void resetStars(float worldWidth, float worldHeight) {
        for (int idx = 0; idx < STARS_AMOUNT; ++idx) {
            mStars[idx].setPosition(AssetHelper.sRandom.nextFloat() * worldWidth,
                    AssetHelper.sRandom.nextFloat() * worldHeight);
        }
    }

    @Override
    public Iterator<Star> iterator() {
        return new SpaceIterator();
    }

    private class SpaceIterator implements Iterator<Star> {

        private int mIndex;

        private SpaceIterator() {
            mIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return mIndex < STARS_AMOUNT;
        }

        @Override
        public Star next() {
            return mStars[mIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
