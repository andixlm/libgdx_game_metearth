package com.ankreez.metearth.Objects;

import java.util.Random;

public class Meteorite extends DynamicSimpleObject {

    private static final float DELIMITER = 0.5f;

    private static final float VELOCITY_MIN = 10.0f;
    private static final float VELOCITY_MAX = 20.0f;

    private static final float ROTATION_SPEED_MIN = 50.0f;
    private static final float ROTATION_SPEED_MAX = 100.0f;

    private static final Random sRandom = new Random();

    private float mRotation;
    private float mRotationSpeed;

    public Meteorite(float radius, float worldWidth, float worldHeight) {
        super();

        setWidth(2.0f * radius);
        setHeight(2.0f * radius);

        reset(worldWidth, worldHeight);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));

        mRotation += delta * mRotationSpeed;
        if (mRotation >= 360.0f) {
            mRotation -= 360.0f;
        }
    }

    public void reset(float worldWidth, float worldHeight) {
        // Generate random out of screen position.
        if (sRandom.nextFloat() < DELIMITER) {
            if (sRandom.nextFloat() < DELIMITER) {
                // Above screen.
                setPosition(sRandom.nextFloat() * worldWidth, worldHeight + getHeight());

                if (getX() / worldWidth < DELIMITER) {
                    setVelocity(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX,
                            -(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX));
                } else {
                    setVelocity(-(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX),
                            -(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX));
                }
            } else {
                // Below screen.
                setPosition(sRandom.nextFloat() * worldWidth, -getHeight());

                if (getX() / worldWidth < DELIMITER) {
                    setVelocity(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX,
                            VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX);
                } else {
                    setVelocity(-(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX),
                            VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX);
                }
            }
        } else {
            if (sRandom.nextFloat() < DELIMITER) {
                // To the left of screen.
                setPosition(-getWidth(), sRandom.nextFloat() * worldHeight);

                if (getY() / worldHeight < DELIMITER) {
                    setVelocity(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX,
                            VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX);
                } else {
                    setVelocity(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX,
                            -(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX));
                }
            } else {
                // To the right of screen.
                setPosition(worldWidth + getWidth(), sRandom.nextFloat() * worldHeight);

                if (getY() / worldHeight < DELIMITER) {
                    setVelocity(-(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX),
                            VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX);
                } else {
                    setVelocity(-(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX),
                            -(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX));
                }
            }
        }

        mRotation = 0.0f;
        mRotationSpeed = ROTATION_SPEED_MIN + sRandom.nextFloat() * ROTATION_SPEED_MAX;
    }

    public float getRadius() {
        return getWidth() / 2.0f;
    }

    public void setRadius(float radius) {
        setWidth(2.0f * radius);
        setHeight(2.0f * radius);
    }

    public float getRotation() {
        return mRotation;
    }

}
