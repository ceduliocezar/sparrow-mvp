package com.cedulio.sparrow.domain.formatter.bill;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LineItemValueFormatterTest {

    @Test
    public void testBRFormat() {

        double value = 10.00;

        LineItemValueFormatter formatter = new LineItemValueFormatter();

        String formattedDescription = formatter.format(value);

        Assert.assertTrue("Expected format= 10,00, formatted string=" + formattedDescription,
                formattedDescription.equals(" 10,00"));
    }

    @Test
    public void testBRFormatNegative() {

        double value = -10.00;

        LineItemValueFormatter formatter = new LineItemValueFormatter();

        String formattedDescription = formatter.format(value);

        Assert.assertTrue("Expected format=- 10,00, formatted string=" + formattedDescription,
                formattedDescription.equals("- 10,00"));
    }

    @Test
    public void testBRFormatThousand() {

        double value = 1000.00;

        LineItemValueFormatter formatter = new LineItemValueFormatter();

        String formattedDescription = formatter.format(value);

        Assert.assertTrue("Expected format= 1.000,00, formatted string=" + formattedDescription,
                formattedDescription.equals(" 1.000,00"));
    }


}
