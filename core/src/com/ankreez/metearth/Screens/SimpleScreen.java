package com.ankreez.metearth.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class SimpleScreen implements Screen {

    protected Game mGame;

    public SimpleScreen(Game game) {
        mGame = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
