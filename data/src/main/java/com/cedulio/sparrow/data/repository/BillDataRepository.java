package com.cedulio.sparrow.data.repository;


import com.cedulio.sparrow.data.entity.mapper.BillEntityMapper;
import com.cedulio.sparrow.data.repository.datasource.bill.BillDataStore;
import com.cedulio.sparrow.data.repository.datasource.bill.BillDataStoreFactory;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.repository.BillRepository;

import java.io.IOException;
import java.util.List;

public class BillDataRepository implements BillRepository {

    private final BillEntityMapper mapper;

    public BillDataRepository() {
        mapper = new BillEntityMapper();
    }

    @Override
    public List<Bill> getBills() throws IOException {

        BillDataStore dataStore = BillDataStoreFactory.getInstance().createCloudDataStore();

        return mapper.transform(dataStore.getBills());
    }

    @Override
    public Bill getBill(String id) {
        BillDataStore dataStore = BillDataStoreFactory.getInstance().createCloudDataStore();
        return mapper.transform(dataStore.getBill(id));
    }
}
