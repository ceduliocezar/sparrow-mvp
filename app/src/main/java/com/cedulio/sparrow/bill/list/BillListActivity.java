package com.cedulio.sparrow.bill.list;


import com.google.gson.Gson;

import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.Bill;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BillListActivity extends Activity implements BillListView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);

        Log.d("debug", "oncreate");

        ((Button) findViewById(R.id.bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("debug","click");
                Runnable r = new Runnable(){

                    @Override
                    public void run() {
                        Log.d("debug","click start");
                        BillListPresenter presenter = new BillListPresenter(BillListActivity.this);
                        presenter.load();
                    }
                };

                new Thread(r).start();

            }
        });
    }

    @Override
    public void renderBillList(final List<Bill> bills) {
        Log.d("debug","renderBillList");
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
}
