package com.cedulio.sparrow.domain.formatter;

import com.cedulio.sparrow.domain.language.DefaultLocale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CurrencyFormatterTest {

    @Test
    public void testBRNoSymbol() {

        double value = 10.00;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedDescription = formatter.formatWithoutSymbol(value,
                DefaultLocale.getLocale());

        Assert.assertTrue("Expected format= 10,00, formatted string=" + formattedDescription,
                formattedDescription.equals(" 10,00"));
    }

    @Test
    public void testBRNoSymbolNegative() {

        double value = -10.00;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedDescription = formatter.formatWithoutSymbol(value,
                DefaultLocale.getLocale());

        Assert.assertTrue("Expected format=- 10,00, formatted string=" + formattedDescription,
                formattedDescription.equals("- 10,00"));
    }

    @Test
    public void testBRNoSymbolThousand() {

        double value = 1000.00;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedDescription = formatter.formatWithoutSymbol(value,
                DefaultLocale.getLocale());

        Assert.assertTrue("Expected format= 1.000,00, formatted string=" + formattedDescription,
                formattedDescription.equals(" 1.000,00"));
    }

    @Test
    public void testBRSymbol() {

        double value = 10.00;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedDescription = formatter.format(value,
                DefaultLocale.getLocale());

        Assert.assertFalse("Expected format=R$ 10,00, formatted string=" + formattedDescription,
                formattedDescription.equals("R$ 10,00"));
    }

    @Test
    public void testBRSymbolNegative() {

        double value = -10.00;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedDescription = formatter.format(value,
                DefaultLocale.getLocale());

        Assert.assertTrue("Expected format=-R$ 10,00, formatted string=" + formattedDescription,
                formattedDescription.equals("-R$ 10,00"));
    }

    @Test
    public void testBRSymbolThousand() {

        double value = 1000.00;

        CurrencyFormatter formatter = new CurrencyFormatter();

        String formattedDescription = formatter.format(value,
                DefaultLocale.getLocale());

        Assert.assertTrue("Expected format=R$ 1.000,00, formatted string=" + formattedDescription,
                formattedDescription.equals("R$ 1.000,00"));
    }
}
