package com.cedulio.sparrow.data.repository.datasource.bill;


import com.cedulio.sparrow.data.entity.BillEntity;

import java.io.IOException;
import java.util.List;

public interface BillDataStore {

    List<BillEntity> getBills() throws IOException;

    BillEntity getBill(String id);

}
