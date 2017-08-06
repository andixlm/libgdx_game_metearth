package com.ankreez.metearth.Helpers;

import com.ankreez.metearth.Game.GameRenderer;
import com.ankreez.metearth.Game.GameWorld;
import com.ankreez.metearth.Objects.Portal;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputHelper implements InputProcessor {

    private GameWorld mGameWorld;

    private Portal mPortal;
    private float mPortalHalfWidth;
    private float mPortalHalfHeight;

    private GameRenderer mGameRenderer;
    private OrthographicCamera mCamera;

    private Vector3 mWorldCoords;

    public InputHelper(GameWorld gameWorld, GameRenderer gameRenderer) {
        mGameWorld = gameWorld;

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

        mPortal.onClick(mWorldCoords.x - mPortalHalfWidth, mWorldCoords.y - mPortalHalfHeight);
        mPortal = mGameWorld.getNextPortal();

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
}
