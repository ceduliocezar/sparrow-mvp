package com.cedulio.sparrow.domain.interactor.bill.visibility;


import com.cedulio.sparrow.domain.model.Bill;

public class UnpaidValuesVisibilityManager extends BillFieldVisibilityManager {

    public boolean mayShow(Bill bill) {

        if (!isBillStateClosed(bill)) {
            return false;
        }

        return bill.getSummary().getPastBalance() > 0;
    }
}
