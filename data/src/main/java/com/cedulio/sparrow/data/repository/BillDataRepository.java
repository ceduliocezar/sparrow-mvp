package com.cedulio.sparrow.data.repository;


import com.cedulio.sparrow.data.entity.mapper.BillEntityDataMapper;
import com.cedulio.sparrow.data.repository.datasource.bill.BillDataStore;
import com.cedulio.sparrow.data.repository.datasource.bill.BillDataStoreFactory;
import com.cedulio.sparrow.domain.exception.ConnectionProblemException;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.repository.BillRepository;

import android.content.Context;

import java.util.List;

public class BillDataRepository implements BillRepository {

    private final BillEntityDataMapper mapper;

    private Context context;

    public BillDataRepository(Context context) {
        this.mapper = new BillEntityDataMapper();
        this.context = context.getApplicationContext();
    }

    @Override
    public List<Bill> getBills() throws ConnectionProblemException {

        List<Bill> bills = getBillsCloud();

        return bills;
    }

    private List<Bill> getBillsCloud() throws ConnectionProblemException {

        BillDataStore dataStore = BillDataStoreFactory.getInstance(context).createCloudDataStore();

        return mapper.transform(dataStore.getBills());
    }

    @Override
    public Bill getBill(String id) {
        BillDataStore dataStore = BillDataStoreFactory.getInstance(context).createCloudDataStore();
        return mapper.transform(dataStore.getBill(id));
    }
}
