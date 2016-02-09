package com.cedulio.sparrow.domain.interactor.bill;

import com.cedulio.sparrow.domain.interactor.UseCase;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter extends UseCase {

    public String format(double value, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(value);
    }

    public String formatWithoutSymbol(double value, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(value);
    }

}