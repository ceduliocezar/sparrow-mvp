package com.cedulio.sparrow.data.repository;


import android.content.Context;

import com.cedulio.sparrow.domain.model.Bill;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BillDataRepositoryTest {

    @Mock
    private Bill bill;

//    @Mock
    private Context context;

    @Test
    public void testCloud() throws Exception {

        mockApplicationContext();

        BillDataRepository repository = new BillDataRepository(context);

        List<Bill> list = repository.getBills();

        Assert.assertFalse("The list should not be empty", list.isEmpty());
    }

    private void mockApplicationContext() {
        when(context.getApplicationContext()).thenReturn(context);
    }

    @Test
    public void testCloudMapper() throws Exception {

        mockApplicationContext();

        BillDataRepository repository = new BillDataRepository(context);

        List<Bill> list = repository.getBills();



        Assert.assertNotNull("Not mapped correctly id null", list.get(0).getId());
    }

    @Test
    public void testGetBills() throws Exception {

        mockApplicationContext();
        BillDataRepository repository = new BillDataRepository(context);

        List<Bill>  bills =   repository.getBills();

        Assert.assertFalse("Bill list should not be empty", bills.isEmpty());
    }

}
