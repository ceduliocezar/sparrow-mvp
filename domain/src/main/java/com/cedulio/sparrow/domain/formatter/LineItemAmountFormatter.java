package com.cedulio.sparrow.domain.formatter;


import com.cedulio.sparrow.domain.utilities.DefaultLocale;

public class LineItemAmountFormatter {

    private static CurrencyFormatter currencyFormatter = new CurrencyFormatter();

    public static String format(double value) {
        return currencyFormatter.format(value, DefaultLocale.getLocale());
    }
}
