package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public class Portal extends SimpleObject {

    private Portal mOutPortal;

    public Portal() {
        super();
    }

    public Portal(float x, float y, float radius) {
        super(x, y, 2.0f * radius, 2.0f * radius);
    }

    public Portal(Vector2 position, float radius) {
        super(position, 2.0f * radius, 2.0f * radius);
    }

    @Override
    public void update(float delta) {

    }

    public void onClick(float x, float y) {
        setPosition(x, y);
    }

    public float getRadius() {
        return getWidth() / 2.0f;
    }

    public void setRadius(float radius) {
        setWidth(2.0f * radius);
        setHeight(2.0f * radius);
    }

    public Portal getOutPortal() {
        return mOutPortal;
    }

    public void setOutPortal(Portal outPortal) {
        mOutPortal = outPortal;
    }

}
