package com.cedulio.sparrow.bill;

import com.cedulio.mvp.MVPView;
import com.cedulio.sparrow.domain.model.Bill;


public interface BillView extends MVPView {

    Bill getBillParam();
    void renderBill(Bill bill);
}
