package com.cedulio.sparrow.bill.listing;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.cedulio.sparrow.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class BillListActivityTest {

    @Rule
    public ActivityTestRule<BillListActivity> mActivityRule = new ActivityTestRule<>(
            BillListActivity.class);

    @Test
    public void testSaveState() {
        rotateScreen();
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        Activity activity = mActivityRule.getActivity();
        activity.setRequestedOrientation((orientation == Configuration.ORIENTATION_PORTRAIT) ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Test
    public void testSlideTabs() {
        onView(withId(R.id.act_bill_list__vp)).perform(swipeRight());
        onView(withId(R.id.act_bill_list__vp)).perform(swipeRight());
        onView(withId(R.id.act_bill_list__vp)).perform(swipeLeft());
        onView(withId(R.id.act_bill_list__vp)).perform(swipeLeft());
        onView(withId(R.id.act_bill_list__vp)).perform(swipeLeft());
        onView(withId(R.id.act_bill_list__vp)).perform(swipeLeft());
    }
}
