package com.cedulio.sparrow.domain.bill.visibility;

import com.cedulio.sparrow.domain.interactor.bill.visibility.InterestVisibilityManager;
import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.Summary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class InterestVisibilitManagerTest {

    @Mock
    private Bill bill;

    @Mock
    private Summary summary;

    @Test
    public void testInterestNegative() {

        InterestVisibilityManager visibilityManager = new InterestVisibilityManager();

        mockState(Bill.State.CLOSED);
        mockSummary();
        mockInterest(-1.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("CLOSED state with interest negative do not show the field", mayShowField);

    }

    @Test
    public void testInterestPositive() {

        InterestVisibilityManager visibilityManager = new InterestVisibilityManager();

        mockState(Bill.State.CLOSED);
        mockSummary();
        mockInterest(1.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertTrue("CLOSED state with interest must  show the field", mayShowField);

    }

    @Test
    public void testInterestZero() {

        InterestVisibilityManager visibilityManager = new InterestVisibilityManager();

        mockState(Bill.State.CLOSED);
        mockSummary();
        mockInterest(0.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("CLOSED state with no interest do not show the field", mayShowField);
    }

    @Test
    public void testNotClosed() {

        InterestVisibilityManager visibilityManager = new InterestVisibilityManager();

        mockState(Bill.State.OPEN);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("OPEN state do not show the field", mayShowField);
    }

    private void mockInterest(double interest) {
        when(summary.getInterest()).thenReturn(interest);
    }

    private void mockState(Bill.State state) {
        when(bill.getState()).thenReturn(state);
    }

    private void mockSummary() {
        when(bill.getSummary()).thenReturn(summary);
    }
}
