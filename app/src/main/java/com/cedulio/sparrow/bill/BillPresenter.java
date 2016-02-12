package com.cedulio.sparrow.bill;

import com.cedulio.sparrow.android.mvp.Presenter;
import com.cedulio.sparrow.domain.formatter.CloseDateFormatter;
import com.cedulio.sparrow.domain.formatter.CurrencyFormatter;
import com.cedulio.sparrow.domain.formatter.LineItemDescriptionFormatter;
import com.cedulio.sparrow.domain.formatter.SummaryPeriodFormatter;
import com.cedulio.sparrow.domain.interactor.bill.visibility.GerarBoletoVisibilityChecker;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;
import com.cedulio.sparrow.domain.model.Summary;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import android.os.Bundle;

import java.util.Date;

public class BillPresenter extends Presenter {

    private BillView billView;

    private BillStateHolder billStateHolder;

    private CurrencyFormatter currencyFormatter;

    private LineItemDescriptionFormatter lineItemDescriptionFormatter;

    private CloseDateFormatter closeDateFormatter;

    private SummaryPeriodFormatter summaryPeriodFormatter;

    public BillPresenter(BillView billView) {
        setBillView(billView);
        setBillStateHolder(new BillStateHolder());
        setCurrencyFormatter(new CurrencyFormatter());
        setLineItemDescriptionFormatter(new LineItemDescriptionFormatter());
        setCloseDateFormatter(new CloseDateFormatter());
        setSummaryPeriodFormatter(new SummaryPeriodFormatter());
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //TODO TECBMCCS
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
                .format(value, DefaultLocale.getLocale());
    }

    private CurrencyFormatter getCurrencyFormatter() {
        return currencyFormatter;
    }

    private void setCurrencyFormatter(
            CurrencyFormatter currencyFormatter) {
        this.currencyFormatter = currencyFormatter;
    }

    private LineItemDescriptionFormatter getLineItemDescriptionFormatter() {
        return lineItemDescriptionFormatter;
    }

    private void setLineItemDescriptionFormatter(
            LineItemDescriptionFormatter lineItemDescriptionFormatter) {
        this.lineItemDescriptionFormatter = lineItemDescriptionFormatter;
    }

    public String getLineItemTitleFormatted(LineItem line) {
        return getLineItemDescriptionFormatter()
                .format(line, getBillStateHolder().getBill().getState());
    }


    public String getCloseDateFormatted(Date closeDate) {
        return getCloseDateFormatter().formatClose(closeDate, getBillView().getContext());
    }

    private CloseDateFormatter getCloseDateFormatter() {
        return closeDateFormatter;
    }

    private void setCloseDateFormatter(
            CloseDateFormatter closeDateFormatter) {
        this.closeDateFormatter = closeDateFormatter;
    }

    public String getCloseDateFormatLong(Date closeDate) {
        return getCloseDateFormatter().formatClosing(closeDate, getBillView().getContext());
    }

    public boolean mayShowGerarBoleto(Bill.State state) {
        return new GerarBoletoVisibilityChecker().mayShow(getBillStateHolder().getBill());
    }

    public String getSummaryPeriodFormatted(Summary summary) {
        return getSummaryPeriodFormatter()
                .format(summary.getOpenDate(), summary.getCloseDate(), getBillView().getContext());
    }

    private SummaryPeriodFormatter getSummaryPeriodFormatter() {
        return summaryPeriodFormatter;
    }

    private void setSummaryPeriodFormatter(SummaryPeriodFormatter summaryPeriodFormatter) {
        this.summaryPeriodFormatter = summaryPeriodFormatter;
    }
}
