package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Earth extends SimpleObject {

    private Circle mBounds;

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

        mBounds = new Circle(x + width / 2.0f, y + height / 2.0f, width / 2.0f);
    }

    public Earth(Vector2 position, float width, float height) {
        super(position, width, height);
    }

    @Override
    public void update(float delta) {

    }

    public Circle getBounds() {
        return mBounds;
    }

}
