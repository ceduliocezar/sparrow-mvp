package com.cedulio.sparrow.bill.list;


import com.beardedhen.androidbootstrap.BootstrapButton;
import com.cedulio.sparrow.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class BillListFragment extends Fragment {

    private ListView listView;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bill_list, container, false);

        listView = (ListView) view.findViewById(R.id.frag_bill_list__lv__transactions);
        listView.setAdapter(new TransactionAdapter(getContext()));

        BootstrapButton button = (BootstrapButton) view.findViewById(R.id.frag_bill_list__fb__gerar);

        return view;
    }


    private class TransactionAdapter extends BaseAdapter {

        private final LayoutInflater inflater;

        public TransactionAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.lv_item_transaction, null);
            }

            setLineVisibility(convertView, position);

            return convertView;
        }

        private void setLineVisibility(View convertView, int position) {

            setVisibilityLineUp(convertView, position);

            setVisibilityLineDown(convertView,position);


        }

        private void setVisibilityLineDown(View convertView, int position) {
            View lineDown = convertView.findViewById(R.id.lv_item_transaction__line__down);

            if (position == getCount() - 1) {
                lineDown.setVisibility(View.INVISIBLE);
            } else {
                lineDown.setVisibility(View.VISIBLE);
            }
        }

        private void setVisibilityLineUp(View convertView, int position) {
            View lineUp = convertView.findViewById(R.id.lv_item_transaction__line__up);
            if (position == 0) {
                lineUp.setVisibility(View.INVISIBLE);
            } else {
                lineUp.setVisibility(View.VISIBLE);
            }
        }
    }
}