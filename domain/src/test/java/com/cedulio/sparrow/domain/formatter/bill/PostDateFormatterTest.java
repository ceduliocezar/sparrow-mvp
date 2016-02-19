package com.cedulio.sparrow.domain.formatter.bill;

import com.cedulio.sparrow.domain.language.DefaultLocale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

@RunWith(MockitoJUnitRunner.class)
public class PostDateFormatterTest {

    @Test
    public void testDay() {

        Calendar calendar = Calendar.getInstance(DefaultLocale.getLocale());
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        PostDateFormatter formatter = new PostDateFormatter();

        String formattedDescription = formatter.formatDay(calendar.getTime());

        Assert.assertTrue("Expected output=1, formatted string=" + formattedDescription,
                formattedDescription.equals("1"));
    }

    @Test
    public void testMonth() {

        Calendar calendar = Calendar.getInstance(DefaultLocale.getLocale());
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        PostDateFormatter formatter = new PostDateFormatter();

        String formattedDescription = formatter.formatMonth(calendar.getTime());

        Assert.assertTrue("Expected output=MAI, formatted string=" + formattedDescription,
                formattedDescription.equals("MAI"));
    }
}
