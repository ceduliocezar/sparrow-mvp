package com.cedulio.sparrow.bill.list;

import com.cedulio.mvp.StateManager;

import android.os.Bundle;

public class BillListStateManager extends StateManager<BillListStateHolder> {


    private static final String BILL_SELECTED_POSITION = "BILL_SELECTED_POSITION";

    @Override
    public Bundle saveState(BillListStateHolder stateHolder) {

        Bundle bundle = new Bundle();
        bundle.putInt(BILL_SELECTED_POSITION, stateHolder.getBillSelectedPosition());
        return bundle;
    }

    @Override
    public BillListStateHolder restoreState(Bundle savedState) {

        BillListStateHolder stateHolder = new BillListStateHolder();
        stateHolder.setBillSelectedPosition(savedState.getInt(BILL_SELECTED_POSITION));

        return stateHolder;
    }
}
