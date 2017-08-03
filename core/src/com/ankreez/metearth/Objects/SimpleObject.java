package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public abstract class SimpleObject {

    private Vector2 mPosition;

    private float mWidth;
    private float mHeight;

    public SimpleObject() {
        mPosition = new Vector2();

        setWidth(0.0f);
        setHeight(0.0f);
    }

    public SimpleObject(float x, float y) {
        this();

        setPosition(x, y);
    }

    public SimpleObject(Vector2 position) {
        this(position.x, position.y);
    }

    public SimpleObject(float x, float y, float width, float height) {
        this(x, y);

        setWidth(width);
        setHeight(height);
    }

    public SimpleObject(Vector2 position, float width, float height) {
        this(position.x, position.y, width, height);
    }

    public abstract void update(float delta);

    public Vector2 getPosition() {
        return mPosition.cpy();
    }

    public void setPosition(float x, float y) {
        mPosition.set(x, y);
    }

    public void setPosition(Vector2 position) {
        mPosition.set(position);
    }

    public float getX() {
        return mPosition.x;
    }

    public void setX(float x) {
        mPosition.x = x;
    }

    public float getY() {
        return mPosition.y;
    }

    public void setY(float y) {
        mPosition.y = y;
    }

    public float getWidth() {
        return mWidth;
    }

    public void setWidth(float width) {
        mWidth = width;
    }

    public float getHeight() {
        return mHeight;
    }

    public void setHeight(float height) {
        mHeight = height;
    }

}
