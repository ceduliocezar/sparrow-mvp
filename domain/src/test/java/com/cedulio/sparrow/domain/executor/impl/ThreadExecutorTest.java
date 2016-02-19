package com.cedulio.sparrow.domain.executor.impl;

import com.cedulio.sparrow.domain.executor.Executor;
import com.cedulio.sparrow.domain.interactor.AbstractUseCase;
import com.cedulio.sparrow.domain.interactor.bill.GetBills;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ThreadExecutorTest {


    @Mock
    AbstractUseCase useCase;

    @Mock
    GetBills getBills;

    @Test
    public void testRun() {

        Executor executor = ThreadExecutor.getInstance();
        executor.execute(useCase);

        verify(useCase, only()).run();
    }

}
