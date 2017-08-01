package com.ankreez.metearth.Helpers;

import com.ankreez.metearth.Game.GameRenderer;
import com.ankreez.metearth.Game.GameWorld;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class InputHelper implements InputProcessor {

    private Vector3 mScreenCoords;
    private Vector3 mWorldCoords;

    private GameWorld mGameWorld;
    private GameRenderer mGameRenderer;

    public InputHelper(GameWorld gameWorld, GameRenderer gameRenderer) {
        mGameWorld = gameWorld;
        mGameRenderer = gameRenderer;

        mScreenCoords = new Vector3();
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
        mWorldCoords = mGameRenderer.getCamera().unproject(
                mScreenCoords.set((float) screenX, (float) screenY, 0.0f));

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
