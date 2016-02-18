package com.cedulio.sparrow.domain.formatter.bill;


import com.cedulio.sparrow.domain.R;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CloseDateFormatter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM",
            DefaultLocale.getLocale());

    private static SimpleDateFormat formatter = new SimpleDateFormat("MMM",
            DefaultLocale.getLocale());


    public String formatClose(Date closeDate, Context context) {

        String format = context.getString(R.string.close_date_format);

        return String
                .format(format, dateFormatter.format(closeDate.getTime()).toString().toUpperCase());
    }

    public String formatClosing(Date closeDate, Context context) {

        String format = context.getString(R.string.close_date_format_long);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(closeDate);

        return String.format(format, calendar.get(Calendar.DAY_OF_MONTH), formatter.format(
                closeDate.getTime()).toString().toUpperCase());
    }

    public String format(Date closeDate, Context context) {
        return dateFormatter.format(closeDate.getTime()).toString().toUpperCase();
    }


}
