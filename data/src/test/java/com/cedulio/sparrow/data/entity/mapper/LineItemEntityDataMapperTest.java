package com.cedulio.sparrow.data.entity.mapper;

import com.cedulio.sparrow.data.entity.LineItemEntity;
import com.cedulio.sparrow.domain.model.LineItem;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LineItemEntityDataMapperTest {

    @Test
    public void testTranformEntityExecution() {
        LineItemEntityDataMapper mapper = new LineItemEntityDataMapper();
        LineItemEntity lineItemEntity = new LineItemEntity();

        mapper.transform(lineItemEntity);
    }


    @Test
    public void testTranformEntityNull() {
        LineItemEntityDataMapper mapper = new LineItemEntityDataMapper();
        LineItemEntity lineItemEntity = new LineItemEntity();

        LineItem lineItem = mapper.transform(lineItemEntity);

        Assert.assertNotNull(lineItem);
    }

    @Test
    public void testTranformEntityListExecution() {
        LineItemEntityDataMapper mapper = new LineItemEntityDataMapper();
        List<LineItemEntity> lineItemEntityList = Collections.emptyList();

        mapper.transform(lineItemEntityList);
    }

    @Test
    public void testTranformEntityListNull() {
        LineItemEntityDataMapper mapper = new LineItemEntityDataMapper();
        List<LineItemEntity> lineItemEntityList = null;

        List<LineItem> lineItem = mapper.transform(lineItemEntityList);

        Assert.assertNotNull("For null references, return empty list", lineItem);
    }

}
