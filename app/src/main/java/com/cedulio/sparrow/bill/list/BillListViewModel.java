package com.cedulio.sparrow.bill.list;

import com.cedulio.mvp.ViewModel;
import com.cedulio.sparrow.domain.model.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillListViewModel extends ViewModel {

    private List<Bill> bills = new ArrayList<>();

    private Bill currentBillSelected;
    private int billSelectedPosition;

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Bill getCurrentBillSelected() {
        return currentBillSelected;
    }

    public void setCurrentBillSelected(Bill currentBillSelected) {
        this.currentBillSelected = currentBillSelected;
    }

    public void setBillSelectedPosition(int billSelectedPosition) {
        this.billSelectedPosition = billSelectedPosition;
    }

    public int getBillSelectedPosition() {
        return billSelectedPosition;
    }
}
