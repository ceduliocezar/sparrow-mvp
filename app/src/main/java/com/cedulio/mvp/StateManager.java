package com.cedulio.mvp;

import android.os.Bundle;

public abstract class StateManager<T> {

    public abstract Bundle saveState(T stateHolder);

    public abstract T restoreState(Bundle savedState);
}
