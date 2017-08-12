package com.ankreez.metearth.UI;

import com.ankreez.metearth.Objects.SimpleObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class RoundButton extends SimpleObject {

    private Circle mBounds;

    public RoundButton(float x, float y, float width, float height) {
        super(x, y, width, height);

        mBounds = new Circle(x, y, width);
    }

    public RoundButton(Vector2 position, float width, float height) {
        this(position.x, position.y, width, height);
    }

    @Override
    public void update(float delta) {

    }

    public boolean isClicked(int x, int y) {
        return mBounds.contains(x, y);
    }

}
