package com.cedulio.sparrow.data.repository.datasource.bill;


import android.content.Context;

public class BillDataStoreFactory {

    private static BillDataStoreFactory INSTANCE;

    private Context context;

    private BillDataStoreFactory(Context context) {
        this.context = context;
    }

    public static BillDataStoreFactory getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new BillDataStoreFactory(context);
        }
        return INSTANCE;
    }

    public BillDataStore createCloudDataStore() {
        return new CloudBillDataStore(context);
    }
}
