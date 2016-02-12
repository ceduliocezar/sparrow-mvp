package com.cedulio.sparrow.bill.listing;

import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.android.mvp.LoadDataView;

import java.util.List;

public interface BillListView extends LoadDataView {

    void renderBillList(List<Bill> bills);

    void renderBillSelected(Bill bill, int billSelectedPosition);
}
