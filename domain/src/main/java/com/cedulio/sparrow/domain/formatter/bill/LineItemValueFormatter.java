package com.cedulio.sparrow.domain.formatter.bill;


import com.cedulio.sparrow.domain.formatter.CurrencyFormatter;
import com.cedulio.sparrow.domain.language.DefaultLocale;

public class LineItemValueFormatter {

    private static CurrencyFormatter currencyFormatter = new CurrencyFormatter();

    public static String format(double value) {
        return currencyFormatter.formatWithoutSymbol(value, DefaultLocale.getLocale());
    }
}
