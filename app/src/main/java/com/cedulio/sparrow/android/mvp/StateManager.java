package com.cedulio.sparrow.android.mvp;

import com.cedulio.sparrow.bill.listing.BillListStateHolder;

import android.os.Bundle;

public abstract class StateManager<T extends StateHolder> {

    public abstract Bundle saveState(
            BillListStateHolder stateHolder, Bundle outState);

    public abstract T restoreState(Bundle savedState);
}
