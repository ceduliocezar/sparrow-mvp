package com.cedulio.sparrow.bill.listing;


import android.content.Context;

import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.model.Bill;

public class BillColorSelector {

    public static int getColor(Bill.State state, Context context) {

        int idColor = 0;
        switch (state) {
            case CLOSED: {
                idColor = R.color.bill_closed;
                break;
            }
            case FUTURE: {
                idColor = R.color.bill_future;
                break;
            }
            case OPEN: {
                idColor = R.color.bill_open;
                break;
            }
            case OVERDUE: {
                idColor = R.color.bill_overdue;
                break;
            }
        }

        return context.getResources().getColor(idColor);
    }
}
