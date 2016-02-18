package com.cedulio.sparrow.domain.visibility.bill;

import com.cedulio.sparrow.domain.model.Bill;

public class GerarBoletoVisibilityChecker extends BillFieldVisibilityChecker {


    public GerarBoletoVisibilityChecker() {

    }

    public boolean mayShow(Bill bill) {
        Bill.State state = bill.getState();

        switch (state) {
            case OVERDUE: {
                return mayShowOverdue(bill.getSummary().getPaid());
            }
            case CLOSED: {
                return mayShowClosed();
            }
            case OPEN: {
                return mayShowOpen();
            }
            case FUTURE: {
                return mayShowFuture();
            }
            default: {
                return false;
            }
        }
    }

    private boolean mayShowFuture() {
        return false;
    }

    private boolean mayShowOpen() {
        return true; // always show gerar boleto when open
    }

    private boolean mayShowClosed() {
        return true; // always show gerar boleto when closed
    }

    private boolean mayShowOverdue(double totalPaid) {
        return totalPaid < 0;
    }
}
