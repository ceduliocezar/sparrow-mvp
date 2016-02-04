package com.cedulio.sparrow.domain.interactor.bill.visibility;

import com.cedulio.sparrow.domain.Bill;

public class InterestVisibilityManager extends BillFieldVisibilityManager {

    public boolean mayShow(Bill bill) {

        if(!isBillStateClosed(bill)){
            return false;
        }

        return bill.getSummary().getInterest() > 0;
    }
}
