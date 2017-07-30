package com.ankreez.metearth.Screens;

import com.ankreez.metearth.Game.GameRenderer;
import com.ankreez.metearth.Game.GameWorld;
import com.badlogic.gdx.Game;

public class GameScreen extends MetEarthScreen {

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
