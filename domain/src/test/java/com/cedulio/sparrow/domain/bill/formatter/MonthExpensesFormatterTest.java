package com.cedulio.sparrow.domain.bill.formatter;


import com.cedulio.sparrow.domain.interactor.bill.formatter.MonthExpensesFormatter;
import com.cedulio.sparrow.domain.Bill;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MonthExpensesFormatterTest {

    @Mock
    private Bill bill;

    @Test
    public void testBRFormat() {

        double monthExpenses = 123.45;

        MonthExpensesFormatter formatter = new MonthExpensesFormatter();

        String valueFormatted = formatter.format(monthExpenses);

        Assert.assertTrue("The correct format is R$ ##,##", valueFormatted.equals("R$ 123,45"));
    }
}
