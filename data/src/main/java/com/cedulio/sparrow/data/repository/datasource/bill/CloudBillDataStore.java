package com.cedulio.sparrow.data.repository.datasource.bill;


import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.data.net.SparrowService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CloudBillDataStore implements BillDataStore {


    private final SparrowService sparrowService;

    public CloudBillDataStore() {
        this.sparrowService = new Retrofit.Builder().baseUrl(SparrowService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SparrowService.class);
    }

    @Override
    public List<BillEntity> getBills() throws IOException {

        Call<List<BillEntity>> callEntity = sparrowService.listBill();
        Response<List<BillEntity>> response = callEntity.execute();
        List<BillEntity> billEntityList = response.body();
        return billEntityList;
    }

    @Override
    public BillEntity getBill(String id) {
        throw new UnsupportedOperationException("Not available");
    }
}
