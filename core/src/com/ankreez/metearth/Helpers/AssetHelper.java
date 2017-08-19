package com.ankreez.metearth.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

public class AssetHelper {

    public static final String GAME_NAME = "MetEarth";

    public static final String HIGH_SCORE_PREF = "com.ankreez.metearth.HIGH_SCORE";

    private static final String TEXTURE_ATLAS_NAME = "TextureAtlas";
    private static final String TEXTURE_ATLAS_PATH = "images/" + TEXTURE_ATLAS_NAME + ".png";

    private static final String FONT_NAME = "PressStart2P";
    private static final String FONT_PATH = "fonts/" + FONT_NAME + ".fnt";

    private static final float DEFAULT_FONT_SIZE = 32.0f;
    private static final float DESIRED_FONT_SIZE = 7.0f;
    private static final float FONT_SIZE_SCALE = DESIRED_FONT_SIZE / DEFAULT_FONT_SIZE;

    public static Texture sTextureAtlas;

    public static TextureRegion sGameTitleTexture;
    public static TextureRegion sAppealTexture;

    public static TextureRegion sScoreTextTexture;
    public static TextureRegion sHighScoreTextTexture;

    public static TextureRegion sPlayButtonTexture;
    public static TextureRegion sReplayButtonTexture;

    public static TextureRegion sStarTexture;
    public static TextureRegion sEarthTexture;
    public static TextureRegion sPortalTexture;
    public static TextureRegion sMeteoriteTexture;

    public static BitmapFont sFont;

    public static Preferences sPreferences;

    public static final Random sRandom  = new Random(TimeUtils.millis());

    public static void load() {
        sTextureAtlas = new Texture(Gdx.files.internal(TEXTURE_ATLAS_PATH));
        sTextureAtlas.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sGameTitleTexture = new TextureRegion(sTextureAtlas, 0, 65, 126, 14);
        sAppealTexture = new TextureRegion(sTextureAtlas, 0, 82, 270, 16);
        sScoreTextTexture = new TextureRegion(sTextureAtlas, 199, 65, 88, 16);
        sHighScoreTextTexture = new TextureRegion(sTextureAtlas, 127, 65, 160, 16);

        sPlayButtonTexture = new TextureRegion(sTextureAtlas, 193, 0, 64, 64);
        sReplayButtonTexture = new TextureRegion(sTextureAtlas, 258, 0, 64, 64);

        sStarTexture = new TextureRegion(sTextureAtlas, 0, 99, 5, 5);
        sEarthTexture = new TextureRegion(sTextureAtlas, 0, 0, 64, 64);
        sPortalTexture = new TextureRegion(sTextureAtlas, 65, 0, 64, 64);
        sMeteoriteTexture = new TextureRegion(sTextureAtlas, 130, 0, 62, 59);

        sFont = new BitmapFont(Gdx.files.internal(FONT_PATH));
        sFont.getData().setScale(FONT_SIZE_SCALE, FONT_SIZE_SCALE);

        sPreferences = Gdx.app.getPreferences(GAME_NAME);

        if (!sPreferences.contains(HIGH_SCORE_PREF)) {
            sPreferences.putInteger(HIGH_SCORE_PREF, 0);
            sPreferences.flush();
        }
    }

    public static void dispose() {
        sFont.dispose();

        sTextureAtlas.dispose();
    }

    public static int getHighScore() {
        return AssetHelper.sPreferences.getInteger(AssetHelper.HIGH_SCORE_PREF);
    }

    public static void setHighScore(int score) {
        AssetHelper.sPreferences.putInteger(AssetHelper.HIGH_SCORE_PREF, score);
        AssetHelper.sPreferences.flush();
    }

}
