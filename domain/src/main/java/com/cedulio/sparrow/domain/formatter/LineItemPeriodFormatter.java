package com.cedulio.sparrow.domain.formatter;


import com.cedulio.sparrow.domain.i18n.FormatMessages;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LineItemPeriodFormatter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM",
            DefaultLocale.getLocale());

    public static String format(Date openDate, Date closeDate) {

        String text = FormatMessages.getString("LineItemsPeriodHeader");

        return String.format(text, dateFormatter.format(openDate.getTime()),
                dateFormatter.format(closeDate).toString()).toUpperCase();
    }

}
