package com.cedulio.sparrow.domain.utilities;

import java.util.Locale;

public class DefaultLocale {

    private static Locale locale = new Locale("pt", "BR");

    public static Locale getLocale() {
        return locale;
    }
}
