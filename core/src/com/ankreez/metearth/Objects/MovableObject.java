package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends MetEarthObject {

    private Vector2 mVelocity;
    private Vector2 mAcceleration;

    public MovableObject(float x, float y, Vector2 velocity, float width, float height) {
        this(new Vector2(x, y), velocity, width, height);
    }

    public MovableObject(Vector2 position, Vector2 velocity, float width, float height) {
        super(position, width, height);

        mVelocity = new Vector2(velocity);
        mAcceleration = new Vector2();
    }

    public Vector2 getVelocity() {
        return mVelocity.cpy();
    }

    public void setVelocity(Vector2 velocity) {
        mVelocity.set(velocity);
    }

    public Vector2 getAcceleration() {
        return mAcceleration.cpy();
    }

    public void setAcceleration(Vector2 acceleration) {
        mAcceleration.set(acceleration);
    }

}
