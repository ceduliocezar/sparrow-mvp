package com.cedulio.sparrow.bill.list;


import com.astuetz.PagerSlidingTabStrip;
import com.cedulio.custom_ui.TriangleView;
import com.cedulio.sparrow.bill.list.adapter.BillViewPagerAdapter;

import com.cedulio.sparrow.R;
import com.cedulio.sparrow.bill.list.utilities.BillColorSelector;
import com.cedulio.sparrow.domain.Bill;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BillListActivity extends AppCompatActivity implements BillListView, BillViewPagerAdapter.BillViewPagerListener, ViewPager.OnPageChangeListener {


    private ViewPager mPager;

    private BillViewPagerAdapter mPagerAdapter;

    private PagerSlidingTabStrip tabs;

    private TextView iconBack;
    private BillListPresenter billPresenter;
    private TriangleView triangleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_bill_list);

        initPresenter();
        initViewReferences();

        getBillPresenter().onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getBillPresenter().onPauseView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBillPresenter().onResumeView();
    }

    private void initPresenter() {
        this.billPresenter = new BillListPresenter(this);
    }

    private void initViewReferences() {
        mPager = (ViewPager) findViewById(R.id.act_bill_list__vp);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setOnPageChangeListener(this);
        iconBack = (TextView) findViewById(R.id.act_bill_list__tv__back);
        triangleView = (TriangleView) findViewById(R.id.act_bill_list__tv);
    }

    @Override
    public void renderBillList(final List<Bill> bills) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPagerAdapter = new BillViewPagerAdapter(getSupportFragmentManager(),
                        BillListActivity.this, BillListActivity.this);

                mPager.setAdapter(mPagerAdapter);

                tabs.setViewPager(mPager);

                Typeface typeface = Typeface.createFromAsset(getAssets(), "icomoon.ttf");
                iconBack.setTypeface(typeface);

                iconBack.setText(String.valueOf((char) 0xe5c4));
                iconBack.setTextColor(Color.parseColor("#7ED321"));

            }
        });

    }

    @Override
    public void updateMarker(Bill bill) {
        triangleView.setColor(BillColorSelector.getColor(bill.getState(), this));
    }

    @Override
    public void showLoading() {
        Log.d("debug", "showLoading");
    }

    @Override
    public void hideLoading() {
        Log.d("debug", "hideLoading");
    }

    @Override
    public void showRetry() {
        Log.d("debug", "showRetry");
    }

    @Override
    public void hideRetry() {
        Log.d("debug", "hideRetry");
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getCountBills() {
        return getBillPresenter().getCountBills();
    }

    @Override
    public String getPageTitle(int position) {
        return getBillPresenter().getPageTitle(position);
    }

    @Override
    public Bill getBill(int position) {
        return getBillPresenter().getBill(position);
    }

    private BillListPresenter getBillPresenter() {
        return billPresenter;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getBillPresenter().onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
