package com.ankreez.metearth;

import com.ankreez.metearth.Helpers.AssetHelper;
import com.ankreez.metearth.Screens.GameScreen;
import com.badlogic.gdx.Game;

public class MetearthGame extends Game {

    @Override
    public void create() {
        AssetHelper.load();

        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();

        AssetHelper.dispose();
    }
}
