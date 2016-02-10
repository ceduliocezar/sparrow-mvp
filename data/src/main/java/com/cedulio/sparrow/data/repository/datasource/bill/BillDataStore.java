package com.cedulio.sparrow.data.repository.datasource.bill;


import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.domain.exception.ConnectionProblemException;

import java.util.List;

public interface BillDataStore {

    List<BillEntity> getBills() throws ConnectionProblemException;

    BillEntity getBill(String id);

}
