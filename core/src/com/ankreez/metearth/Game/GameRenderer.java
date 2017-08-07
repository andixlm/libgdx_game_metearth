package com.ankreez.metearth.Game;

import com.ankreez.metearth.Helpers.AssetHelper;
import com.ankreez.metearth.Objects.Earth;
import com.ankreez.metearth.Objects.Meteorite;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.Objects.Wormhole;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GameRenderer {

    private static float MARGIN = 5.0f;

    private GameWorld mGameWorld;

    private short mScore;
    private String mScoreText;

    private Earth mEarth;
    private Wormhole mWormhole;
    private Array<Meteorite> mMeteorites;

    private TextureRegion mEarthTexture;
    private TextureRegion mPortalTexture;
    private TextureRegion mMeteoriteTexture;

    private OrthographicCamera mCamera;
    
    private SpriteBatch mSpriteRenderer;

    public GameRenderer(GameWorld gameWorld) {
        mGameWorld = gameWorld;

        initScore();
        initObjects();
        initAssets();

        mCamera = new OrthographicCamera();
        mCamera.setToOrtho(false, mGameWorld.getWorldWidth(), mGameWorld.getWorldHeight());

        mSpriteRenderer = new SpriteBatch();
        mSpriteRenderer.setProjectionMatrix(mCamera.combined);
    }

    public void render(float delta) {
        updateScore();

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

        AssetHelper.sFont.draw(mSpriteRenderer, mScoreText,
                (mGameWorld.getWorldWidth() - 6.0f * mScoreText.length()) / 2.0f,
                mGameWorld.getWorldHeight() - MARGIN);

        mSpriteRenderer.end();
    }

    private void initScore() {
        mScore = -1;
        mScoreText = "";
    }

    private void updateScore() {
        short currentScore = mGameWorld.getScore();

        if (mScore != currentScore) {
            mScore = currentScore;
            mScoreText = String.valueOf(mScore);
        }
    }

    private void initObjects() {
        mEarth = mGameWorld.getEarth();
        mWormhole = mGameWorld.getWormhole();
        mMeteorites = mGameWorld.getMeteorites();
    }

    private void initAssets() {
        mEarthTexture = AssetHelper.sEarthTexture;
        mPortalTexture = AssetHelper.sPortalTexture;
        mMeteoriteTexture = AssetHelper.sMeteoriteTexture;
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

}
