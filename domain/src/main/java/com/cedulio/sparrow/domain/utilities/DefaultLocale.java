package com.cedulio.sparrow.domain.utilities;

import java.util.Locale;

public class DefaultLocale {

    private static DefaultLocale INSTANCE = new DefaultLocale();

    private Locale locale = new Locale("pt", "BR");

    private DefaultLocale() {

    }

    public static DefaultLocale getInstance() {
        return INSTANCE;
    }

    public Locale getLocale() {
        return locale;
    }
}
