package com.ankreez.metearth.Game;

import com.ankreez.metearth.Helpers.AssetHelper;
import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Meteorite;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.Objects.Space;
import com.ankreez.metearth.Objects.Wormhole;
import com.ankreez.metearth.UI.RoundButton;
import com.ankreez.metearth.UI.TextBox;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

    public enum GameState { GAME_READY, GAME_RUNNING, GAME_OVER }

    private static final byte METEORITES_AMOUNT = 7;

    private float mWorldWidth;
    private float mWorldHeight;

    private GameState mGameState;
    private boolean mSoundState;
    private short mScore;

    private TextBox mGameTitle;
    private TextBox mAppeal;

    private TextBox mScoreText;
    private TextBox mHighScoreText;

    private RoundButton mPlayButton;
    private RoundButton mReplayButton;
    private RoundButton mSoundButton;

    private Space mSpace;
    private Earth mEarth;
    private Wormhole mWormhole;
    private Array<Meteorite> mMeteorites;

    private OnGameStateChangeListener mOnGameStateChangeListener;
    private OnSoundStateChangeListener mOnSoundStateChangeListener;
    private OnScoreChangeListener mOnScoreChangeListener;

    public GameWorld(float worldWidth, float worldHeight) {
        mWorldWidth = worldWidth;
        mWorldHeight = worldHeight;

        mSoundState = AssetHelper.getSoundState();

        initObjects();

        mGameState = GameState.GAME_READY;
    }

    private void initObjects() {
        float gameTitleWidth = mWorldWidth / 1.25f;
        float gameTitleHeight = gameTitleWidth / 9.0f;
        float gameTitlePositionX = (mWorldWidth - gameTitleWidth) / 2.0f;
        float gameTitlePositionY = 7.5f * mWorldHeight / 10.0f;
        mGameTitle = new TextBox(gameTitlePositionX, gameTitlePositionY,
                gameTitleWidth, gameTitleHeight);

        float appealWidth = mWorldWidth / 2.0f;
        float appealHeight = appealWidth / 16.875f;
        float appealPositionX = 1.0f * mWorldWidth / 2.0f;
        float appealPositionY = 0.0f;
        mAppeal = new TextBox(appealPositionX, appealPositionY, appealWidth, appealHeight);

        float scoreTextWidth = mWorldWidth / 2.5f;
        float scoreTextHeight = scoreTextWidth / 5.5f;
        float scoreTextPositionX = (mWorldWidth - 7.5f * scoreTextWidth / 6.0f) / 2.0f;
        float scoreTextPositionY = 2.25f * mWorldHeight / 10.0f - scoreTextHeight;
        mScoreText = new TextBox(scoreTextPositionX, scoreTextPositionY,
                scoreTextWidth, scoreTextHeight);

        float highScoreTextWidth = mWorldWidth / 1.5f;
        float highScoreTextHeight = highScoreTextWidth / 10.0f;
        float highScoreTextPositionX = (mWorldWidth - 12.5f * highScoreTextWidth / 11.0f) / 2.0f;
        float highScoreTextPositionY = 1.5f * mWorldHeight / 10.0f - highScoreTextHeight;
        mHighScoreText = new TextBox(highScoreTextPositionX, highScoreTextPositionY,
                highScoreTextWidth, highScoreTextHeight);

        float earthWidth = mWorldWidth / 2.5f;
        float earthHeight = earthWidth;
        float earthPositionX = (mWorldWidth - earthWidth) / 2.0f;
        float earthPositionY = (mWorldHeight - earthHeight) / 2.0f;

        mPlayButton = new RoundButton(earthPositionX, earthPositionY, earthWidth, earthHeight);
        mReplayButton = new RoundButton(earthPositionX, earthPositionY, earthWidth, earthHeight);

        float soundButtonWidth = mWorldWidth / 7.5f;
        float soundButtonHeight = soundButtonWidth;
        float soundButtonPositionX = mWorldWidth - 9.0f * soundButtonWidth / 8.0f;
        float soundButtonPositionY = mWorldHeight - 9.0f * soundButtonHeight / 8.0f;
        mSoundButton = new RoundButton(soundButtonPositionX, soundButtonPositionY,
                soundButtonWidth, soundButtonHeight);

        mSpace = new Space(mWorldWidth, mWorldHeight);

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
        for (Meteorite meteorite : mMeteorites) {
            meteorite.update(delta);

            if (meteorite.isOutOfScreen(mWorldWidth, mWorldHeight)) {
                meteorite.reset(mWorldWidth, mWorldHeight);
            }
        }
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
                        if (mSoundState) {
                            AssetHelper.sTeleportSound.play();
                        }
                        increaseScore();
                    }
                } else if (meteorite.collides(mEarth)) {
                    if (mSoundState) {
                        AssetHelper.sCollisionSound.play();
                    }
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

    public void switchSoundState() {
        mSoundState = !mSoundState;
        mOnSoundStateChangeListener.onSoundStateChange(mSoundState);
    }

    public boolean isGameReady() {
        return mGameState == GameState.GAME_READY;
    }

    public void startGame() {
        for (Meteorite meteorite : mMeteorites) {
            meteorite.reset(mWorldWidth, mWorldHeight);
        }

        mGameState = GameState.GAME_RUNNING;

        mOnGameStateChangeListener.onGameStateChange(mGameState);

        mOnScoreChangeListener.onScoreChange(mScore = 0);
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
        mOnScoreChangeListener.onScoreChange(mScore = 0);

        mWormhole.onRestart();

        for (Meteorite meteorite : mMeteorites) {
            meteorite.onRestart(mWorldWidth, mWorldHeight);
        }

        mGameState = GameState.GAME_RUNNING;

        mOnGameStateChangeListener.onGameStateChange(mGameState);
    }

    public void increaseScore() {
        mOnScoreChangeListener.onScoreChange(++mScore);
    }

    public void updateHighScore() {
        if (mScore > AssetHelper.getHighScore()) {
            AssetHelper.setHighScore(mScore);
        }
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

    public boolean getSoundState() {
        return mSoundState;
    }

    public void setSoundState(boolean state) {
        mSoundState = state;
        mOnSoundStateChangeListener.onSoundStateChange(mSoundState);
    }

    public short getScore() {
        return mScore;
    }

    public void setScore(short score) {
        mScore = score > 0 ? score : 0;
    }

    public TextBox getGameTitle() {
        return mGameTitle;
    }

    public TextBox getAppeal() {
        return mAppeal;
    }

    public TextBox getScoreText() {
        return mScoreText;
    }

    public TextBox getHighScoreText() {
        return mHighScoreText;
    }

    public RoundButton getPlayButton() {
        return mPlayButton;
    }

    public RoundButton getReplayButton() {
        return mReplayButton;
    }

    public RoundButton getSoundButton() {
        return mSoundButton;
    }

    public Space getSpace() {
        return mSpace;
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

    public OnSoundStateChangeListener getOnSoundStateChangeListener() {
        return mOnSoundStateChangeListener;
    }

    public void setOnSoundStateChangeListener(OnSoundStateChangeListener listener) {
        mOnSoundStateChangeListener = listener;
    }

    public OnScoreChangeListener getOnScoreChangeListener() {
        return mOnScoreChangeListener;
    }

    public void setOnScoreChangeListener(OnScoreChangeListener listener) {
        mOnScoreChangeListener = listener;
    }

    public interface OnGameStateChangeListener {

        public void onGameStateChange(GameWorld.GameState gameState);

    }

    public interface OnSoundStateChangeListener {

        public void onSoundStateChange(boolean state);

    }

    public interface OnScoreChangeListener {

        public void onScoreChange(short score);

    }

}
