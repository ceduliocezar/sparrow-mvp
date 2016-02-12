package com.cedulio.sparrow.bill.listing;

import com.cedulio.sparrow.android.mvp.StateManager;
import com.cedulio.sparrow.domain.model.Bill;

import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

public class BillListStateManager extends StateManager<BillListStateHolder> {


    private static final String BILL_SELECTED_POSITION = "BILL_SELECTED_POSITION";

    private static final String BILL_LIST = "BILL_LIST";

    @Override
    public Bundle saveState(BillListStateHolder stateHolder, Bundle outState) {

        outState.putInt(BILL_SELECTED_POSITION, stateHolder.getBillSelectedPosition());
        outState.putSerializable(BILL_LIST, (Serializable) stateHolder.getBills());

        return outState;
    }

    @Override
    public BillListStateHolder restoreState(Bundle savedState) {

        BillListStateHolder stateHolder = new BillListStateHolder();
        stateHolder.setBillSelectedPosition(savedState.getInt(BILL_SELECTED_POSITION));
        stateHolder.setBills((List<Bill>) savedState.getSerializable(BILL_LIST));
        return stateHolder;
    }
}
