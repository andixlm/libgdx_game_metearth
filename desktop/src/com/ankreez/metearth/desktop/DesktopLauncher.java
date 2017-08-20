package com.ankreez.metearth.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ankreez.metearth.MetearthGame;

public class DesktopLauncher {

    private static final String GAME_TITLE = "metearth";
    private static final int GAME_WIDTH = 360;
    private static final int GAME_HEIGHT = 600;

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = GAME_TITLE;
        config.width = GAME_WIDTH;
        config.height = GAME_HEIGHT;

        new LwjglApplication(new MetearthGame(), config);
    }

}
