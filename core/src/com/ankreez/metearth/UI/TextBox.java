package com.ankreez.metearth.UI;

import com.ankreez.metearth.Objects.SimpleObject;
import com.badlogic.gdx.math.Vector2;

public class TextBox extends SimpleObject {

    public TextBox(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public TextBox(Vector2 position, float width, float height) {
        this(position.x, position.y, width, height);
    }

    @Override
    public void update(float delta) {

    }

}
