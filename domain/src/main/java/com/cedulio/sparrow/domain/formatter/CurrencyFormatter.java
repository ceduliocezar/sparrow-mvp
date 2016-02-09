package com.cedulio.sparrow.domain.formatter;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    public String format(double value, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(value);
    }

    public String formatWithoutSymbol(double value, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(value);
    }

}