package com.cedulio.sparrow.bill.formatter;

import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostDateFormatter {

    private static SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM",
            DefaultLocale.getInstance().getLocale());
    private static SimpleDateFormat dayFormatter = new SimpleDateFormat("d",
            DefaultLocale.getInstance().getLocale());

    public static String formatMonth(Date postDate) {
        return monthFormatter.format(postDate.getTime()).toString()
                .toUpperCase(DefaultLocale.getInstance().getLocale());
    }

    public static String formatDay(Date postDate) {
        return dayFormatter.format(postDate.getTime()).toString()
                .toUpperCase(DefaultLocale.getInstance().getLocale());
    }
}
