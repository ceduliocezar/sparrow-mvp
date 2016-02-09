package com.cedulio.sparrow.domain.formatter;

import com.cedulio.sparrow.domain.i18n.FormatMessages;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;

public class LineItemDescriptionFormatter {

    public String format(LineItem lineItem, Bill.State billState) {

        if (isFutureBill(billState) && hasMoreThanOneCharge(lineItem)) {
            return getDescriptionWithCharges(lineItem);
        }

        return lineItem.getTitle();
    }

    private boolean isFutureBill(Bill.State billState) {
        return billState == Bill.State.FUTURE;
    }

    private String getDescriptionWithCharges(LineItem lineItem) {

        String rawString = FormatMessages.getString("LineItemDescriptionWithCharges");

        return String.format(rawString, lineItem.getTitle(), lineItem.getIndex(),
                lineItem.getCharges());
    }

    private boolean hasMoreThanOneCharge(LineItem lineItem) {
        return lineItem.getCharges() > 1;
    }
}