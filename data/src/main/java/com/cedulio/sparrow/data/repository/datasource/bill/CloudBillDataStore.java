package com.cedulio.sparrow.data.repository.datasource.bill;


import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.data.transfer.BillTO;
import com.cedulio.sparrow.data.net.SparrowService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class CloudBillDataStore implements BillDataStore {


    private final SparrowService sparrowService;

    public CloudBillDataStore() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        this.sparrowService = new Retrofit.Builder().baseUrl(SparrowService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(SparrowService.class);
    }

    @Override
    public List<BillEntity> getBills() throws IOException {
        return getBillList();
    }

    private List<BillEntity> getBillList() throws IOException {

        Call<List<BillTO>> callEntity = sparrowService.listBill();
        Response<List<BillTO>> response = callEntity.execute();
        List<BillTO> billTOList = response.body();

        return extractBillEntityList(billTOList);
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
}
