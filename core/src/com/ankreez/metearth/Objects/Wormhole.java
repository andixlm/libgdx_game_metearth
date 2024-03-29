package com.ankreez.metearth.Objects;

import com.ankreez.metearth.Helpers.AssetHelper;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public class Wormhole implements Iterable<Portal> {

    private static final byte PORTALS_AMOUNT = 2;

    private static final byte ALPHA_PORTAL_ID = 0;
    private static final byte BETA_PORTAL_ID = 1;

    private byte mPortalId;
    private Portal[] mPortals;

    private Vector2 mAlphaInitialPosition;
    private Vector2 mBetaInitialPosition;

    public Wormhole(Portal alphaPortal, Portal betaPortal) {
        if (alphaPortal == null || betaPortal == null) {
            throw new IllegalArgumentException();
        }

        mAlphaInitialPosition = new Vector2(alphaPortal.getPosition());
        mBetaInitialPosition = new Vector2(betaPortal.getPosition());

        alphaPortal.setOutPortal(betaPortal);
        betaPortal.setOutPortal(alphaPortal);

        mPortals = new Portal[PORTALS_AMOUNT];

        mPortals[ALPHA_PORTAL_ID] = alphaPortal;
        mPortals[BETA_PORTAL_ID] = betaPortal;

        resetId();
    }

    private void resetId() {
        mPortalId = (AssetHelper.sRandom.nextFloat() < 0.5f) ? ALPHA_PORTAL_ID : BETA_PORTAL_ID;
    }

    public Portal getNextPortal() {
        if (mPortalId == ALPHA_PORTAL_ID) {
            mPortalId = BETA_PORTAL_ID;

            return mPortals[ALPHA_PORTAL_ID];
        } else {
            mPortalId = ALPHA_PORTAL_ID;

            return mPortals[BETA_PORTAL_ID];
        }
    }

    public void onRestart() {
        resetId();

        mPortals[ALPHA_PORTAL_ID].setPosition(mAlphaInitialPosition);
        mPortals[BETA_PORTAL_ID].setPosition(mBetaInitialPosition);

        mPortals[ALPHA_PORTAL_ID].onRestart();
        mPortals[BETA_PORTAL_ID].onRestart();
    }

    public Portal getAlphaPortal() {
        return mPortals[ALPHA_PORTAL_ID];
    }

    public Portal getBetaPortal() {
        return mPortals[BETA_PORTAL_ID];
    }

    @Override
    public Iterator<Portal> iterator() {
        return new WormholeIterator();
    }

    private class WormholeIterator implements Iterator<Portal> {

        private byte mCurrentPortalId;

        private WormholeIterator() {
            mCurrentPortalId = ALPHA_PORTAL_ID;
        }

        @Override
        public boolean hasNext() {
            return mCurrentPortalId <= BETA_PORTAL_ID;
        }

        @Override
        public Portal next() {
            return mPortals[mCurrentPortalId++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
