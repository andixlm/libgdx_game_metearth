package com.ankreez.metearth.Objects;

import java.util.Iterator;

public class Wormhole implements Iterable<Portal> {

    private static final byte PORTALS_AMOUNT = 2;

    private static final byte ALPHA_PORTAL_ID = 0;
    private static final byte BETA_PORTAL_ID = 1;

    private byte mPortalId;
    private Portal[] mPortals;

    public Wormhole(Portal alphaPortal, Portal betaPortal) {
        if (alphaPortal == null || betaPortal == null) {
            throw new IllegalArgumentException();
        }

        alphaPortal.setOutPortal(betaPortal);
        betaPortal.setOutPortal(alphaPortal);

        mPortals = new Portal[PORTALS_AMOUNT];

        mPortals[ALPHA_PORTAL_ID] = alphaPortal;
        mPortals[BETA_PORTAL_ID] = betaPortal;

        mPortalId = ALPHA_PORTAL_ID;
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
