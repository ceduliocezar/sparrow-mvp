package com.cedulio.sparrow.bill.list.formatter;


import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthFormatter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM",
            DefaultLocale.getInstance().getLocale());

    public static String format(Date date) {
        return dateFormatter.format(date).toUpperCase(DefaultLocale.getInstance().getLocale());
    }
}
