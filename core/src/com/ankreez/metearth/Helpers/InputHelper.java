package com.ankreez.metearth.Helpers;

import com.ankreez.metearth.Game.GameRenderer;
import com.ankreez.metearth.Game.GameWorld;
import com.ankreez.metearth.Objects.Portal;
import com.ankreez.metearth.UI.RoundButton;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputHelper implements InputProcessor {

    private GameWorld mGameWorld;

    private GameWorld.GameState mGameState;

    private RoundButton mPlayButton;
    private RoundButton mReplayButton;

    private Portal mPortal;
    private float mPortalHalfWidth;
    private float mPortalHalfHeight;

    private GameRenderer mGameRenderer;
    private OrthographicCamera mCamera;

    private Vector3 mWorldCoords;

    public InputHelper(GameWorld gameWorld, GameRenderer gameRenderer) {
        mGameWorld = gameWorld;

        mGameState = mGameWorld.getGameState();

        mPlayButton = mGameWorld.getPlayButton();
        mReplayButton = mGameWorld.getReplayButton();

        mPortal = mGameWorld.getNextPortal();
        mPortalHalfWidth = mPortal.getWidth() / 2.0f;
        mPortalHalfHeight = mPortal.getHeight() / 2.0f;

        mGameRenderer = gameRenderer;
        mCamera = mGameRenderer.getCamera();

        mWorldCoords = new Vector3();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mCamera.unproject(mWorldCoords.set((float) screenX, (float) screenY, 0.0f));

        int worldX = (int) mWorldCoords.x;
        int worldY = (int) mWorldCoords.y;

        switch (mGameState) {
            case GAME_READY:
                touchDownOnGameReady(worldX, worldY, pointer, button);
                break;

            case GAME_RUNNING:
                touchDownOnGameRunning(worldX, worldY, pointer, button);
                break;

            case GAME_OVER:
                touchDownOnGameOver(worldX, worldY, pointer, button);
                break;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void touchDownOnGameReady(int screenX, int screenY, int pointer, int button) {
        if (mPlayButton.isClicked(screenX, screenY)) {
            mGameWorld.startGame();
        }
    }

    private void touchDownOnGameRunning(int screenX, int screenY, int pointer, int button) {
        mPortal.onClick(mWorldCoords.x - mPortalHalfWidth, mWorldCoords.y - mPortalHalfHeight);
        mPortal = mGameWorld.getNextPortal();
    }

    private void touchDownOnGameOver(int screenX, int screenY, int pointer, int button) {
        if (mReplayButton.isClicked(screenX, screenY)) {
            mGameWorld.restartGame();
        }
    }

    public GameWorld.GameState getGameState() {
        return mGameState;
    }

    public void setGameState(GameWorld.GameState gameState) {
        mGameState = gameState;
    }

}
