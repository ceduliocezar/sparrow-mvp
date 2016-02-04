package com.cedulio.sparrow.domain.interactor.bill.formatter;

import com.cedulio.sparrow.domain.utilities.BrazilianLocale;
import com.cedulio.sparrow.domain.utilities.CurrencyFormatter;
import com.cedulio.sparrow.domain.interactor.UseCase;

public class MonthExpensesFormatter extends UseCase {

    private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

    private BrazilianLocale mBrazilianLocale = BrazilianLocale.getInstance();

    public String format(double gastosDoMes) {
        return currencyFormatter.format(gastosDoMes, getBrazilianLocale().getLocale());
    }

    public CurrencyFormatter getCurrencyFormatter() {
        return currencyFormatter;
    }

    public BrazilianLocale getBrazilianLocale() {
        return mBrazilianLocale;
    }
}
