package com.cedulio.sparrow.domain.formatter.bill;


import com.cedulio.sparrow.domain.R;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SummaryPeriodFormatter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM",
            DefaultLocale.getLocale());

    public String format(Date openDate, Date closeDate, Context context) {

        String text = context.getString(R.string.line_items_period_header);

        return String.format(text, dateFormatter.format(openDate.getTime()),
                dateFormatter.format(closeDate).toString()).toUpperCase();
    }

}
