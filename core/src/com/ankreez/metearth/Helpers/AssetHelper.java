package com.ankreez.metearth.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetHelper {

    public static Texture sTextureAtlas;

    public static TextureRegion sEarthTexture;
    public static TextureRegion sPortalTexture;
    public static TextureRegion sMeteoriteTexture;

    public static void load() {
        sTextureAtlas = new Texture(Gdx.files.internal("image/TextureAtlas.png"));
        sTextureAtlas.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sEarthTexture = new TextureRegion(sTextureAtlas, 0, 0, 64, 64);
        sPortalTexture = new TextureRegion(sTextureAtlas, 64, 0, 64, 64);
        sMeteoriteTexture = new TextureRegion(sTextureAtlas, 128, 0, 62, 60);
    }

    public static void dispose() {
        sTextureAtlas.dispose();
    }

}
