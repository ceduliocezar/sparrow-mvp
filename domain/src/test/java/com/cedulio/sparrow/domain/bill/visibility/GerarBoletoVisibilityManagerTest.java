package com.cedulio.sparrow.domain.bill.visibility;

import com.cedulio.sparrow.domain.interactor.bill.visibility.GerarBoletoVisibilityManager;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.Summary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GerarBoletoVisibilityManagerTest {

    @Mock
    private Bill bill;

    @Mock
    private Summary summary;

    @Test
    public void testOverdueNegative() {

        GerarBoletoVisibilityManager visibilityManager = new GerarBoletoVisibilityManager();

        mockState(Bill.State.OVERDUE);
        mockSummary();
        mockPaidValue(-1.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertTrue("Overdue state with paid value negative must return true", mayShowField);

    }

    @Test
    public void testOverduePositive() {

        GerarBoletoVisibilityManager visibilityManager = new GerarBoletoVisibilityManager();

        mockState(Bill.State.OVERDUE);
        mockSummary();
        mockPaidValue(1.0);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("OVERDUE state with paid value negative must return false", mayShowField);

    }

    @Test
    public void testClosed() {

        GerarBoletoVisibilityManager visibilityManager = new GerarBoletoVisibilityManager();

        mockState(Bill.State.CLOSED);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertTrue("CLOSED state must return true", mayShowField);

    }

    @Test
    public void testOpen() {

        GerarBoletoVisibilityManager visibilityManager = new GerarBoletoVisibilityManager();

        mockState(Bill.State.OPEN);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertTrue("OPEN state must return true", mayShowField);
    }

    @Test
    public void testFuture() {

        GerarBoletoVisibilityManager visibilityManager = new GerarBoletoVisibilityManager();

        mockState(Bill.State.FUTURE);

        boolean mayShowField = visibilityManager.mayShow(bill);

        Assert.assertFalse("FUTURE state must return false", mayShowField);
    }

    private void mockState(Bill.State state) {
        when(bill.getState()).thenReturn(state);
    }

    private void mockPaidValue(double paidValue) {
        when(summary.getPaid()).thenReturn(paidValue);
    }

    private void mockSummary() {
        when(bill.getSummary()).thenReturn(summary);
    }


}
