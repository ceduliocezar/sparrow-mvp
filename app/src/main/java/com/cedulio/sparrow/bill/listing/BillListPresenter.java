package com.cedulio.sparrow.bill.listing;

import com.cedulio.sparrow.android.mvp.Presenter;
import com.cedulio.sparrow.data.repository.BillDataRepository;
import com.cedulio.sparrow.domain.exception.ConnectionProblemException;
import com.cedulio.sparrow.domain.executor.impl.ThreadExecutor;
import com.cedulio.sparrow.domain.formatter.MonthFormatter;
import com.cedulio.sparrow.domain.interactor.bill.GetBills;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.android.threading.MainThreadAndroid;

import android.os.Bundle;

import java.util.List;


public class BillListPresenter extends Presenter implements GetBills.CallBack {

    private BillListView view;

    private BillListStateHolder stateHolder;

    private BillListStateManager stateManager;

    private GetBills getBills;

    public BillListPresenter(BillListView view) {
        setView(view);
        initUseCaseGetBills();
        setStateHolder(new BillListStateHolder());
        setStateManager(new BillListStateManager());
    }

    private void initUseCaseGetBills() {
        this.getBills = new GetBills(new BillDataRepository(getView().getContext()), this,
                MainThreadAndroid.getInstance(), ThreadExecutor.getInstance());
    }

    private void loadDataFromDomain() {
        getBills.execute();
    }

    private void renderBills() {
        getView().renderBillList(getStateHolder().getBills());
    }

    private void selectClosedBill() {

        List<Bill> bills = getStateHolder().getBills();

        int closedBillPosition = getClosedBillPosition(bills);
        getStateHolder().setBillSelectedPosition(closedBillPosition);
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

    private BillListStateHolder getStateHolder() {
        return stateHolder;
    }

    private void setStateHolder(BillListStateHolder stateHolder) {
        this.stateHolder = stateHolder;
    }

    public int getCountBills() {
        return getStateHolder().getBills().size();
    }

    public String getPageTitle(int position) {
        return MonthFormatter.format(getBill(position).getSummary().getCloseDate());
    }

    public void onCreateView(Bundle savedInstanceState) {

        if (hasPreviousState(savedInstanceState)) {
            loadPreviousState(savedInstanceState);
            renderCurrentState();
        } else {
            showLoading();
            loadDataFromDomain();
        }
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
        renderBillSelected();
    }

    @Override
    public void onLoadBills(List<Bill> bills) {
        hideLoading();

        setViewModelBills(bills);
        selectClosedBill();

        renderCurrentState();
    }

    private void hideLoading() {
        getView().hideLoading();
    }

    private void renderCurrentState() {
        renderBills();
        if (hasSelectedBill()) {
            renderBillSelected();
        }
    }

    private void renderBillSelected() {
        int billSelectedPosition = getStateHolder().getBillSelectedPosition();
        Bill billSelected = getBill(billSelectedPosition);

        getView().renderBillSelected(billSelected, billSelectedPosition);
    }

    private boolean hasSelectedBill() {
        return getStateHolder().getBillSelectedPosition()
                != BillListStateHolder.BILL_INDEX_NOT_SELECTED;
    }

    @Override
    public void problemLoadingBills(Exception e) {
        hideLoading();
        showErrorGenericError(e);
    }

    @Override
    public void connectionProblemLoadingBills(ConnectionProblemException e) {
        e.printStackTrace();
        hideLoading();
        showConnectionProblemError(e);
    }

    private void showConnectionProblemError(ConnectionProblemException e) {
        getView().showError(e.getMessage());
    }

    private void showErrorGenericError(Exception e) {
        e.printStackTrace();
        getView().showError(e.getMessage());
    }

    public BillListView getView() {
        return view;
    }

    public void setView(BillListView view) {
        this.view = view;
    }

    @Override
    public void onResumeView() {
    }

    @Override
    public void onPauseView() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getStateManager().saveState(getStateHolder(), outState);
    }

    private BillListStateManager getStateManager() {
        return stateManager;
    }

    private void setStateManager(BillListStateManager stateManager) {
        this.stateManager = stateManager;
    }
}
