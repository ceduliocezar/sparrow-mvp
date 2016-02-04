package com.cedulio.sparrow.domain.interactor.bill.visibility;

import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.interactor.UseCase;

public abstract class BillFieldVisibilityManager extends UseCase {

    public abstract boolean mayShow(Bill bill);

    public boolean isBillStateClosed(Bill bill) {
        Bill.State state = bill.getState();

        return state == Bill.State.CLOSED;
    }
}
