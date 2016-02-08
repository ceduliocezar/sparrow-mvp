package com.cedulio.sparrow.data.entity.mapper;

import com.cedulio.sparrow.data.entity.LineItemEntity;
import com.cedulio.sparrow.domain.model.LineItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LineItemEntityDataMapper {

    public LineItem transform(LineItemEntity lineItemEntity) {
        LineItem lineItem = null;

        if (lineItemEntity != null) {
            lineItem = new LineItem();
            lineItem.setAmount(lineItemEntity.getAmount());
            lineItem.setCharges(lineItemEntity.getCharges());
            lineItem.setHref(lineItemEntity.getHref());
            lineItem.setIndex(lineItemEntity.getIndex());
            lineItem.setPostDate(lineItemEntity.getPostDate());
            lineItem.setTitle(lineItemEntity.getTitle());
        }

        return lineItem;
    }

    public List<LineItem> transform(Collection<LineItemEntity> lineItemEntityCollection) {
        List<LineItem> lineItemList = new ArrayList<>();
        LineItem lineItem;

        for (LineItemEntity lineItemEntity : lineItemEntityCollection) {
            lineItem = transform(lineItemEntity);
            if (lineItem != null) {
                lineItemList.add(lineItem);
            }
        }
        return lineItemList;
    }

}
