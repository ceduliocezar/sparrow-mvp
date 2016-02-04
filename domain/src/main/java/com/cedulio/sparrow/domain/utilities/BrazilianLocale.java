package com.cedulio.sparrow.domain.utilities;

import java.util.Locale;

public class BrazilianLocale {

    private static BrazilianLocale INSTANCE = new BrazilianLocale();

    private Locale locale = new Locale("pt", "BR");

    private BrazilianLocale() {

    }

    public static BrazilianLocale getInstance() {
        return INSTANCE;
    }

    public Locale getLocale() {
        return locale;
    }
}
