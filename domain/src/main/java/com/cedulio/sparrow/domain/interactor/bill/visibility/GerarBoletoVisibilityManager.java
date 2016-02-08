package com.cedulio.sparrow.domain.interactor.bill.visibility;

import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.interactor.UseCase;

public class GerarBoletoVisibilityManager extends UseCase {


    public GerarBoletoVisibilityManager() {

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
