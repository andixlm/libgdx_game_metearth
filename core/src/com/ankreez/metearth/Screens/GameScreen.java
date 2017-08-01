package com.ankreez.metearth.Screens;

import com.ankreez.metearth.Game.GameRenderer;
import com.ankreez.metearth.Game.GameWorld;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameScreen extends SimpleScreen {

    private static final float ASPECT_RATIO =
            (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

    private static final float WORLD_WIDTH = 128.0f;
    private static final float WORLD_HEIGHT = WORLD_WIDTH * ASPECT_RATIO;

    private GameWorld mGameWorld;
    private GameRenderer mGameRenderer;

    public GameScreen(Game game) {
        super(game);

        mGameWorld = new GameWorld(WORLD_WIDTH, WORLD_HEIGHT);
        mGameRenderer = new GameRenderer(mGameWorld);
    }

    @Override
    public void render(float delta) {
        mGameWorld.update(delta);
        mGameRenderer.render(delta);
    }

}
