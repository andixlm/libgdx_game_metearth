package com.ankreez.metearth.Game;

import com.ankreez.metearth.Helpers.AssetHelper;
import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Meteorite;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.Objects.Space;
import com.ankreez.metearth.Objects.Star;
import com.ankreez.metearth.Objects.Wormhole;
import com.ankreez.metearth.UI.RoundButton;
import com.ankreez.metearth.UI.TextBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GameRenderer {

    private static float MARGIN = 10.0f;

    private GameWorld mGameWorld;

    private GameWorld.GameState mGameState;

    private float mWorldWidth;
    private float mWorldHeight;

    private short mScore;
    private short mHighScore;
    private String mScoreString;
    private String mHighScoreString;

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

    private TextureRegion mGameTitleTexture;
    private TextureRegion mAppealTexture;
    private TextureRegion mScoreTextTexture;
    private TextureRegion mHighScoreTextTexture;

    private TextureRegion mPlayButtonTexture;
    private TextureRegion mReplayButtonTexture;

    private TextureRegion mSoundCurrentStateTexture;
    private TextureRegion mSoundOnButtonTexture;
    private TextureRegion mSoundOffButtonTexture;

    private TextureRegion mStarTexture;
    private TextureRegion mEarthTexture;
    private TextureRegion mPortalTexture;
    private TextureRegion mMeteoriteTexture;

    private OrthographicCamera mCamera;

    private SpriteBatch mSpriteRenderer;

    public GameRenderer(GameWorld gameWorld) {
        mGameWorld = gameWorld;

        mGameState = mGameWorld.getGameState();

        mWorldWidth = mGameWorld.getWorldWidth();
        mWorldHeight = mGameWorld.getWorldHeight();

        initScore();
        initObjects();
        initAssets();

        updateSoundState(mGameWorld.getSoundState());

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, mWorldWidth, mWorldHeight);

        mSpriteRenderer = new SpriteBatch();
        mSpriteRenderer.setProjectionMatrix(mCamera.combined);
    }

    public void updateSoundState(boolean state) {
        mSoundCurrentStateTexture = state ? mSoundOnButtonTexture : mSoundOffButtonTexture;
    }

    private void initScore() {
        mScore = -1;
        mScoreString = "";

        mHighScore = -1;
        mHighScoreString = "";
    }

    public void updateScore(short score) {
        mScore = score;
        mScoreString = String.valueOf(mScore);
    }

    public void updateHighScore() {
        mHighScore = (short) AssetHelper.getHighScore();
        mHighScoreString = String.valueOf(mHighScore);
    }

    private void initObjects() {
        mGameTitle = mGameWorld.getGameTitle();
        mAppeal = mGameWorld.getAppeal();

        mScoreText = mGameWorld.getScoreText();
        mHighScoreText = mGameWorld.getHighScoreText();

        mPlayButton = mGameWorld.getPlayButton();
        mReplayButton = mGameWorld.getReplayButton();
        mSoundButton = mGameWorld.getSoundButton();

        mSpace = mGameWorld.getSpace();
        mEarth = mGameWorld.getEarth();
        mWormhole = mGameWorld.getWormhole();
        mMeteorites = mGameWorld.getMeteorites();
    }

    private void initAssets() {
        mGameTitleTexture = AssetHelper.sGameTitleTexture;
        mAppealTexture = AssetHelper.sAppealTexture;

        mScoreTextTexture = AssetHelper.sScoreTextTexture;
        mHighScoreTextTexture = AssetHelper.sHighScoreTextTexture;

        mPlayButtonTexture = AssetHelper.sPlayButtonTexture;
        mReplayButtonTexture = AssetHelper.sReplayButtonTexture;
        mSoundOnButtonTexture = AssetHelper.sSoundOnButtonTexture;
        mSoundOffButtonTexture = AssetHelper.sSoundOffButtonTexture;

        mStarTexture = AssetHelper.sStarTexture;
        mEarthTexture = AssetHelper.sEarthTexture;
        mPortalTexture = AssetHelper.sPortalTexture;
        mMeteoriteTexture = AssetHelper.sMeteoriteTexture;
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (mGameState) {
            case GAME_READY:
                renderOnGameReady(delta);
                break;

            case GAME_RUNNING:
                renderOnGameRunning(delta);
                break;

            case GAME_OVER:
                renderOnGameOver(delta);
                break;
        }
    }

    private void renderOnGameReady(float delta) {
        mSpriteRenderer.begin();

        for (Star star : mSpace) {
            mSpriteRenderer.draw(mStarTexture,
                    star.getX(), star.getY(), star.getWidth(), star.getHeight());
        }

        for (Meteorite meteorite : mMeteorites) {
            mSpriteRenderer.draw(mMeteoriteTexture,
                    meteorite.getX(), meteorite.getY(),
                    meteorite.getWidth() / 2.0f, meteorite.getHeight() / 2.0f,
                    meteorite.getWidth(), meteorite.getHeight(),
                    1.0f, 1.0f, meteorite.getRotation());
        }

        mSpriteRenderer.draw(mGameTitleTexture,
                mGameTitle.getX(), mGameTitle.getY(),
                mGameTitle.getWidth(), mGameTitle.getHeight());

        mSpriteRenderer.draw(mAppealTexture,
                mAppeal.getX(), mAppeal.getY(),
                mAppeal.getWidth(), mAppeal.getHeight());

        mSpriteRenderer.draw(mPlayButtonTexture,
                mPlayButton.getX(), mPlayButton.getY(),
                mPlayButton.getWidth(), mPlayButton.getHeight());

        mSpriteRenderer.draw(mSoundCurrentStateTexture,
                mSoundButton.getX(), mSoundButton.getY(),
                mSoundButton.getWidth(), mSoundButton.getHeight());

        mSpriteRenderer.end();
    }

    private void renderOnGameRunning(float delta) {
        mSpriteRenderer.begin();

        for (Star star : mSpace) {
            mSpriteRenderer.draw(mStarTexture,
                    star.getX(), star.getY(), star.getWidth(), star.getHeight());
        }

        mSpriteRenderer.draw(mEarthTexture,
                mEarth.getX(), mEarth.getY(),
                mEarth.getWidth(), mEarth.getHeight());

        for (Portal portal : mWormhole) {
            mSpriteRenderer.draw(mPortalTexture,
                    portal.getX(), portal.getY(),
                    portal.getWidth(), portal.getHeight());
        }

        for (Meteorite meteorite : mMeteorites) {
            mSpriteRenderer.draw(mMeteoriteTexture,
                    meteorite.getX(), meteorite.getY(),
                    meteorite.getWidth() / 2.0f, meteorite.getHeight() / 2.0f,
                    meteorite.getWidth(), meteorite.getHeight(),
                    1.0f, 1.0f, meteorite.getRotation());
        }

        AssetHelper.sFont.draw(mSpriteRenderer, mScoreString,
                (mWorldWidth - 6.0f * mScoreString.length()) / 2.0f, mWorldHeight - MARGIN);

        mSpriteRenderer.end();
    }

    private void renderOnGameOver(float delta) {
        mSpriteRenderer.begin();

        for (Star star : mSpace) {
            mSpriteRenderer.draw(mStarTexture,
                    star.getX(), star.getY(), star.getWidth(), star.getHeight());
        }

        mSpriteRenderer.draw(mScoreTextTexture,
                mScoreText.getX(), mScoreText.getY(),
                mScoreText.getWidth(), mScoreText.getHeight());

        AssetHelper.sFont.draw(mSpriteRenderer, mScoreString,
                mScoreText.getX() + 6.5f * mScoreText.getWidth() / 6.0f,
                mScoreText.getY() + 0.7f * mScoreText.getHeight());

        mSpriteRenderer.draw(mHighScoreTextTexture,
                mHighScoreText.getX(), mHighScoreText.getY(),
                mHighScoreText.getWidth(), mHighScoreText.getHeight());

        AssetHelper.sFont.draw(mSpriteRenderer, mHighScoreString,
                mHighScoreText.getX() + 11.5f * mHighScoreText.getWidth() / 11.0f,
                mHighScoreText.getY() + 0.7f * mHighScoreText.getHeight());

        mSpriteRenderer.draw(mReplayButtonTexture,
                mReplayButton.getX(), mReplayButton.getY(),
                mReplayButton.getWidth(), mReplayButton.getHeight());

        mSpriteRenderer.draw(mSoundCurrentStateTexture,
                mSoundButton.getX(), mSoundButton.getY(),
                mSoundButton.getWidth(), mSoundButton.getHeight());

        mSpriteRenderer.end();
    }

    public GameWorld getGameWorld() {
        return mGameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        mGameWorld = gameWorld;
    }

    public OrthographicCamera getCamera() {
        return mCamera;
    }

    public void setCamera(OrthographicCamera camera) {
        mCamera = camera;
    }

    public GameWorld.GameState getGameState() {
        return mGameState;
    }

    public void setGameState(GameWorld.GameState gameState) {
        mGameState = gameState;
    }

}
