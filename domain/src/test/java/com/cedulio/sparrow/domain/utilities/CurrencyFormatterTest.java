package com.cedulio.sparrow.domain.utilities;


import com.cedulio.sparrow.domain.Bill;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyFormatterTest {

    @Mock
    private Bill bill;

    @Test
    public void testBRFormat() {

        double value = 12.34;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedString = formatter.format(value, new Locale("pt", "BR"));

        Assert.assertTrue("The correct format is R$ ##,##", formattedString.equals("R$ 12,34"));
    }

    @Test
    public void testUSFormat() {

        double value = 12.34;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedString = formatter.format(value, Locale.US);

        Assert.assertTrue("The correct format is $12.34, NOT " + formattedString,
                formattedString.equals("$12.34"));
    }
}
