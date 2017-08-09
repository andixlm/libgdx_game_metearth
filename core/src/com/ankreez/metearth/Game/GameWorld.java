package com.ankreez.metearth.Game;

import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Meteorite;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.Objects.Wormhole;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

    public enum GameState { GAME_READY, GAME_RUNNING, GAME_OVER }

    private static final byte METEORITES_AMOUNT = 5;

    private float mWorldWidth;
    private float mWorldHeight;

    private GameState mState;
    private short mScore;

    private Earth mEarth;
    private Wormhole mWormhole;
    private Array<Meteorite> mMeteorites;

    public GameWorld(float worldWidth, float worldHeight) {
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;

        mScore = 0;

        initObjects();

        // TODO: First game state must be READY.
        startGame();
    }

    private void initObjects() {
        float earthWidth = mWorldWidth / 2.5f;
        float earthHeight = earthWidth;
        float earthPositionX = (mWorldWidth - earthWidth) / 2.0f;
        float earthPositionY = (mWorldHeight - earthHeight) / 2.0f;
        mEarth = new Earth(earthPositionX, earthPositionY, earthWidth, earthHeight);

        float portalRadius = (earthWidth / 2.0f) / 2.25f;
        float portalPositionX = mWorldWidth / 2.0f - portalRadius;
        float portalPositionY = mWorldHeight / 4.0f - portalRadius;
        mWormhole = new Wormhole(
                new Portal(portalPositionX, portalPositionY,
                        portalRadius),
                new Portal(portalPositionX, mWorldHeight - (portalPositionY + 2.0f * portalRadius),
                        portalRadius)
        );

        float meteoriteRadius = portalRadius / 2.0f;
        mMeteorites = new Array<Meteorite>(false, METEORITES_AMOUNT);
        for (int count = 0; count < METEORITES_AMOUNT; ++count) {
            mMeteorites.add(new Meteorite(meteoriteRadius, mWorldWidth, mWorldHeight));
        }
    }

    public void update(float delta) {
        switch (mState) {
            case GAME_READY:
                updateOnGameReady(delta);
                break;

            case GAME_RUNNING:
                updateOnGameRunning(delta);
                break;

            case GAME_OVER:
                updateOnGameOver(delta);
                break;
        }
    }

    private void updateOnGameReady(float delta) {

    }

    private void updateOnGameRunning(float delta) {
        for (Meteorite meteorite : mMeteorites) {
            if (meteorite.isMoving()) {
                meteorite.update(delta);

                Portal portalCollided = meteorite.collides(mWormhole);
                if (portalCollided != null) {
                    Portal outPortal = portalCollided.getOutPortal();
                    Rectangle outBounds = outPortal.getBounds();

                    float outX = outPortal.getX() + outPortal.getRadius() - meteorite.getRadius() +
                            Math.signum(meteorite.getVelocity().x) *
                                    (meteorite.getRadius() + outBounds.getWidth() / 2.0f);
                    float outY = outPortal.getY() + outPortal.getRadius() - meteorite.getRadius() +
                            Math.signum(meteorite.getVelocity().y) *
                                    (meteorite.getRadius() + outBounds.getHeight() / 2.0f);

                    meteorite.setPosition(outX, outY);

                    // Portals shouldn't be near to each other to count score.
                    // Condition is inverted overlapping.
                    if (portalCollided.getX() >= outPortal.getX() + outPortal.getWidth() ||
                            outPortal.getX() >= portalCollided.getX() + portalCollided.getWidth() ||
                            portalCollided.getY() >= outPortal.getY() + outPortal.getHeight() ||
                            outPortal.getY() >= portalCollided.getY() + portalCollided.getHeight()) {
                        increaseScore();
                    }
                } else if (meteorite.collides(mEarth)) {
                    stopGame();
                }

                if (meteorite.isOutOfScreen(mWorldWidth, mWorldHeight)) {
                    meteorite.reset(mWorldWidth, mWorldHeight);
                }
            }
        }
    }

    private void updateOnGameOver(float delta) {

    }

    public boolean isGameReady() {
        return mState == GameState.GAME_READY;
    }

    public void startGame() {
        mState = GameState.GAME_RUNNING;
    }

    public boolean isGameRunning() {
        return mState == GameState.GAME_RUNNING;
    }

    public void stopGame() {
        mState = GameState.GAME_OVER;
    }

    public boolean isGameOver() {
        return mState == GameState.GAME_OVER;
    }

    public void increaseScore() {
        ++mScore;
    }

    public GameState getGameState() {
        return mState;
    }

    public void setGameState(GameState gameState) {
        mState = gameState;
    }

    public float getWorldWidth() {
        return mWorldWidth;
    }

    public float getWorldHeight() {
        return mWorldHeight;
    }

    public short getScore() {
        return mScore;
    }

    public void setScore(short score) {
        mScore = score > 0 ? score : 0;
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
