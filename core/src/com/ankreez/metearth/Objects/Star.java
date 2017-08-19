package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public class Star extends SimpleObject {

    public Star(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Star(Vector2 position, float width, float height) {
        super(position, width, height);
    }

    @Override
    public void update(float delta) {

    }

}
