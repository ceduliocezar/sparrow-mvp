package com.cedulio.mvp;

import com.cedulio.sparrow.bill.list.BillListStateHolder;

import android.os.Bundle;

public abstract class StateManager<T extends StateHolder> {

    public abstract Bundle saveState(
            BillListStateHolder stateHolder, Bundle outState);

    public abstract T restoreState(Bundle savedState);
}
