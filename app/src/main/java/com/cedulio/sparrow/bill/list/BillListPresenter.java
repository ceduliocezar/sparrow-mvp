package com.cedulio.sparrow.bill.list;

import android.os.Bundle;

import com.cedulio.sparrow.data.repository.BillDataRepository;
import com.cedulio.sparrow.domain.executor.impl.ThreadExecutor;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;
import com.cedulio.sparrow.domain.interactor.bill.GetBills;
import com.cedulio.sparrow.domain.interactor.bill.formatter.MonthExpensesFormatter;
import com.cedulio.sparrow.domain.interactor.bill.formatter.TransactionDescriptionFormatter;
import com.cedulio.sparrow.domain.interactor.bill.visibility.GerarBoletoVisibilityManager;
import com.cedulio.mvp.Presenter;
import com.cedulio.threading.MainThreadAndroid;

import java.util.List;


public class BillListPresenter extends Presenter implements GetBills.CallBack {

    private final BillListView view;

    private BillListViewModel viewModel;

    private TransactionDescriptionFormatter transactionDescriptionFormatter;

    private GerarBoletoVisibilityManager visibilityManagerGerarBoleto;

    private MonthExpensesFormatter mMonthExpensesFormatter;

    private GetBills getBills;

    public BillListPresenter(BillListView view) {
        this.view = view;
        this.getBills = new GetBills(new BillDataRepository(), this,
                MainThreadAndroid.getInstance(), ThreadExecutor.getInstance());

        this.viewModel = new BillListViewModel();
    }

    private void loadData() {
        getBills.execute();
    }

    private void renderBills(List<Bill> bills) {
        view.renderBillList(bills);
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

        getView().updateMarker(getViewModel().getBills().get(position));
    }

    @Override
    public void onLoadBills(List<Bill> bills) {
        setViewModelBills(bills);
        selectFirstBill(bills);

        renderBills(bills);
    }

    @Override
    public void onErrorLoadingBills(Exception e) {
        e.printStackTrace();
        getView().showError(e.getMessage()); // TODO HANDLE THIS CORRECTLY
    }

    public BillListView getView() {
        return view;
    }
}
