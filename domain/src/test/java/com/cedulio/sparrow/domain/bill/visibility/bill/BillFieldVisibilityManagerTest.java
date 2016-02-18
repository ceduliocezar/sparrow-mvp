package com.cedulio.sparrow.domain.bill.visibility.bill;


import com.cedulio.sparrow.domain.visibility.bill.BillFieldVisibilityChecker;
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

        BillFieldVisibilityChecker billFieldVisibilityChecker = createNewManager();

        boolean billClosed = billFieldVisibilityChecker.isBillStateClosed(bill);

        Assert.assertFalse("State FUTURE should return false", billClosed);
    }

    @Test
    public void testisClosedPassingClosed() {

        mockState(Bill.State.CLOSED);

        BillFieldVisibilityChecker billFieldVisibilityChecker = createNewManager();

        boolean billClosed = billFieldVisibilityChecker.isBillStateClosed(bill);

        Assert.assertTrue("State CLOSED should return true", billClosed);

    }

    @Test
    public void testisClosedPassingOpen() {

        mockState(Bill.State.OPEN);

        BillFieldVisibilityChecker billFieldVisibilityChecker = createNewManager();

        boolean billClosed = billFieldVisibilityChecker.isBillStateClosed(bill);

        Assert.assertFalse("State OPEN should return false", billClosed);

    }

    private BillFieldVisibilityChecker createNewManager() {
        return new BillFieldVisibilityChecker() {
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
