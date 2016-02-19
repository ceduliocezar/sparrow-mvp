package com.cedulio.sparrow.domain.formatter.bill;


import com.cedulio.sparrow.domain.language.DefaultLocale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

@RunWith(MockitoJUnitRunner.class)
public class MonthFormatterTest {

    @Test
    public void testBRFormat() {

        Calendar calendar = Calendar.getInstance(DefaultLocale.getLocale());
        calendar.set(Calendar.MONTH,Calendar.MAY);

        MonthFormatter formatter = new MonthFormatter();

        String formattedDescription = formatter.format(calendar.getTime());

        Assert.assertTrue("Expected output=MAI, formatted string=" + formattedDescription,
                formattedDescription.equals("MAI"));
    }
}
