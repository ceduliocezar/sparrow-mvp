package com.cedulio.sparrow.bill.list;

import com.cedulio.sparrow.data.repository.BillDataRepository;
import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.Transaction;
import com.cedulio.sparrow.domain.interactor.bill.GetBills;
import com.cedulio.sparrow.domain.interactor.bill.formatter.MonthExpensesFormatter;
import com.cedulio.sparrow.domain.interactor.bill.formatter.TransactionDescriptionFormatter;
import com.cedulio.sparrow.domain.interactor.bill.visibility.GerarBoletoVisibilityManager;
import com.cedulio.sparrow.mvp.Presenter;

import android.util.Log;

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
    }

    public void load() {
        Log.d("debug", "load");
        try {
            List<Bill> bills = getBills.execute();
            mView.renderBillList(bills);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFormatedDescription(Transaction transaction) {
        return getTransactionDescriptionFormatter().format(transaction,
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
}
