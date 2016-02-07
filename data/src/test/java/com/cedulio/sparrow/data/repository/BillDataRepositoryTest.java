package com.cedulio.sparrow.data.repository;


import com.cedulio.sparrow.domain.Bill;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BillDataRepositoryTest {

    @Mock
    private Bill bill;

    @Test
    public void testCloud() throws Exception {

        BillDataRepository repository = new BillDataRepository();

        List<Bill> list = repository.getBills();

        Assert.assertFalse("The list should not be empty", list.isEmpty());
    }

    @Test
    public void testCloudMapper() throws Exception {

        BillDataRepository repository = new BillDataRepository();

        List<Bill> list = repository.getBills();

        Assert.assertNotNull("Not mapped correctly id null", list.get(0).getId());
    }

}
