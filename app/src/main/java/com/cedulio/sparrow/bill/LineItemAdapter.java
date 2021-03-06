package com.cedulio.sparrow.bill;

import com.alorma.timeline.RoundTimelineView;
import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.formatter.bill.LineItemValueFormatter;
import com.cedulio.sparrow.domain.formatter.bill.PostDateFormatter;
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

    private LayoutInflater inflater;

    private List<LineItem> items;

    private Callback callback;


    public LineItemAdapter(Context context, List<LineItem> items, Callback callback) {
        setInflater(LayoutInflater.from(context));
        setItems(items);
        setCallback(callback);
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
            convertView = getInflater().inflate(R.layout.lv_item_item, null);
        }

        setPostDateText(position, convertView);
        setTitleText(position, convertView);
        setValueText(position, convertView);

        RoundTimelineView roundView = (RoundTimelineView) convertView.findViewById(R.id.timeline1);
        if(position == 0 ){
            roundView.setTimelineType(RoundTimelineView.TYPE_START);
        }else if( position ==  getCount() -1){
            roundView.setTimelineType(RoundTimelineView.TYPE_END);
        }else{
            roundView.setTimelineType(RoundTimelineView.TYPE_DEFAULT);
        }


//        setLineVisibility(convertView, position);

        return convertView;
    }

    private void setValueText(int position, View convertView) {
        TextView valueTextView = (TextView) convertView.findViewById(R.id.lv_item__tv__value);
        valueTextView.setText(LineItemValueFormatter.format(items.get(position).getAmount()));
    }

    private void setTitleText(int position, View convertView) {
        TextView titleTextView = (TextView) convertView.findViewById(R.id.lv_item__tv__title);
        titleTextView.setText(getLineItemTitleFormatted(position));
    }

    private String getLineItemTitleFormatted(int position) {
        return getCallback().getLineItemTitleFormatted(items.get(position));
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

//    private void setLineVisibility(View convertView, int position) {
//
//        setVisibilityLineUp(convertView, position);
//
//        setVisibilityLineDown(convertView, position);
//    }

//    private void setVisibilityLineDown(View convertView, int position) {
//        View lineDown = convertView.findViewById(R.id.lv_item__line__down);
//
//        if (position == getCount() - 1) {
//            lineDown.setVisibility(View.INVISIBLE);
//        } else {
//            lineDown.setVisibility(View.VISIBLE);
//        }
//    }

//    private void setVisibilityLineUp(View convertView, int position) {
//        View lineUp = convertView.findViewById(R.id.lv_item__line__up);
//        if (position == 0) {
//            lineUp.setVisibility(View.INVISIBLE);
//        } else {
//            lineUp.setVisibility(View.VISIBLE);
//        }
//    }

    private Callback getCallback() {
        return callback;
    }

    private void setCallback(Callback callback) {
        this.callback = callback;
    }

    private LayoutInflater getInflater() {
        return inflater;
    }

    private void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    private List<LineItem> getItems() {
        return items;
    }

    private void setItems(List<LineItem> items) {
        this.items = items;
    }

    public interface Callback {

        String getLineItemTitleFormatted(LineItem lineItem);
    }
}