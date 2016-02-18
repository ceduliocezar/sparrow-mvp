package com.cedulio.sparrow.bill;


import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.formatter.bill.CloseDateFormatter;
import com.cedulio.sparrow.domain.formatter.CurrencyFormatter;
import com.cedulio.sparrow.domain.formatter.bill.LineItemDescriptionFormatter;
import com.cedulio.sparrow.domain.formatter.bill.SummaryPeriodFormatter;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;
import com.cedulio.sparrow.domain.model.Summary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillFragment extends Fragment implements BillView, LineItemAdapter.Callback {

    private static final String BILL_TAG = "BILL_TAG";

    @Bind(R.id.frag_bill__lv__line_items)
    ListView lineItensListView;

    @Bind(R.id.frag_bill__bt__gerar)
    Button gerarBoletoButton;

    @Bind(R.id.frag_bill__tv__total_balance)
    TextView totalBalanceTextView;

    @Bind(R.id.frag_bill__tv__close_date)
    TextView closeDateTextView;

    @Bind(R.id.frag_bill__ln__background_color)
    LinearLayout backgroundColorLinear;

    @Bind(R.id.frag_bill__tv__paid_value)
    TextView paidValueTextView;

    @Bind(R.id.fra_bill__ln__paid)
    LinearLayout paidLinearHolder;

    @Bind(R.id.frag_bill__rl__closed_informantion)
    RelativeLayout closeBillInformationRelative;

    @Bind(R.id.frag_bill__tv__expenses_value)
    TextView expensesTextView;

    @Bind(R.id.frag_bill__tv__unpaid_values_value)
    TextView unpaidValuesTextView;

    @Bind(R.id.frag_bill__tv__interest_value)
    TextView interestTextView;

    @Bind(R.id.frag_bill__tv__additional_information)
    TextView additionalInformationTextView;

    @Bind(R.id.act_bill__ln__subheader)
    LinearLayout subheaderLinear;

    @Bind(R.id.frag_bill__tv__period_header)
    TextView periodLineItemsTextView;

    private CurrencyFormatter currencyFormatter;

    private LineItemDescriptionFormatter lineItemDescriptionFormatter;

    private CloseDateFormatter closeDateFormatter;

    private SummaryPeriodFormatter summaryPeriodFormatter;


    private BillPresenter billPresenter;

    public static BillFragment newInstance(Bill bill) {

        BillFragment billFragment = new BillFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable(BILL_TAG, bill);
        billFragment.setArguments(bundle);

        return billFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    private void initPresenter() {
        setBillPresenter(new BillPresenter(this));
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflateView(inflater, container);

        ButterKnife.bind(this, view);

        getBillPresenter().onCreateView(savedInstanceState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBillPresenter().onResumeView();
    }

    @Override
    public void onPause() {
        super.onPause();
        getBillPresenter().onPauseView();
    }

    private void setLineItens(Bill bill) {
        lineItensListView.setAdapter(new LineItemAdapter(getContext(), bill.getLineItems(), this));
    }

    @Override
    public Bill getBillParam() {
        return (Bill) getArguments().getSerializable(BILL_TAG);
    }

    public void renderBill(Bill bill) {
        initHeader(bill);
        setLineItens(bill);
    }

    private void initHeader(Bill bill) {
        setBackgroundColor(bill.getState());

        setText(bill);
        setVisibility(bill);
    }

    private void setText(Bill bill) {
        setTotalBalanceText(bill.getSummary().getTotalBalance());
        setCloseDateText(bill.getSummary().getCloseDate());
        setPaidText(bill.getSummary().getPaid());
        setClosedInformations(bill.getSummary(), bill.getState());
        setButtonGerarBoletoColor(bill.getState());
        setAdditionalInformationText(bill);
        setLineItemsPeriodText(bill.getSummary());
    }

    private void setVisibility(Bill bill) {
        setPaidVisibility(bill.getState());
        setClosedInformationVisibility(bill.getState());
        setAdditionalInformationVisibility(bill.getState());
        setSubHeaderVisibility(bill.getState());
        setButtonGerarBoletoVisibility(bill.getState());
    }

    private void setLineItemsPeriodText(Summary summary) {
        periodLineItemsTextView.setText(getSummaryPeriodFormatted(summary));
    }

    @NonNull
    private String getSummaryPeriodFormatted(Summary summary) {
        return getBillPresenter().getSummaryPeriodFormatted(summary);
    }

    private void setSubHeaderVisibility(Bill.State state) {
        if (isFuture(state)) {
            subheaderLinear.setVisibility(View.GONE);
        } else {
            subheaderLinear.setVisibility(View.VISIBLE);
        }
    }

    private void setButtonGerarBoletoColor(Bill.State state) {
        gerarBoletoButton.setTextColor(BillColorSelector.getColor(state, getContext()));

        if (isClosed(state)) {
            gerarBoletoButton.setBackgroundResource(R.drawable.closed_button);
        } else {
            gerarBoletoButton.setBackgroundResource(R.drawable.open_button);
        }

    }

    private void setAdditionalInformationVisibility(Bill.State state) {
        if (isOpen(state) || isFuture(state)) {
            additionalInformationTextView.setVisibility(View.VISIBLE);
        } else {
            additionalInformationTextView.setVisibility(View.GONE);
        }
    }

    private boolean isFuture(Bill.State state) {
        return state == Bill.State.FUTURE;
    }

    private void setAdditionalInformationText(Bill bill) {
        if (isOpen(bill.getState())) {
            setLongCloseDateText(bill.getSummary().getCloseDate());
        } else if (isFuture(bill.getState())) {
            setParcialBillingText();
        }
    }

    private void setParcialBillingText() {
        additionalInformationTextView.setText(R.string.parcial_billing);
    }

    private void setLongCloseDateText(Date closeDate) {
        additionalInformationTextView
                .setText(getCloseDateFormatLong(closeDate));
    }

    private String getCloseDateFormatLong(Date closeDate) {
        return getBillPresenter().getCloseDateFormatLong(closeDate);
    }

    private void setButtonGerarBoletoVisibility(Bill.State state) {

        if (mayShowGerarBoleto(state)) {
            gerarBoletoButton.setVisibility(View.VISIBLE);
        } else {
            gerarBoletoButton.setVisibility(View.GONE);
        }
    }

    private boolean mayShowGerarBoleto(Bill.State state) {
        return getBillPresenter().mayShowGerarBoleto(state);
    }

    private boolean isOpen(Bill.State state) {
        return state == Bill.State.OPEN;
    }

    private void setClosedInformations(Summary summary, Bill.State state) {
        if (isClosed(state)) {
            setExpensesText(summary.getTotalBalance());
            setPastBalanceText(summary.getPastBalance());
            setInterestText(summary.getInterest());
        }
    }

    private void setInterestText(double interest) {
        interestTextView.setText(formatCurrencyValue(interest));
    }

    private void setPastBalanceText(double pastBalance) {
        unpaidValuesTextView.setText(formatCurrencyValue(pastBalance));
    }

    private String formatCurrencyValue(double pastBalance) {
        return getBillPresenter().formatCurrencyValue(pastBalance);
    }

    private void setExpensesText(double totalBalance) {
        expensesTextView.setText(formatCurrencyValue(totalBalance));
    }

    private void setClosedInformationVisibility(Bill.State state) {
        if (isClosed(state)) {
            closeBillInformationRelative.setVisibility(View.VISIBLE);
        } else {
            closeBillInformationRelative.setVisibility(View.GONE);
        }
    }

    private boolean isClosed(Bill.State state) {
        return state == Bill.State.CLOSED;
    }


    private void setPaidVisibility(Bill.State state) {
        if (isOverdue(state)) {
            paidLinearHolder.setVisibility(View.VISIBLE);
        } else {
            paidLinearHolder.setVisibility(View.GONE);
        }
    }

    private boolean isOverdue(Bill.State state) {
        return state == Bill.State.OVERDUE;
    }

    private void setPaidText(double paid) {
        paidValueTextView.setText(formatCurrencyValue(paid));
    }

    private void setCloseDateText(Date closeDate) {
        closeDateTextView.setText(getCloseDateFormatted(closeDate));
    }

    private String getCloseDateFormatted(Date closeDate) {
        return getBillPresenter().getCloseDateFormatted(closeDate);
    }

    private void setTotalBalanceText(double totalBalance) {
        totalBalanceTextView.setText(formatCurrencyValue(totalBalance));
    }

    private void setBackgroundColor(Bill.State state) {
        int color = BillColorSelector.getColor(state, getContext());
        backgroundColorLinear.setBackgroundColor(color);
    }

    private View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.frag_bill, container, false);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private BillPresenter getBillPresenter() {
        return billPresenter;
    }

    private void setBillPresenter(BillPresenter billPresenter) {
        this.billPresenter = billPresenter;
    }

    @Override
    public String getLineItemTitleFormatted(LineItem lineItem) {
        return getBillPresenter().getLineItemTitleFormatted(lineItem);
    }



}