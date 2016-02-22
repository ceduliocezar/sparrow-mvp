package com.cedulio.sparrow.data.entity.mapper;


import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;
import com.cedulio.sparrow.domain.model.Summary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BillEntityDataMapper {

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

        if (billEntityCollection == null) {
            return Collections.emptyList();
        }

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
