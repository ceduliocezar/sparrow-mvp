package com.cedulio.sparrow.bill.list;


import com.astuetz.PagerSlidingTabStrip;
import com.cedulio.custom_ui.TriangleView;
import com.cedulio.sparrow.R;
import com.cedulio.sparrow.bill.list.adapter.BillViewPagerAdapter;
import com.cedulio.sparrow.bill.list.utilities.BillColorSelector;
import com.cedulio.sparrow.domain.model.Bill;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillListActivity extends AppCompatActivity
        implements BillListView, BillViewPagerAdapter.BillViewPagerListener,
        ViewPager.OnPageChangeListener {


    @Bind(R.id.act_bill_list__vp)
    ViewPager viewPagerBills;

    @Bind(R.id.act_bill_list_tab_strip)
    PagerSlidingTabStrip tabs;

    @Bind(R.id.act_bill_list__tv__back)
    TextView iconBack;

    @Bind(R.id.act_bill_list__tv__load_back)
    TextView loadIconBack;

    @Bind(R.id.act_bill_list__tv)
    TriangleView triangleView;

    @Bind(R.id.act_bill_list__ln__loader)
    LinearLayout loadingLayout;

    private BillViewPagerAdapter mPagerAdapter;

    private BillListPresenter billListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_bill_list);

        ButterKnife.bind(this);

        initPresenter();
        initBackButton();
        setTabsListener();

        getBillListPresenter().onCreateView(savedInstanceState);
    }

    private void setTabsListener() {
        tabs.setOnPageChangeListener(this);
    }

    private void initBackButton() {
        iconBack.setTypeface(getIconTypeFace());
        iconBack.setText(getBackIconString());

        loadIconBack.setTypeface(getIconTypeFace());
        loadIconBack.setText(getBackIconString());
    }

    private Typeface getIconTypeFace() {
        return Typeface.createFromAsset(getAssets(), "icomoon.ttf");
    }

    @NonNull
    private String getBackIconString() {
        return String.valueOf((char) 0xe5c4);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getBillListPresenter().onPauseView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBillListPresenter().onResumeView();
    }

    private void initPresenter() {
        this.billListPresenter = new BillListPresenter(this);
    }

    @Override
    public void renderBillList(final List<Bill> bills) {

        mPagerAdapter = new BillViewPagerAdapter(getSupportFragmentManager(),
                BillListActivity.this, BillListActivity.this);
        viewPagerBills.setAdapter(mPagerAdapter);

        tabs.setViewPager(viewPagerBills);
    }

    private void setIconBackColor(int idColor) {
        iconBack.setTextColor(idColor);
    }

    @Override
    public void updateViews(Bill bill) {
        int idColor = BillColorSelector.getColor(bill.getState(), this);
        triangleView.setColor(idColor);
        setIconBackColor(idColor);
    }

    @Override
    public void selectCurrentBillByPosition(int i) {
        viewPagerBills.setCurrentItem(i, true);
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
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
        return getBillListPresenter().getCountBills();
    }

    @Override
    public String getPageTitle(int position) {
        return getBillListPresenter().getPageTitle(position);
    }

    @Override
    public Bill getBill(int position) {
        return getBillListPresenter().getBill(position);
    }

    private BillListPresenter getBillListPresenter() {
        return billListPresenter;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getBillListPresenter().onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getBillListPresenter().onSaveInstanceState(outState);
    }
}
