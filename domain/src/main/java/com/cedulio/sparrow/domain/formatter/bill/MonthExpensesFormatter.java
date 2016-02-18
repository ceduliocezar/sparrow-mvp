package com.cedulio.sparrow.domain.formatter.bill;

import com.cedulio.sparrow.domain.formatter.CurrencyFormatter;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

public class MonthExpensesFormatter {

    private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

    public String format(double gastosDoMes) {
        return currencyFormatter.format(gastosDoMes, DefaultLocale.getLocale());
    }

    public CurrencyFormatter getCurrencyFormatter() {
        return currencyFormatter;
    }

}
