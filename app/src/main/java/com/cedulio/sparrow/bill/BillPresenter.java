package com.cedulio.sparrow.bill;

import com.cedulio.mvp.Presenter;
import com.cedulio.sparrow.domain.interactor.bill.CurrencyFormatter;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

public class BillPresenter extends Presenter {

    private BillView billView;

    private BillStateHolder billStateHolder;

    private CurrencyFormatter currencyFormatter;

    public BillPresenter(BillView billView) {
        setBillView(billView);
        setBillStateHolder(new BillStateHolder());
        setCurrencyFormatter(new CurrencyFormatter());
    }

    @Override
    public void onCreateView() {
        loadBill();
        renderView();
    }

    private void renderView() {
        renderBill();
    }

    private void renderBill() {
        getBillView().renderBill(getBillView().getBillParam());
    }

    private void loadBill() {
        Bill bill = getBillView().getBillParam();

        getBillStateHolder().setBill(bill);
    }

    @Override
    public void onResumeView() {

    }

    @Override
    public void onPauseView() {
    }

    private BillView getBillView() {
        return billView;
    }

    private void setBillView(BillView billView) {
        this.billView = billView;
    }

    private BillStateHolder getBillStateHolder() {
        return billStateHolder;
    }

    private void setBillStateHolder(BillStateHolder billStateHolder) {
        this.billStateHolder = billStateHolder;
    }

    public String formatCurrencyValue(double value) {
        return getCurrencyFormatter()
                .format(value, DefaultLocale.getInstance().getLocale());
    }

    private CurrencyFormatter getCurrencyFormatter() {
        return currencyFormatter;
    }

    private void setCurrencyFormatter(
            CurrencyFormatter currencyFormatter) {
        this.currencyFormatter = currencyFormatter;
    }
}
