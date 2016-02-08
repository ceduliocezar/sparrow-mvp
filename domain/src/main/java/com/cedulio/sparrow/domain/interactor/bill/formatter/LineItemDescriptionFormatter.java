package com.cedulio.sparrow.domain.interactor.bill.formatter;

import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;
import com.cedulio.sparrow.domain.R;
import com.cedulio.sparrow.domain.interactor.UseCase;

import android.content.Context;

public class LineItemDescriptionFormatter extends UseCase {

    private final Context context;

    public LineItemDescriptionFormatter(Context context) {
        this.context = context;
    }

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

        String rawString = getContext()
                .getString(R.string.bill_transaction_description);

        return String.format(rawString, lineItem.getTitle(), lineItem.getIndex(),
                lineItem.getCharges());
    }

    private boolean hasMoreThanOneCharge(LineItem lineItem) {
        return lineItem.getCharges() > 1;
    }

    private Context getContext() {
        return context;
    }
}
