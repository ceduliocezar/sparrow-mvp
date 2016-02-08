package com.cedulio.sparrow.domain.interactor.bill.formatter;

import com.cedulio.sparrow.domain.utilities.DefaultLocale;
import com.cedulio.sparrow.domain.interactor.bill.CurrencyFormatter;
import com.cedulio.sparrow.domain.interactor.UseCase;

public class MonthExpensesFormatter extends UseCase {

    private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

    private DefaultLocale mDefaultLocale = DefaultLocale.getInstance();

    public String format(double gastosDoMes) {
        return currencyFormatter.format(gastosDoMes, getDefaultLocale().getLocale());
    }

    public CurrencyFormatter getCurrencyFormatter() {
        return currencyFormatter;
    }

    public DefaultLocale getDefaultLocale() {
        return mDefaultLocale;
    }
}
