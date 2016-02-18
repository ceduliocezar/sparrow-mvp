package com.cedulio.sparrow.domain.formatter.bill;


import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.model.LineItem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LineItemDescriptionFormatterTest {

    @Mock
    private LineItem mLineItem;

    @Test
    public void testBRFormatBillNotFuture() {

        Bill.State state = Bill.State.OPEN;

        String mockedTitle = "Futurama Guaicurus";
        mockTitle(mockedTitle);

        LineItemDescriptionFormatter formatter = new LineItemDescriptionFormatter();

        String formattedDescription = formatter.format(mLineItem, state);

        Assert.assertTrue("In this case description should  be equals title",
                formattedDescription.equals(mockedTitle));
    }

    @Test
    public void testBRFormatBillFutureNoCharges() {

        Bill.State state = Bill.State.FUTURE;

        String mockedTitle = "Futurama Guaicurus";
        mockTitle(mockedTitle);

        long charges = 0;
        mockTotalCharges(charges);

        LineItemDescriptionFormatter formatter = new LineItemDescriptionFormatter();

        String formattedDescription = formatter.format(mLineItem, state);

        Assert.assertTrue("In this case description should  be equals title",
                formattedDescription.equals(mockedTitle));
    }

    @Test
    public void testBRFormatBillFutureWithCharges() {

        Bill.State state = Bill.State.FUTURE;

        String mockedTitle = "Futurama Guaicurus";
        mockTitle(mockedTitle);

        long charges = 5;
        mockTotalCharges(charges);

        long index = 2;
        mockIndex(index);

        String stringFormat = "%s %d/%d";

        LineItemDescriptionFormatter formatter = new LineItemDescriptionFormatter();

        String formattedDescription = formatter.format(mLineItem, state);

        String string = String.format("%s %d/%d", mockedTitle, index, charges);

        Assert.assertTrue("The formatted description should be [" + string +"] NOT ["+formattedDescription+"]",
                formattedDescription.equals(string));
    }

    @Test
    public void testBRFormatBillNullTitle() {

        Bill.State state = Bill.State.FUTURE;

        String mockedTitle = null;
        mockTitle(mockedTitle);

        long charges = 5;
        mockTotalCharges(charges);

        long index = 2;
        mockIndex(index);

        String stringFormat = "%s %d/%d";

        LineItemDescriptionFormatter formatter = new LineItemDescriptionFormatter();

        String formattedDescription = formatter.format(mLineItem, state);

        String string = String.format("%s %d/%d", mockedTitle, index, charges);

        Assert.assertTrue(
                "The formatted description should be [" + string + "] NOT [" + formattedDescription
                        + "]",
                formattedDescription.equals(string));
    }


    private void mockIndex(long index) {
        when(mLineItem.getIndex()).thenReturn(index);
    }

    private void mockTitle(String title) {
        when(mLineItem.getTitle()).thenReturn(title);
    }

    private void mockTotalCharges(long charges) {
        when(mLineItem.getCharges()).thenReturn(charges);
    }
}
