package com.cedulio.sparrow.bill;

import com.cedulio.sparrow.android.mvp.MVPView;
import com.cedulio.sparrow.domain.model.Bill;


public interface BillView extends MVPView {

    Bill getBillParam();
    void renderBill(Bill bill);
}
