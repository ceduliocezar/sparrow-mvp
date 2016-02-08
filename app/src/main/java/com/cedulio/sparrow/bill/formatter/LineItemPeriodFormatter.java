package com.cedulio.sparrow.bill.formatter;


import com.cedulio.sparrow.R;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LineItemPeriodFormatter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM",
            DefaultLocale.getInstance().getLocale());

    public static String format(Date openDate, Date closeDate, Context context) {
        String text = context.getResources().getString(R.string.line_items_period_header);

        return String.format(text, dateFormatter.format(openDate.getTime()),
                dateFormatter.format(closeDate).toString()).toUpperCase();
    }

}
