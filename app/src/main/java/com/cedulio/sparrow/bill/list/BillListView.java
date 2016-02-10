package com.cedulio.sparrow.bill.list;

import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.mvp.LoadDataView;

import java.util.List;

public interface BillListView extends LoadDataView {

    void renderBillList(List<Bill> bills);

    void renderBillSelected(Bill bill, int billSelectedPosition);
}
