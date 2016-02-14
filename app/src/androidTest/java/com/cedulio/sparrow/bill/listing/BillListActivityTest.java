package com.cedulio.sparrow.bill.listing;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class BillListActivityTest {

    @Rule
    public ActivityTestRule<BillListActivity> mActivityRule = new ActivityTestRule<>(
            BillListActivity.class);

    @Test
    public void testeSaveState() {
        rotateScreen();
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        Activity activity = mActivityRule.getActivity();
        activity.setRequestedOrientation((orientation == Configuration.ORIENTATION_PORTRAIT) ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
