package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public abstract class DynamicGameObject extends SimpleObject {

    private Vector2 mVelocity;
    private Vector2 mAcceleration;

    public DynamicGameObject() {
        super();

        mVelocity = new Vector2();
        mAcceleration = new Vector2();
    }

    public DynamicGameObject(float x, float y) {
        this();

        setPosition(x, y);
    }

    public DynamicGameObject(Vector2 position) {
        this(position.x, position.y);
    }

    public DynamicGameObject(float positionX, float positionY, float velocityX, float velocityY) {
        this(positionX, positionY);

        setVelocity(velocityX, velocityY);
    }

    public DynamicGameObject(Vector2 position, Vector2 velocity) {
        this(position.x, position.y, velocity.x, velocity.y);
    }

    public Vector2 getVelocity() {
        return mVelocity.cpy();
    }

    public void setVelocity(float x, float y) {
        mVelocity.set(x, y);
    }

    public void setVelocity(Vector2 velocity) {
        mVelocity.set(velocity);
    }

    public Vector2 getAcceleration() {
        return mAcceleration.cpy();
    }

    public void setAcceleration(float x, float y) {
        mAcceleration.set(x, y);
    }

    public void setAcceleration(Vector2 acceleration) {
        mAcceleration.set(acceleration);
    }

}
