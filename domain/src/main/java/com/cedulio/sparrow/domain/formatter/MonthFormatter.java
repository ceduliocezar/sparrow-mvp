package com.cedulio.sparrow.domain.formatter;


import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthFormatter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM",
            DefaultLocale.getLocale());

    public static String format(Date date) {
        return dateFormatter.format(date).toUpperCase(DefaultLocale.getLocale());
    }
}
