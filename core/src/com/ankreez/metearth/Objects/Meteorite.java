package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

public class Meteorite extends DynamicSimpleObject {

    private static final float DELIMITER = 0.5f;

    private static final float VELOCITY_MIN = 10.0f;
    private static final float VELOCITY_MAX = 20.0f;

    private static final float ROTATION_SPEED_MIN = 50.0f;
    private static final float ROTATION_SPEED_MAX = 100.0f;

    private static final Random sRandom  = new Random(TimeUtils.millis());

    private float mRotation;
    private float mRotationSpeed;

    private Circle mBounds;

    public Meteorite(float radius, float worldWidth, float worldHeight) {
        super();

        setWidth(2.0f * radius);
        setHeight(2.0f * radius);

        reset(worldWidth, worldHeight);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().cpy().scl(delta));

        mBounds.setPosition(getX() + getRadius(), getY() + getRadius());

        mRotation += delta * mRotationSpeed;
        if (mRotation >= 360.0f) {
            mRotation -= 360.0f;
        }
    }

    public void stop() {
        setVelocity(0.0f, 0.0f);

        mRotationSpeed = 0.0f;
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

        mBounds = new Circle(getX() + getRadius(), getY() + getRadius(), getRadius());
    }

    public boolean collides(Earth earth) {
        if (earth.getX() < getX() + getWidth() && getX() < earth.getX() + earth.getWidth() &&
                earth.getY() < getY() + getHeight() && getY() < earth.getY() + earth.getHeight()) {
            return Intersector.overlaps(mBounds, earth.getBounds());
        }

        return false;
    }

    public Portal collides(Wormhole wormhole) {
        for (Portal portal : wormhole) {
            if (portal.getX() < getX() + getWidth() && getX() < portal.getX() + portal.getWidth() &&
                    portal.getY() < getY() + getHeight() && getY() < portal.getY() + portal.getHeight()) {
                if (Intersector.overlaps(mBounds, portal.getBounds())) {
                    return portal;
                }
            }
        }

        return null;
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

    public void setRotation(float rotation) {
        mRotation = rotation;
    }

    public Circle getBounds() {
        return mBounds;
    }

    public boolean isOutOfScreen(float worldWidth, float worldHeight) {
        return (getX() + getWidth() < 0) || (worldWidth < getX() - getWidth()) ||
                (getY() + getHeight() < 0) || (worldHeight < getY() - getHeight());
    }

}
