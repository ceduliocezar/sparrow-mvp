package com.cedulio.sparrow.data.entity.mapper;

import com.cedulio.sparrow.data.entity.BillEntity;
import com.cedulio.sparrow.domain.model.Bill;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BillEntityDataMapperTest {

    @Test
    public void testTranformEntityExecution() {
        BillEntityDataMapper mapper = new BillEntityDataMapper();
        BillEntity billEntity = new BillEntity();

        mapper.transform(billEntity);
    }


    @Test
    public void testTranformEntityNull() {
        BillEntityDataMapper mapper = new BillEntityDataMapper();

        BillEntity billEntity = null;
        Bill bill = mapper.transform(billEntity);

        Assert.assertNull(bill);
    }

    @Test
    public void testTranformEntityListExecution() {
        BillEntityDataMapper mapper = new BillEntityDataMapper();
        List<BillEntity> billEntityList = new ArrayList<BillEntity>();

        mapper.transform(billEntityList);
    }

    @Test
    public void testTranformEntityListNull() {
        BillEntityDataMapper mapper = new BillEntityDataMapper();
        List<BillEntity> billEntityList = null;

        List<Bill> billList = mapper.transform(billEntityList);

        Assert.assertNotNull("For null references, return empty list", billList);
    }

}
