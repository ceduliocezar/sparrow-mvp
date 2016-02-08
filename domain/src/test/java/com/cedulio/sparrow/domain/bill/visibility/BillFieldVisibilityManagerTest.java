package com.cedulio.sparrow.domain.bill.visibility;


import com.cedulio.sparrow.domain.interactor.bill.visibility.BillFieldVisibilityManager;
import com.cedulio.sparrow.domain.model.Bill;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillFieldVisibilityManagerTest {

    @Mock
    private Bill bill;

    @Test
    public void testisClosedPassingFuture() {

        mockState(Bill.State.FUTURE);

        BillFieldVisibilityManager billFieldVisibilityManager = createNewManager();

        boolean billClosed = billFieldVisibilityManager.isBillStateClosed(bill);

        Assert.assertFalse("State FUTURE should return false", billClosed);
    }

    @Test
    public void testisClosedPassingClosed() {

        mockState(Bill.State.CLOSED);

        BillFieldVisibilityManager billFieldVisibilityManager = createNewManager();

        boolean billClosed = billFieldVisibilityManager.isBillStateClosed(bill);

        Assert.assertTrue("State CLOSED should return true", billClosed);

    }

    @Test
    public void testisClosedPassingOpen() {

        mockState(Bill.State.OPEN);

        BillFieldVisibilityManager billFieldVisibilityManager = createNewManager();

        boolean billClosed = billFieldVisibilityManager.isBillStateClosed(bill);

        Assert.assertFalse("State OPEN should return false", billClosed);

    }

    private BillFieldVisibilityManager createNewManager() {
        return new BillFieldVisibilityManager() {
            @Override
            public boolean mayShow(Bill bill) {
                return false;
            }
        };
    }

    private void mockState(Bill.State state) {
        when(bill.getState()).thenReturn(state);
    }

}
