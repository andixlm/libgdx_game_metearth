package com.ankreez.metearth.Objects;

import com.badlogic.gdx.math.Vector2;

public class Portal extends SimpleObject {

    private Portal mOutPortal;

    public Portal() {
        super();
    }

    public Portal(float x, float y, float radius) {
        super(x, y, radius, radius);
    }

    public Portal(Vector2 position, float radius) {
        super(position, radius, radius);
    }

    @Override
    public void update(float delta) {

    }

    public void onClick(float x, float y) {
        setPosition(x, y);
    }

    public Portal getOutPortal() {
        return mOutPortal;
    }

    public void setOutPortal(Portal outPortal) {
        mOutPortal = outPortal;
    }

}
