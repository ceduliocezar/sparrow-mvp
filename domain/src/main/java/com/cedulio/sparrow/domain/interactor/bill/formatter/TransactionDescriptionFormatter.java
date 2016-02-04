package com.cedulio.sparrow.domain.interactor.bill.formatter;

import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.Transaction;
import com.cedulio.sparrow.domain.R;
import com.cedulio.sparrow.domain.interactor.UseCase;

import android.content.Context;

public class TransactionDescriptionFormatter extends UseCase {

    private final Context context;

    public TransactionDescriptionFormatter(Context context) {
        this.context = context;
    }

    public String format(Transaction transaction, Bill.State billState) {

        if (isFutureBill(billState) && hasMoreThanOneCharge(transaction)) {
            return getDescriptionWithCharges(transaction);
        }

        return transaction.getTitle();
    }

    private boolean isFutureBill(Bill.State billState) {
        return billState == Bill.State.FUTURE;
    }

    private String getDescriptionWithCharges(Transaction transaction) {

        String rawString = getContext()
                .getString(R.string.bill_transaction_description);

        return String.format(rawString, transaction.getTitle(), transaction.getIndex(),
                transaction.getCharges());
    }

    private boolean hasMoreThanOneCharge(Transaction transaction) {
        return transaction.getCharges() > 1;
    }

    private Context getContext() {
        return context;
    }
}
