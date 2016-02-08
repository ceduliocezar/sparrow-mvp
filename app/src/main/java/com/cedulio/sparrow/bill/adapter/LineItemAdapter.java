package com.cedulio.sparrow.bill.adapter;

import com.cedulio.sparrow.R;
import com.cedulio.sparrow.bill.formatter.LineItemAmountFormatter;
import com.cedulio.sparrow.bill.formatter.CloseDateFormatter;
import com.cedulio.sparrow.bill.formatter.PostDateFormatter;
import com.cedulio.sparrow.domain.model.LineItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class LineItemAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    private final List<LineItem> items;

    private CloseDateFormatter closeDateFormatter;

    public LineItemAdapter(Context context, List<LineItem> items) {
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.lv_item_item, null);
        }

        setPostDateText(position, convertView);
        setTitleText(position, convertView);
        setValueText(position, convertView);

        setLineVisibility(convertView, position);

        return convertView;
    }

    private void setValueText(int position, View convertView) {
        TextView valueTextView = (TextView) convertView.findViewById(R.id.lv_item__tv__value);
        valueTextView.setText(LineItemAmountFormatter.format(items.get(position).getAmount()));
    }

    private void setTitleText(int position, View convertView) {
        TextView titleTextView = (TextView) convertView.findViewById(R.id.lv_item__tv__title);
        titleTextView.setText(items.get(position).getTitle());
    }

    private void setPostDateText(int position, View convertView) {
        TextView postDateDayTextView = (TextView) convertView
                .findViewById(R.id.lv_item__tv__day);

        TextView postDateMonthTextView = (TextView) convertView
                .findViewById(R.id.lv_item__tv__month);

        Date postDate = items.get(position).getPostDate();

        postDateDayTextView.setText(PostDateFormatter.formatDay(postDate));
        postDateMonthTextView.setText(PostDateFormatter.formatMonth(postDate));
    }

    private void setLineVisibility(View convertView, int position) {

        setVisibilityLineUp(convertView, position);

        setVisibilityLineDown(convertView, position);
    }

    private void setVisibilityLineDown(View convertView, int position) {
        View lineDown = convertView.findViewById(R.id.lv_item__line__down);

        if (position == getCount() - 1) {
            lineDown.setVisibility(View.INVISIBLE);
        } else {
            lineDown.setVisibility(View.VISIBLE);
        }
    }

    private void setVisibilityLineUp(View convertView, int position) {
        View lineUp = convertView.findViewById(R.id.lv_item__line__up);
        if (position == 0) {
            lineUp.setVisibility(View.INVISIBLE);
        } else {
            lineUp.setVisibility(View.VISIBLE);
        }
    }
}