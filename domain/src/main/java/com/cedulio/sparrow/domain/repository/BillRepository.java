package com.cedulio.sparrow.domain.repository;


import com.cedulio.sparrow.domain.exception.ConnectionProblemException;
import com.cedulio.sparrow.domain.model.Bill;

import java.util.List;

public interface BillRepository {

    List<Bill> getBills() throws ConnectionProblemException;

    Bill getBill(String id);

}
