package com.cedulio.sparrow.bill.list;

import com.cedulio.mvp.StateHolder;
import com.cedulio.sparrow.domain.model.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillListStateHolder extends StateHolder {

    public static final int BILL_INDEX_NOT_SELECTED = -1;

    private List<Bill> bills = new ArrayList<>();

    private int billSelectedPosition = BILL_INDEX_NOT_SELECTED;

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public int getBillSelectedPosition() {
        return billSelectedPosition;
    }

    public void setBillSelectedPosition(int billSelectedPosition) {
        this.billSelectedPosition = billSelectedPosition;
    }
}
