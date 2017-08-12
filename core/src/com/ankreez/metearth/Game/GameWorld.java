package com.ankreez.metearth.Game;

import com.ankreez.metearth.Helpers.OnGameStateChangeListener;
import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Meteorite;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.Objects.Wormhole;
import com.ankreez.metearth.UI.RoundButton;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

    public enum GameState { GAME_READY, GAME_RUNNING, GAME_OVER }

    private static final byte METEORITES_AMOUNT = 5;

    private float mWorldWidth;
    private float mWorldHeight;

    private GameState mGameState;
    private short mScore;

    private RoundButton mPlayButton;
    private RoundButton mReplayButton;

    private Earth mEarth;
    private Wormhole mWormhole;
    private Array<Meteorite> mMeteorites;

    private OnGameStateChangeListener mOnGameStateChangeListener;

    public GameWorld(float worldWidth, float worldHeight) {
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;

        mScore = 0;

        initObjects();

        mGameState = GameState.GAME_READY;
    }

    private void initObjects() {
        float earthWidth = mWorldWidth / 2.5f;
        float earthHeight = earthWidth;
        float earthPositionX = (mWorldWidth - earthWidth) / 2.0f;
        float earthPositionY = (mWorldHeight - earthHeight) / 2.0f;

        mPlayButton = new RoundButton(earthPositionX, earthPositionY, earthWidth, earthHeight);
        mReplayButton = new RoundButton(earthPositionX, earthPositionY, earthWidth, earthHeight);

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
        switch (mGameState) {
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
        return mGameState == GameState.GAME_READY;
    }

    public void startGame() {
        mGameState = GameState.GAME_RUNNING;

        mOnGameStateChangeListener.onGameStateChange(mGameState);
    }

    public boolean isGameRunning() {
        return mGameState == GameState.GAME_RUNNING;
    }

    public void stopGame() {
        mGameState = GameState.GAME_OVER;

        mOnGameStateChangeListener.onGameStateChange(mGameState);
    }

    public boolean isGameOver() {
        return mGameState == GameState.GAME_OVER;
    }

    public void restartGame() {
        mScore = 0;

        mWormhole.onRestart();

        for (Meteorite meteorite : mMeteorites) {
            meteorite.onRestart(mWorldWidth, mWorldHeight);
        }

        mGameState = GameState.GAME_RUNNING;

        mOnGameStateChangeListener.onGameStateChange(mGameState);
    }

    public void increaseScore() {
        ++mScore;
    }

    public GameState getGameState() {
        return mGameState;
    }

    public void setGameState(GameState gameState) {
        mGameState = gameState;
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

    public RoundButton getPlayButton() {
        return mPlayButton;
    }

    public RoundButton getReplayButton() {
        return mReplayButton;
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

    public OnGameStateChangeListener getOnGameStateChangeListener() {
        return mOnGameStateChangeListener;
    }

    public void setOnGameStateChangeListener(OnGameStateChangeListener listener) {
        mOnGameStateChangeListener = listener;
    }

}
