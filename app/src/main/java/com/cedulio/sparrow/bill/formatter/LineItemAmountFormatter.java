package com.cedulio.sparrow.bill.formatter;


import com.cedulio.sparrow.domain.interactor.bill.CurrencyFormatter;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

public class LineItemAmountFormatter {

    private static CurrencyFormatter currencyFormatter = new CurrencyFormatter();

    public static String format(double value) {
        return currencyFormatter.format(value, DefaultLocale.getInstance().getLocale());
    }
}
