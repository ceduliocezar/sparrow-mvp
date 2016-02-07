package com.cedulio.sparrow.data.entity.mapper;


import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.LineItem;
import com.cedulio.sparrow.domain.Summary;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BillEntityMapper {

    private SummaryEntityDataMapper summaryEntityDataMapper = new SummaryEntityDataMapper();
    private LineItemEntityDataMapper lineItemEntityDataMapper = new LineItemEntityDataMapper();

    public Bill transform(BillEntity billEntity) {
        Bill bill = null;

        if (billEntity != null) {
            bill = new Bill();
            bill.setState(billEntity.getState());
            bill.setSummary(convertEntityToSummary(billEntity));
            bill.setLineItems(convertEntityToLineItem(billEntity));
            bill.setId(billEntity.getId());
            bill.setBarcode(billEntity.getBarcode());
            bill.setLinhaDigitavel(billEntity.getLinhaDigitavel());

        }

        return bill;
    }

    private List<LineItem> convertEntityToLineItem(BillEntity billEntity) {
        return lineItemEntityDataMapper.transform(billEntity.getLineItems());
    }

    private Summary convertEntityToSummary(BillEntity billEntity) {
        return summaryEntityDataMapper.transform(billEntity.getSummary());
    }

    public List<Bill> transform(Collection<BillEntity> billEntityCollection) {

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
