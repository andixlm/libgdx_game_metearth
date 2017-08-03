package com.ankreez.metearth.Objects;

public class Meteorite extends DynamicGameObject {

    public Meteorite(float radius) {
        super();

        setWidth(radius);
        setHeight(radius);
    }

    @Override
    public void update(float delta) {
        getPosition().add(getVelocity().scl(delta));
    }

}
