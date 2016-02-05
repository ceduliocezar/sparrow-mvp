package com.cedulio.sparrow.bill.list;


import com.google.gson.Gson;

import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.Bill;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class BillListActivity extends AppCompatActivity implements BillListView {


    private ViewPager mPager;

    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bill_list);

        Log.d("debug", "oncreate");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.act_bill_list_toolbar);
//
//        setSupportActionBar(toolbar);
//        //Your toolbar is now an action bar and you can use it like you always do, for example:
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPager = (ViewPager) findViewById(R.id.act_bill_list__vp);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

//        ((Button) findViewById(R.id.bt)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d("debug","click");
//                Runnable r = new Runnable(){
//
//                    @Override
//                    public void run() {
//                        Log.d("debug","click start");
//                        BillListPresenter presenter = new BillListPresenter(BillListActivity
// .this);
//                        presenter.load();
//                    }
//                };
//
//                new Thread(r).start();
//
//            }
//        });
    }

    @Override
    public void renderBillList(final List<Bill> bills) {
        Log.d("debug", "renderBillList");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) findViewById(R.id.text);
                Gson gson = new Gson();
                tv.setText(gson.toJson(bills));
            }
        });

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
        Log.e("debug", message);
    }

    @Override
    public Context getContext() {
        return this;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new BillListFragment();
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Title " + position;
        }
    }

}
