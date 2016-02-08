package com.cedulio.sparrow.bill.formatter;


import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CloseDateFormatter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM",
            DefaultLocale.getInstance().getLocale());

    private static SimpleDateFormat formatter = new SimpleDateFormat("MMM",
            DefaultLocale.getInstance().getLocale());

    public static String formatClose(Date closeDate, Context context) {

        String format = context.getString(R.string.close_date_format);

        return String
                .format(format, dateFormatter.format(closeDate.getTime()).toString().toUpperCase());
    }

    public static String formatClosing(Date closeDate, Context context) {

        String format = context.getString(R.string.close_date_format_long);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(closeDate);

        return String.format(format, calendar.get(Calendar.DAY_OF_MONTH), formatter.format(
                closeDate.getTime()).toString().toUpperCase());
    }

    public static String format(Date closeDate, Context context) {
        return dateFormatter.format(closeDate.getTime()).toString().toUpperCase();
    }


}
