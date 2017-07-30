package com.ankreez.metearth.Screens;

import com.ankreez.metearth.Game.GameRenderer;
import com.ankreez.metearth.Game.GameWorld;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameScreen extends MetEarthScreen {

    private static final float WORLD_WIDTH = 128.0f;
    private static final float WORLD_HEIGHT = (float) Gdx.graphics.getHeight() /
            ((float) Gdx.graphics.getWidth() / WORLD_WIDTH);

    private GameWorld mGameWorld;
    private GameRenderer mGameRenderer;

    public GameScreen(Game game) {
        super(game);

        mGameWorld = new GameWorld();
        mGameRenderer = new GameRenderer();
    }

    @Override
    public void render(float delta) {
        mGameWorld.update(delta);
        mGameRenderer.render(delta);
    }

}
