package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    private Vector2 mPosition;

    private float mWidth;
    private float mHeight;

    public GameObject() {
        mPosition = new Vector2();

        mWidth = 0;
        mHeight = 0;
    }

    public GameObject(float x, float y, float width, float height) {
        this(new Vector2(x, y), width, height);
    }

    public GameObject(Vector2 position, float width, float height) {
        mPosition = new Vector2(position);
        mWidth = width;
        mHeight = height;
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
