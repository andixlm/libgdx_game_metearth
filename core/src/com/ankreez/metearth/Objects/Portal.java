package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Portal extends SimpleObject {

    private static final float BOUNDS_POS_MULT = 0.75f;
    private static final float BOUNDS_SIZE_MULT = 0.5f;

    private Rectangle mBounds;

    private Portal mOutPortal;

    public Portal(float x, float y, float radius) {
        super(x, y, 2.0f * radius, 2.0f * radius);

        mBounds = new Rectangle(x + BOUNDS_POS_MULT * radius, y + BOUNDS_POS_MULT * radius,
                radius * BOUNDS_SIZE_MULT, radius * BOUNDS_SIZE_MULT);
    }

    public Portal(Vector2 position, float radius) {
        this(position.x, position.y, radius);
    }

    @Override
    public void update(float delta) {

    }

    public void updateBounds() {
        mBounds.setPosition(getX() + BOUNDS_POS_MULT * getRadius(),
                getY() + BOUNDS_POS_MULT * getRadius());
    }

    public void onClick(float x, float y) {
        setPosition(x, y);
        updateBounds();
    }

    public void onRestart() {
        updateBounds();
    }

    public float getRadius() {
        return getWidth() / 2.0f;
    }

    public void setRadius(float radius) {
        setWidth(2.0f * radius);
        setHeight(2.0f * radius);
    }

    public Rectangle getBounds() {
        return mBounds;
    }

    public Portal getOutPortal() {
        return mOutPortal;
    }

    public void setOutPortal(Portal outPortal) {
        mOutPortal = outPortal;
    }

}
