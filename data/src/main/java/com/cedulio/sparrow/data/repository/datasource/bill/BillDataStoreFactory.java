package com.cedulio.sparrow.data.repository.datasource.bill;


public class BillDataStoreFactory {

    private static BillDataStoreFactory INSTANCE = new BillDataStoreFactory();

    public static BillDataStoreFactory getInstance() {
        return INSTANCE;
    }

    private BillDataStoreFactory() {

    }

    public BillDataStore createCloudDataStore(){
        return new CloudBillDataStore();
    }
}
