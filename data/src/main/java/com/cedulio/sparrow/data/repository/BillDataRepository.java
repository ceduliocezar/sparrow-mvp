package com.cedulio.sparrow.data.repository;


import com.cedulio.sparrow.data.cache.CacheManager;
import com.cedulio.sparrow.data.entity.mapper.BillEntityMapper;
import com.cedulio.sparrow.data.repository.datasource.bill.BillDataStore;
import com.cedulio.sparrow.data.repository.datasource.bill.BillDataStoreFactory;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.repository.BillRepository;

import android.util.Log;

import java.io.IOException;
import java.util.List;

public class BillDataRepository implements BillRepository {

    private final BillEntityMapper mapper;

    private CacheManager cacheManager;


    public BillDataRepository() {
        mapper = new BillEntityMapper();
        cacheManager = new CacheManager();
    }

    @Override
    public List<Bill> getBills() throws IOException {

        List<Bill> bills = null;

        if (isCacheValid()) {
            bills = getBillsLocal();
        } else {
            bills = getBillsCloud();
        }

        return bills;
    }

    private List<Bill> getBillsCloud() throws IOException {

        Log.d("debug","getCloud");
        BillDataStore dataStore = BillDataStoreFactory.getInstance().createCloudDataStore();

        return mapper.transform(dataStore.getBills());
    }

    private List<Bill> getBillsLocal() throws IOException {
        Log.d("debug","getLocal");
        BillDataStore dataStore = BillDataStoreFactory.getInstance().createCloudDataStore();

        return mapper.transform(dataStore.getBills());
    }

    private boolean isCacheValid() {
        return cacheManager.isCacheValid();
    }

    @Override
    public Bill getBill(String id) {
        BillDataStore dataStore = BillDataStoreFactory.getInstance().createCloudDataStore();
        return mapper.transform(dataStore.getBill(id));
    }
}
