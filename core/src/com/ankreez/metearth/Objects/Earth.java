package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;
import com.sun.istack.internal.NotNull;

public class Earth extends MetEarthObject {

    public Earth(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Earth(@NotNull Vector2 position, float width, float height) {
        super(position, width, height);
    }

    @Override
    public void update(float delta) {

    }

}
