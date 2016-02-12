package com.cedulio.sparrow.android.mvp;

public abstract class StateHolder {

    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
