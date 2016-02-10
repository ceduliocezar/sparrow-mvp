package com.cedulio.sparrow.data.repository.datasource.bill;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.cedulio.sparrow.data.R;
import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.data.net.rest.SparrowService;
import com.cedulio.sparrow.data.transfer.BillTO;
import com.cedulio.sparrow.domain.exception.ConnectionProblemException;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class CloudBillDataStore implements BillDataStore {

    private Gson gson;

    private SparrowService sparrowService;

    private Context context;

    public CloudBillDataStore(Context context) {
        setContext(context.getApplicationContext());
        initGson();
        initSparrowService();
    }

    private void initSparrowService() {
        this.sparrowService = new Retrofit.Builder().baseUrl(SparrowService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(SparrowService.class);
    }

    private void initGson() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
    }

    @Override
    public List<BillEntity> getBills() throws ConnectionProblemException {
        return getBillList();
    }

    private List<BillEntity> getBillList() throws ConnectionProblemException {

        try {
            List<BillTO> billTOList = getSparrowService().listBill().execute().body();

            return extractBillEntityList(billTOList);
        } catch (IOException e) {
            throw new ConnectionProblemException(
                    getContext().getString(R.string.connection_problem_message));
        }
    }

    private List<BillEntity> extractBillEntityList(List<BillTO> billTOList) {

        List<BillEntity> billEntityList = new ArrayList<>();

        for (BillTO billTO : billTOList) {
            billEntityList.add(billTO.getBill());
        }

        return billEntityList;
    }

    @Override
    public BillEntity getBill(String id) {
        throw new UnsupportedOperationException("Not available");
    }

    private SparrowService getSparrowService() {
        return sparrowService;
    }

    private Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }
}
