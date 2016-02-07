package com.cedulio.sparrow.bill.list;


import com.beardedhen.androidbootstrap.BootstrapButton;
import com.cedulio.sparrow.R;
import com.cedulio.sparrow.bill.list.adapter.LineItemAdapter;
import com.cedulio.sparrow.bill.list.utilities.BillColorSelector;
import com.cedulio.sparrow.domain.Bill;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillFragment extends Fragment {

    private static final String BILL_TAG = "BILL_TAG";
    @Bind(R.id.frag_bill__lv__line_items)
    ListView listView;

    @Bind(R.id.frag_bill__bt__gerar)
    BootstrapButton gerarBoletoButton;

    @Bind(R.id.frag_bill__tv__bill_value)
    TextView totalTextView;

    @Bind(R.id.frag_bill__tv__vencimento)
    TextView vencimentoTextView;

    @Bind(R.id.frag_bill__tv__data_fechamento)
    TextView dataFechamentoTextView;

    @Bind(R.id.frag_bill__ln__background_color)
    LinearLayout backgroundColorLinear;

    @Bind(R.id.frag_bill__tv__pagamento_recebido_value)
    TextView pagamentoRecebidoTextView;

    @Bind(R.id.fra_bill__ln__pagamento_recebido)
    LinearLayout pagamentoRecebidoLinearHolder;

    @Bind(R.id.frag_bill__rl__close_infos)
    RelativeLayout closeBillInfoRelative;

    private Bill bill;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflateView(inflater, container);

        ButterKnife.setDebug(true);
        ButterKnife.bind(view);

        bill = (Bill) getArguments().getSerializable(BILL_TAG);

        listView = (ListView) view.findViewById(R.id.frag_bill__lv__line_items);
        backgroundColorLinear = (LinearLayout) view.findViewById(R.id.frag_bill__ln__background_color);

        listView.setAdapter(new LineItemAdapter(getContext(), bill.getLineItems()));

        setBackgroundColor();
        return view;
    }

    private void setBackgroundColor() {

        int color = BillColorSelector.getColor(bill.getState(), getContext());
        backgroundColorLinear.setBackgroundColor(color);
    }


    private View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.frag_bill_list, container, false);
    }

    public static BillFragment newInstance(Bill bill) {

        BillFragment billFragment = new BillFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable(BILL_TAG, bill);
        billFragment.setArguments(bundle);

        return billFragment;
    }


}