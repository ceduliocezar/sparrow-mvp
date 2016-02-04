package com.cedulio.sparrow.bill.list;

import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.mvp.LoadDataView;

import java.util.List;

public interface BillListView extends LoadDataView {

    void renderBillList(List<Bill> bills);
}
