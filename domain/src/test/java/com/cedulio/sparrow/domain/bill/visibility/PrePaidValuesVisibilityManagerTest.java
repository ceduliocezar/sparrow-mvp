package com.cedulio.sparrow.domain.bill.visibility;


import com.cedulio.sparrow.domain.interactor.bill.visibility.PrePaidValuesVisibilityChecker;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.Summary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PrePaidValuesVisibilityManagerTest {

    @Mock
    private Bill bill;

    @Mock
    private Summary summary;

    @Test
    public void testPastBalanceNegative() {

        PrePaidValuesVisibilityChecker visibilityManager = new PrePaidValuesVisibilityChecker();

        mockState(Bill.State.CLOSED);
        mockSummary();
        mockPastBalance(-1.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertTrue("CLOSED state with past balance negative must show the field",
                mayShowField);

    }

    @Test
    public void testPastBalancePositive() {

        PrePaidValuesVisibilityChecker visibilityManager = new PrePaidValuesVisibilityChecker();

        mockState(Bill.State.CLOSED);
        mockSummary();
        mockPastBalance(1.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("CLOSED state with past balance positive do not show the field",
                mayShowField);

    }

    @Test
    public void testPastBalanceZero() {

        PrePaidValuesVisibilityChecker visibilityManager = new PrePaidValuesVisibilityChecker();

        mockState(Bill.State.CLOSED);
        mockSummary();
        mockPastBalance(0.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("CLOSED state with 0 past balance do not show the field",
                mayShowField);

    }

    @Test
    public void testNotClose() {

        PrePaidValuesVisibilityChecker visibilityManager = new PrePaidValuesVisibilityChecker();

        mockState(Bill.State.OPEN);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("Not CLOSED state do not show the field",
                mayShowField);

    }

    private void mockPastBalance(double pastBalance) {
        when(summary.getPastBalance()).thenReturn(pastBalance);
    }

    private void mockState(Bill.State state) {
        when(bill.getState()).thenReturn(state);
    }

    private void mockSummary() {
        when(bill.getSummary()).thenReturn(summary);
    }
}
