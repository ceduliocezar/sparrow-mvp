package com.cedulio.sparrow.bill.list;

import com.cedulio.mvp.Presenter;
import com.cedulio.sparrow.data.repository.BillDataRepository;
import com.cedulio.sparrow.domain.executor.impl.ThreadExecutor;
import com.cedulio.sparrow.domain.formatter.LineItemDescriptionFormatter;
import com.cedulio.sparrow.domain.formatter.MonthExpensesFormatter;
import com.cedulio.sparrow.domain.formatter.MonthFormatter;
import com.cedulio.sparrow.domain.interactor.bill.GetBills;
import com.cedulio.sparrow.domain.interactor.bill.visibility.GerarBoletoVisibilityChecker;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;
import com.cedulio.threading.MainThreadAndroid;

import android.os.Bundle;

import java.util.List;


public class BillListPresenter extends Presenter implements GetBills.CallBack {

    private final BillListView view;

    private BillListStateHolder stateHolder;

    private BillListStateManager stateManager;

    private LineItemDescriptionFormatter mLineItemDescriptionFormatter;

    private GerarBoletoVisibilityChecker visibilityManagerGerarBoleto;

    private MonthExpensesFormatter mMonthExpensesFormatter;

    private GetBills getBills;

    public BillListPresenter(BillListView view) {
        this.view = view;
        this.getBills = new GetBills(new BillDataRepository(), this,
                MainThreadAndroid.getInstance(), ThreadExecutor.getInstance());

        setStateHolder(new BillListStateHolder());
        setStateManager(new BillListStateManager());
    }

    private void loadData() {
        getBills.execute();
    }

    private void renderBills(List<Bill> bills) {
        getView().renderBillList(bills);
    }

    private void selectClosedBill(List<Bill> bills) {

        int closedBillPosition = getClosedBillPosition(bills);
        getView().selectCurrentBillByPosition(closedBillPosition);
        getStateHolder().setCurrentBillSelected(bills.get(closedBillPosition));
    }

    private int getClosedBillPosition(List<Bill> bills) {

        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getState() == Bill.State.CLOSED) {
                return i;
            }
        }

        return 0;
    }

    private void setViewModelBills(List<Bill> bills) {
        getStateHolder().setBills(bills);
    }

    public String getFormatedDescription(LineItem lineItem) {
        return getLineItemDescriptionFormatter().format(lineItem,
                getStateFromCurrentBillSelected());
    }

    private Bill.State getStateFromCurrentBillSelected() {
        return getStateHolder().getCurrentBillSelected().getState();
    }

    public boolean mayShowGerarBoleto() {
        return getVisibilityManagerGerarBoleto().mayShow(
                getStateHolder().getCurrentBillSelected());
    }

    private BillListStateHolder getStateHolder() {
        return stateHolder;
    }

    private void setStateHolder(BillListStateHolder stateHolder) {
        this.stateHolder = stateHolder;
    }

    private LineItemDescriptionFormatter getLineItemDescriptionFormatter() {
        return mLineItemDescriptionFormatter;
    }

    private GerarBoletoVisibilityChecker getVisibilityManagerGerarBoleto() {
        return visibilityManagerGerarBoleto;
    }

    public String formatMonthExpenses(double monthExpenses) {
        return getMonthExpensesFormatter().format(monthExpenses);
    }

    private MonthExpensesFormatter getMonthExpensesFormatter() {
        return mMonthExpensesFormatter;
    }

    public int getCountBills() {
        return getStateHolder().getBills().size();
    }

    public String getPageTitle(int position) {
        return MonthFormatter
                .format(getStateHolder().getBills().get(position).getSummary().getCloseDate());
    }

    private void updateView() {
        // TODO
    }

    public void onCreateView(Bundle savedInstanceState) {

        if (getStateHolder().isLoaded()) {
            loadPreviousState(savedInstanceState);
        }

        showLoading();
        loadData();
    }

    private void showLoading() {
        getView().showLoading();
    }

    private void loadPreviousState(Bundle savedInstanceState) {
        setStateHolder(getStateManager().restoreState(savedInstanceState));
    }

    private boolean hasPreviousState(Bundle savedInstanceState) {
        return savedInstanceState != null;
    }

    public Bill getBill(int position) {
        return getStateHolder().getBills().get(position);
    }

    public void onPageSelected(int position) {
        getStateHolder().setBillSelectedPosition(position);
        getView().updateViews(getStateHolder().getBills().get(position));
    }

    @Override
    public void onLoadBills(List<Bill> bills) {
        getView().hideLoading();

        setViewModelBills(bills);
        renderBills(bills);

        if (!hasPreviousSelectedBill(bills)) {
            getView().selectCurrentBillByPosition(getStateHolder().getBillSelectedPosition());
        }
        selectClosedBill(bills);
    }

    private boolean hasPreviousSelectedBill(List<Bill> bills) {
        return getStateHolder().getBillSelectedPosition()
                != BillListStateHolder.BILL_INDEX_NOT_SELECTED;
    }

    @Override
    public void onErrorLoadingBills(Exception e) {
        e.printStackTrace();
        getView().hideLoading();
        getView().showError(e.getMessage()); // TODO HANDLE THIS CORRECTLY
    }

    public BillListView getView() {
        return view;
    }

    @Override
    public void onResumeView() {
    }

    @Override
    public void onPauseView() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getStateManager().saveState(getStateHolder());
    }

    private BillListStateManager getStateManager() {
        return stateManager;
    }

    private void setStateManager(BillListStateManager stateManager) {
        this.stateManager = stateManager;
    }
}
