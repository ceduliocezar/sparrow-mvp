package com.cedulio.sparrow.data.entity.mapper;

import com.cedulio.sparrow.data.entity.SummaryEntity;
import com.cedulio.sparrow.domain.model.Summary;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SummaryEntityDataMapperTest {

    @Test
    public void testTranformEntityExecution() {
        SummaryEntityDataMapper mapper = new SummaryEntityDataMapper();
        SummaryEntity summaryEntity = new SummaryEntity();

        mapper.transform(summaryEntity);
    }


    @Test
    public void testTranformEntityNull() {
        SummaryEntityDataMapper mapper = new SummaryEntityDataMapper();
        SummaryEntity summaryEntity = new SummaryEntity();

        Summary summary = mapper.transform(summaryEntity);

        Assert.assertNotNull(summary);
    }

    @Test
    public void testTranformEntityListExecution() {
        SummaryEntityDataMapper mapper = new SummaryEntityDataMapper();
        List<SummaryEntity> billEntityList = Collections.emptyList();

        mapper.transform(billEntityList);
    }

    @Test
    public void testTranformEntityListNull() {
        SummaryEntityDataMapper mapper = new SummaryEntityDataMapper();
        List<SummaryEntity> summaryEntityList = null;

        List<Summary> summaryList = mapper.transform(summaryEntityList);

        Assert.assertNotNull("For null references, return empty list", summaryList);
    }

}