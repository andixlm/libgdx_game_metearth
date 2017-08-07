package com.ankreez.metearth.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetHelper {

    private static final String FONT_NAME = "PressStart2P";
    private static final String FONT_PATH = "font/" + FONT_NAME + ".fnt";

    private static final float DEFAULT_FONT_SIZE = 32.0f;
    private static final float DESIRED_FONT_SIZE = 8.0f;
    private static final float FONT_SIZE_SCALE = DESIRED_FONT_SIZE / DEFAULT_FONT_SIZE;

    public static Texture sTextureAtlas;

    public static TextureRegion sEarthTexture;
    public static TextureRegion sPortalTexture;
    public static TextureRegion sMeteoriteTexture;

    public static BitmapFont sFont;

    public static void load() {
        sTextureAtlas = new Texture(Gdx.files.internal("image/TextureAtlas.png"));
        sTextureAtlas.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sEarthTexture = new TextureRegion(sTextureAtlas, 0, 0, 64, 64);
        sPortalTexture = new TextureRegion(sTextureAtlas, 64, 0, 64, 64);
        sMeteoriteTexture = new TextureRegion(sTextureAtlas, 128, 0, 62, 60);

        sFont = new BitmapFont(Gdx.files.internal(FONT_PATH));
        sFont.getData().setScale(FONT_SIZE_SCALE, FONT_SIZE_SCALE);
    }

    public static void dispose() {
        sFont.dispose();

        sTextureAtlas.dispose();
    }

}
