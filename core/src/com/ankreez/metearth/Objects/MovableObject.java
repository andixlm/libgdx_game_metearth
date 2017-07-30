package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;
import com.sun.istack.internal.NotNull;

public abstract class MovableObject extends MetEarthObject {

    private Vector2 mVelocity;
    private Vector2 mAcceleration;

    public MovableObject(float x, float y, @NotNull Vector2 velocity, float width, float height) {
        this(new Vector2(x, y), velocity, width, height);
    }

    public MovableObject(@NotNull Vector2 position, @NotNull Vector2 velocity,
                         float width, float height) {
        super(position, width, height);

        mVelocity = new Vector2(velocity);
        mAcceleration = new Vector2();
    }

    public Vector2 getVelocity() {
        return mVelocity.cpy();
    }

    public void setVelocity(@NotNull Vector2 velocity) {
        mVelocity.set(velocity);
    }

    public Vector2 getAcceleration() {
        return mAcceleration.cpy();
    }

    public void setAcceleration(@NotNull Vector2 acceleration) {
        mAcceleration.set(acceleration);
    }

}
