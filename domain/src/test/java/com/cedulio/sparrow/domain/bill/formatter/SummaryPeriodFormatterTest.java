package com.cedulio.sparrow.domain.bill.formatter;

import com.cedulio.sparrow.domain.R;
import com.cedulio.sparrow.domain.formatter.SummaryPeriodFormatter;
import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;

import java.util.Calendar;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SummaryPeriodFormatterTest {

    @Mock
    Context context;

    @Test
    public void testBRFormat() {

        when(context.getString(R.string.line_items_period_header)).thenReturn("%s ATÉ %s");

        Calendar openDate = Calendar.getInstance(DefaultLocale.getLocale());
        openDate.set(Calendar.MONTH, Calendar.MAY);
        openDate.set(Calendar.DAY_OF_MONTH, 1);

        Calendar closeDate = Calendar.getInstance(DefaultLocale.getLocale());
        closeDate.set(Calendar.MONTH, Calendar.JUNE);
        closeDate.set(Calendar.DAY_OF_MONTH, 1);

        SummaryPeriodFormatter formatter = new SummaryPeriodFormatter();

        String formattedDescription = formatter
                .format(openDate.getTime(), closeDate.getTime(), context);

        Assert.assertTrue("Expected format=1 MAI ATÉ 1 JUN, formatted string=" + formattedDescription,
                formattedDescription.equals("1 MAI ATÉ 1 JUN"));
    }

}
