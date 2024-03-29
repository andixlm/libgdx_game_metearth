package com.ankreez.metearth.Screens;

import com.ankreez.metearth.Game.GameRenderer;
import com.ankreez.metearth.Game.GameWorld;
import com.ankreez.metearth.Helpers.AssetHelper;
import com.ankreez.metearth.Helpers.InputHelper;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameScreen extends SimpleScreen {

    private static final float ASPECT_RATIO =
            (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

    private static final float WORLD_WIDTH = 128.0f;
    private static final float WORLD_HEIGHT = WORLD_WIDTH * ASPECT_RATIO;

    private GameWorld mGameWorld;
    private GameRenderer mGameRenderer;
    private InputHelper mInputHelper;

    public GameScreen(Game game) {
        super(game);

        mGameWorld = new GameWorld(WORLD_WIDTH, WORLD_HEIGHT);
        mGameWorld.setOnGameStateChangeListener(new GameWorld.OnGameStateChangeListener() {

            @Override
            public void onGameStateChange(GameWorld.GameState gameState) {
                mGameWorld.updateHighScore();
                mGameRenderer.updateHighScore();

                mGameRenderer.setGameState(gameState);
                mInputHelper.setGameState(gameState);
            }

        });
        mGameWorld.setOnSoundStateChangeListener(new GameWorld.OnSoundStateChangeListener() {

            @Override
            public void onSoundStateChange(boolean state) {
                AssetHelper.setSoundState(state);
                mGameRenderer.updateSoundState(state);
            }

        });
        mGameWorld.setOnScoreChangeListener(new GameWorld.OnScoreChangeListener() {

            @Override
            public void onScoreChange(short score) {
                mGameRenderer.updateScore(score);
            }

        });

        mGameRenderer = new GameRenderer(mGameWorld);

        mInputHelper = new InputHelper(mGameWorld, mGameRenderer);
        Gdx.input.setInputProcessor(mInputHelper);
    }

    @Override
    public void render(float delta) {
        mGameWorld.update(delta);
        mGameRenderer.render(delta);
    }

}
