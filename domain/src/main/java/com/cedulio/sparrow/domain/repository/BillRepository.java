package com.cedulio.sparrow.domain.repository;


import com.cedulio.sparrow.domain.model.Bill;

import java.io.IOException;
import java.util.List;

public interface BillRepository {

    List<Bill> getBills() throws IOException;

    Bill getBill(String id);

}
