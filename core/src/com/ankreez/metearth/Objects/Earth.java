package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public class Earth extends GameObject {

    public Earth() {
        super();
    }

    public Earth(float x, float y) {
        super(x, y);
    }

    public Earth(Vector2 position) {
        super(position);
    }

    public Earth(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Earth(Vector2 position, float width, float height) {
        super(position, width, height);
    }

    @Override
    public void update(float delta) {

    }

}
