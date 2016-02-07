package com.cedulio.sparrow.data.transfer;

import com.cedulio.sparrow.data.entity.BillEntity;
import com.google.gson.annotations.SerializedName;

public class BillTO {

    @SerializedName("bill")
    private BillEntity bill;

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }
}
