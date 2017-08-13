package com.ankreez.metearth.Game;

import com.ankreez.metearth.Helpers.AssetHelper;
import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Meteorite;
import com.ankreez.metearth.Objects.Portal;
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
    private String mScoreString;

    private TextBox mGameTitle;
    private TextBox mAppeal;

    private TextBox mScoreText;

    private RoundButton mPlayButton;
    private RoundButton mReplayButton;

    private Earth mEarth;
    private Wormhole mWormhole;
    private Array<Meteorite> mMeteorites;

    private TextureRegion mGameTitleTexture;
    private TextureRegion mAppealTexture;
    private TextureRegion mScoreTextTexture;

    private TextureRegion mPlayButtonTexture;
    private TextureRegion mReplayButtonTexture;

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

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, mWorldWidth, mWorldHeight);

        mSpriteRenderer = new SpriteBatch();
        mSpriteRenderer.setProjectionMatrix(mCamera.combined);
    }

    private void initScore() {
        mScore = -1;
        mScoreString = "";
    }

    private void updateScore() {
        short currentScore = mGameWorld.getScore();

        if (mScore != currentScore) {
            mScore = currentScore;
            mScoreString = String.valueOf(mScore);
        }
    }

    private void initObjects() {
        mGameTitle = mGameWorld.getGameTitle();
        mAppeal = mGameWorld.getAppeal();

        mScoreText = mGameWorld.getScoreText();

        mPlayButton = mGameWorld.getPlayButton();
        mReplayButton = mGameWorld.getReplayButton();

        mEarth = mGameWorld.getEarth();
        mWormhole = mGameWorld.getWormhole();
        mMeteorites = mGameWorld.getMeteorites();
    }

    private void initAssets() {
        mGameTitleTexture = AssetHelper.sGameTitleTexture;
        mAppealTexture = AssetHelper.sAppealTexture;

        mScoreTextTexture = AssetHelper.sScoreTextTexture;

        mPlayButtonTexture = AssetHelper.sPlayButtonTexture;
        mReplayButtonTexture = AssetHelper.sReplayButtonTexture;

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

        mSpriteRenderer.end();
    }

    private void renderOnGameRunning(float delta) {
        updateScore();

        mSpriteRenderer.begin();

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

        mSpriteRenderer.draw(mReplayButtonTexture,
                mReplayButton.getX(), mReplayButton.getY(),
                mReplayButton.getWidth(), mReplayButton.getHeight());

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
