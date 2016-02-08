package com.cedulio.sparrow.bill.list.adapter;


import com.astuetz.PagerSlidingTabStrip;
import com.cedulio.sparrow.R;
import com.cedulio.sparrow.bill.BillFragment;
import com.cedulio.sparrow.bill.list.utilities.BillColorSelector;
import com.cedulio.sparrow.domain.model.Bill;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BillViewPagerAdapter extends FragmentStatePagerAdapter
        implements PagerSlidingTabStrip.CustomTabProvider {

    private final LayoutInflater layoutInflater;

    private final BillViewPagerListener listener;

    public BillViewPagerAdapter(
            FragmentManager fm, Context context, BillViewPagerListener listener) {
        super(fm);
        this.layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public Fragment getItem(int position) {

        return BillFragment.newInstance(getListener().getBill(position));
    }

    @Override
    public int getCount() {
        return getListener().getCountBills();
    }

    @Override
    public View getCustomTabView(ViewGroup parent, int position) {

        View view = inflateView();

        TextView tv = (TextView) view.findViewById(R.id.view_pager_bill_tab__tv);
        tv.setText(getListener().getPageTitle(position));

        setTitleColor(position, tv);
        return view;
    }

    private void setTitleColor(int position, TextView tv) {
        Bill.State state = getListener().getBill(position).getState();
        tv.setTextColor(BillColorSelector.getColor(state, getListener().getContext()));
    }

    private View inflateView() {
        return getLayoutInflater().inflate(R.layout.view_pager_bill_tab, null);
    }

    @Override
    public void tabSelected(View tab) {
        TextView tv = (TextView) tab.findViewById(R.id.view_pager_bill_tab__tv);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        tv.setTypeface(null, Typeface.BOLD);
    }

    @Override
    public void tabUnselected(View tab) {
        TextView tv = (TextView) tab.findViewById(R.id.view_pager_bill_tab__tv);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setTypeface(null, Typeface.BOLD);
    }

    private BillViewPagerListener getListener() {
        return listener;
    }

    private LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public interface BillViewPagerListener {

        int getCountBills();

        String getPageTitle(int position);

        Bill getBill(int position);

        Context getContext();
    }
}