package com.cedulio.sparrow.bill.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.model.LineItem;

import java.util.List;

public class LineItemAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<LineItem> items;

    public LineItemAdapter(Context context, List<LineItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
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

        setVisibilityLineDown(convertView, position);
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