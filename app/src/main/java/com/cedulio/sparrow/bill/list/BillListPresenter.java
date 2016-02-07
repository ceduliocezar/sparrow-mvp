package com.cedulio.sparrow.bill.list;

import android.os.Bundle;

import com.cedulio.sparrow.data.repository.BillDataRepository;
import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.LineItem;
import com.cedulio.sparrow.domain.interactor.bill.GetBills;
import com.cedulio.sparrow.domain.interactor.bill.formatter.MonthExpensesFormatter;
import com.cedulio.sparrow.domain.interactor.bill.formatter.TransactionDescriptionFormatter;
import com.cedulio.sparrow.domain.interactor.bill.visibility.GerarBoletoVisibilityManager;
import com.cedulio.mvp.Presenter;

import java.io.IOException;
import java.util.List;


public class BillListPresenter extends Presenter {

    private final BillListView mView;

    private BillListViewModel viewModel;

    private TransactionDescriptionFormatter transactionDescriptionFormatter;

    private GerarBoletoVisibilityManager visibilityManagerGerarBoleto;

    private MonthExpensesFormatter mMonthExpensesFormatter;

    private GetBills getBills;

    public BillListPresenter(BillListView view) {
        this.mView = view;
        this.getBills = new GetBills(new BillDataRepository());
        this.viewModel = new BillListViewModel();
    }

    private void loadData() {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                List<Bill> bills = null;
                try {
                    bills = getBills.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                setViewModelBills(bills);
                selectFirstBill(bills);

                renderBills(bills);
            }
        };

        new Thread(r).start();
    }

    private void renderBills(List<Bill> bills) {
        mView.renderBillList(bills);
    }

    private void selectFirstBill(List<Bill> bills) {
        viewModel.setCurrentBillSelected(bills.get(0));
    }

    private void setViewModelBills(List<Bill> bills) {
        viewModel.setBills(bills);
    }

    public String getFormatedDescription(LineItem lineItem) {
        return getTransactionDescriptionFormatter().format(lineItem,
                getStateFromCurrentBillSelected());
    }

    private Bill.State getStateFromCurrentBillSelected() {
        return getViewModel().getCurrentBillSelected().getState();
    }

    public boolean mayShowGerarBoleto() {
        return getVisibilityManagerGerarBoleto().mayShow(
                getViewModel().getCurrentBillSelected());
    }

    private BillListViewModel getViewModel() {
        return viewModel;
    }

    private TransactionDescriptionFormatter getTransactionDescriptionFormatter() {
        return transactionDescriptionFormatter;
    }

    private GerarBoletoVisibilityManager getVisibilityManagerGerarBoleto() {
        return visibilityManagerGerarBoleto;
    }

    public String formatMonthExpenses(double monthExpenses) {
        return getMonthExpensesFormatter().format(monthExpenses);
    }

    private MonthExpensesFormatter getMonthExpensesFormatter() {
        return mMonthExpensesFormatter;
    }

    public int getCountBills() {
        return getViewModel().getBills().size();
    }

    public String getPageTitle(int position) {
        return "MAI " + position;
    }

    public void onPauseView() {

    }

    public void onResumeView() {


    }

    private void updateView() {
        // TODO
    }

    public void onCreate(Bundle savedInstanceState) {

        if (getViewModel().isLoaded()) {
            updateView();
        } else {
            loadData();
        }
    }

    public Bill getBill(int position) {
        return viewModel.getBills().get(position);
    }

    public void onPageSelected(int position) {
        getViewModel().setBillSelectedPosition(position);

        mView.updateMarker(getViewModel().getBills().get(position));
    }


}
