package com.ankreez.metearth.Objects;

import com.ankreez.metearth.Helpers.AssetHelper;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class Meteorite extends DynamicSimpleObject {

    private static final float DELIMITER = 0.5f;

    private static final float VELOCITY_MIN = 10.0f;
    private static final float VELOCITY_MAX = 20.0f;
    private static final float VELOCITY_DELTA = VELOCITY_MAX - VELOCITY_MIN;

    private static final float ROTATION_SPEED_MIN = 50.0f;
    private static final float ROTATION_SPEED_MAX = 100.0f;
    private static final float ROTATION_SPEED_DELTA = ROTATION_SPEED_MAX - ROTATION_SPEED_MIN;

    private float mRotation;
    private float mRotationSpeed;

    private Circle mBounds;

    public Meteorite(float radius, float worldWidth, float worldHeight) {
        super();

        setWidth(2.0f * radius);
        setHeight(2.0f * radius);

        mBounds = new Circle();
        mBounds.setRadius(getRadius());

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
        if (AssetHelper.sRandom.nextFloat() < DELIMITER) {
            if (AssetHelper.sRandom.nextFloat() < DELIMITER) {
                // Above screen.
                setPosition(AssetHelper.sRandom.nextFloat() * worldWidth, worldHeight + getHeight());

                if (getX() / worldWidth < DELIMITER) {
                    setVelocity(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA,
                            -(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA));
                } else {
                    setVelocity(-(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA),
                            -(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA));
                }
            } else {
                // Below screen.
                setPosition(AssetHelper.sRandom.nextFloat() * worldWidth, -getHeight());

                if (getX() / worldWidth < DELIMITER) {
                    setVelocity(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA,
                            VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA);
                } else {
                    setVelocity(-(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA),
                            VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA);
                }
            }
        } else {
            if (AssetHelper.sRandom.nextFloat() < DELIMITER) {
                // To the left of screen.
                setPosition(-getWidth(), AssetHelper.sRandom.nextFloat() * worldHeight);

                if (getY() / worldHeight < DELIMITER) {
                    setVelocity(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA,
                            VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA);
                } else {
                    setVelocity(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA,
                            -(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA));
                }
            } else {
                // To the right of screen.
                setPosition(worldWidth + getWidth(), AssetHelper.sRandom.nextFloat() * worldHeight);

                if (getY() / worldHeight < DELIMITER) {
                    setVelocity(-(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA),
                            VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA);
                } else {
                    setVelocity(-(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA),
                            -(VELOCITY_MIN + AssetHelper.sRandom.nextFloat() * VELOCITY_DELTA));
                }
            }
        }

        mRotation = 0.0f;
        mRotationSpeed = ROTATION_SPEED_MIN + AssetHelper.sRandom.nextFloat() * ROTATION_SPEED_DELTA;

        mBounds.setPosition(getX() + getRadius(), getY() + getRadius());
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

    public void onRestart(float worldWidth, float worldHeight) {
        reset(worldWidth, worldHeight);
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

    public boolean isMoving() {
        return getVelocity().x != 0.0f && getVelocity().y != 0.0f;
    }

    public boolean isOutOfScreen(float worldWidth, float worldHeight) {
        return (getX() + getWidth() < 0) || (worldWidth < getX() - getWidth()) ||
                (getY() + getHeight() < 0) || (worldHeight < getY() - getHeight());
    }

}
