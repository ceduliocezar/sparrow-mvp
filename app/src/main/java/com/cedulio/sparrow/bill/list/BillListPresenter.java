package com.cedulio.sparrow.bill.list;

import com.cedulio.mvp.Presenter;
import com.cedulio.sparrow.bill.list.formatter.MonthFormatter;
import com.cedulio.sparrow.data.repository.BillDataRepository;
import com.cedulio.sparrow.domain.executor.impl.ThreadExecutor;
import com.cedulio.sparrow.domain.interactor.bill.GetBills;
import com.cedulio.sparrow.domain.interactor.bill.formatter.LineItemDescriptionFormatter;
import com.cedulio.sparrow.domain.interactor.bill.formatter.MonthExpensesFormatter;
import com.cedulio.sparrow.domain.interactor.bill.visibility.GerarBoletoVisibilityManager;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;
import com.cedulio.threading.MainThreadAndroid;

import java.util.List;


public class BillListPresenter extends Presenter implements GetBills.CallBack {

    private final BillListView view;

    private BillListStateHolder viewModel;

    private LineItemDescriptionFormatter mLineItemDescriptionFormatter;

    private GerarBoletoVisibilityManager visibilityManagerGerarBoleto;

    private MonthExpensesFormatter mMonthExpensesFormatter;

    private GetBills getBills;

    public BillListPresenter(BillListView view) {
        this.view = view;
        this.getBills = new GetBills(new BillDataRepository(), this,
                MainThreadAndroid.getInstance(), ThreadExecutor.getInstance());

        this.viewModel = new BillListStateHolder();
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
        getViewModel().setCurrentBillSelected(bills.get(closedBillPosition));
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
        getViewModel().setBills(bills);
    }

    public String getFormatedDescription(LineItem lineItem) {
        return getLineItemDescriptionFormatter().format(lineItem,
                getStateFromCurrentBillSelected());
    }

    private Bill.State getStateFromCurrentBillSelected() {
        return getViewModel().getCurrentBillSelected().getState();
    }

    public boolean mayShowGerarBoleto() {
        return getVisibilityManagerGerarBoleto().mayShow(
                getViewModel().getCurrentBillSelected());
    }

    private BillListStateHolder getViewModel() {
        return viewModel;
    }

    private LineItemDescriptionFormatter getLineItemDescriptionFormatter() {
        return mLineItemDescriptionFormatter;
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
        return MonthFormatter
                .format(getViewModel().getBills().get(position).getSummary().getCloseDate());
    }

    private void updateView() {
        // TODO
    }

    public void onCreateView() {

        if (getViewModel().isLoaded()) {
            updateView();
        } else {
            getView().showLoading();
            loadData();
        }
    }

    public Bill getBill(int position) {
        return viewModel.getBills().get(position);
    }

    public void onPageSelected(int position) {
        getViewModel().setBillSelectedPosition(position);
        getView().updateViews(getViewModel().getBills().get(position));
    }

    @Override
    public void onLoadBills(List<Bill> bills) {
        getView().hideLoading();

        setViewModelBills(bills);
        renderBills(bills);
        selectClosedBill(bills);
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
}
