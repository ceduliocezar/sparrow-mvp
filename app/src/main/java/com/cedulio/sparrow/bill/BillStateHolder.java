package com.cedulio.sparrow.bill;


import com.cedulio.sparrow.android.mvp.StateHolder;
import com.cedulio.sparrow.domain.model.Bill;

public class BillStateHolder extends StateHolder {

    private Bill bill;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
