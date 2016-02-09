package com.cedulio.sparrow.domain.formatter;

import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostDateFormatter {

    private static SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM",
            DefaultLocale.getLocale());

    private static SimpleDateFormat dayFormatter = new SimpleDateFormat("d",
            DefaultLocale.getLocale());

    public static String formatMonth(Date postDate) {
        return monthFormatter.format(postDate.getTime()).toString()
                .toUpperCase(DefaultLocale.getLocale());
    }

    public static String formatDay(Date postDate) {
        return dayFormatter.format(postDate.getTime()).toString()
                .toUpperCase(DefaultLocale.getLocale());
    }
}
