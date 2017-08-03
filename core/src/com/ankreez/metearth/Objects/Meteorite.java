package com.ankreez.metearth.Objects;

import java.util.Random;

public class Meteorite extends DynamicGameObject {

    private static final float DELIMITER = 0.5f;

    private static final float VELOCITY_MIN = 10.0f;
    private static final float VELOCITY_MAX = 100.0f;

    private static final Random sRandom = new Random();

    public Meteorite(float radius, float worldWidth, float worldHeight) {
        super();

        setWidth(radius);
        setHeight(radius);

        reset(worldWidth, worldHeight);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().scl(delta));
    }

    public void reset(float worldWidth, float worldHeight) {
        // Generate random out of screen position.
        if (sRandom.nextFloat() < DELIMITER) {
            if (sRandom.nextFloat() < DELIMITER) {
                // Above screen.
                setPosition(sRandom.nextFloat() * worldWidth, -getHeight());

                if (getX() / worldWidth < DELIMITER) {
                    setVelocity(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX,
                            -(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX));
                } else {
                    setVelocity(-(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX),
                            -(VELOCITY_MIN + sRandom.nextFloat() * VELOCITY_MAX));
                }
            } else {
                // Below screen.
                setPosition(sRandom.nextFloat() * worldWidth, worldHeight + getHeight());

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
    }

}
