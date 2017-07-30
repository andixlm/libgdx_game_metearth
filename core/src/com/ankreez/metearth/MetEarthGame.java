package com.ankreez.metearth;

import com.ankreez.metearth.Screens.GameScreen;
import com.badlogic.gdx.Game;

public class MetEarthGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }

}
