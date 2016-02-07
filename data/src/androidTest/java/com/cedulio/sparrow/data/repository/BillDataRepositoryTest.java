package com.cedulio.sparrow.data.repository;


import com.cedulio.sparrow.domain.Bill;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class BillDataRepositoryTest {

    @Test
    public void testGetBills() throws IOException {
        BillDataRepository repository = new BillDataRepository();

        List<Bill>  bills =   repository.getBills();

        Assert.assertFalse("Bill list should not be empty" , bills.isEmpty());
    }
}
