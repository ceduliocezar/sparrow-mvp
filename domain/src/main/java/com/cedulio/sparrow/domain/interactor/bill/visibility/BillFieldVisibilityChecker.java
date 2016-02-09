package com.cedulio.sparrow.domain.interactor.bill.visibility;

import com.cedulio.sparrow.domain.model.Bill;

public abstract class BillFieldVisibilityChecker {

    public abstract boolean mayShow(Bill bill);

    public boolean isBillStateClosed(Bill bill) {
        Bill.State state = bill.getState();

        return state == Bill.State.CLOSED;
    }
}
