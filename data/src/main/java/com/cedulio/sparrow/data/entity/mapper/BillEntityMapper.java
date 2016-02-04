package com.cedulio.sparrow.data.entity.mapper;


import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.domain.Bill;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BillEntityMapper {

    public Bill transform(BillEntity billEntity) {
        Bill bill = null;

        if (billEntity != null) {
            bill = new Bill();
            bill.setState(billEntity.getState());
//            bill.setSummary(billEntity.getSummary());
//            bill.setTransactions(billEntity.getTransactions());
        }

        return bill;
    }

    public List<Bill> transform(Collection<BillEntity> billEntityCollection) {
        Log.d("debug", "transform");
        List<Bill> billList = new ArrayList<>();
        Bill bill;

        for (BillEntity billEntity : billEntityCollection) {
            bill = transform(billEntity);
            if (bill != null) {
                billList.add(bill);
            }
        }
        return billList;
    }
}
