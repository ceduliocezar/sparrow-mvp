package com.cedulio.sparrow.bill.list;

import com.cedulio.sparrow.mvp.ViewModel;
import com.cedulio.sparrow.domain.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillListViewModel extends ViewModel {

    private List<Bill> bills = new ArrayList<>();

    private Bill currentBillSelected;

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
}
