package com.example.andrew.opendayapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 *
 * This test class is used to test the refreshment info activity
 * this class requires information to be passed to it in order to
 * display the information
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
@RunWith(AndroidJUnit4.class)
public class Refreshment_Info_ActivityTest {


    @Rule
    public ActivityTestRule<Refreshment_Info_Activity> activityTestRule
            = new ActivityTestRule<Refreshment_Info_Activity>(Refreshment_Info_Activity.class, true, false);


    /**
     * This method starts the activity and defines
     * what information is passed to the activity to be displayed
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra("id", "1");
        intent.putExtra("name", "TaMed Da");
        intent.putExtra("description", "Description of TaMed Da");
        intent.putExtra("image", "welcome");
        intent.putExtra("open", "09:00:00");
        intent.putExtra("close", "17:00:00");

        activityTestRule.launchActivity(intent);
    }


    /**
     * This method checks that the passed information matches the displayed information
     */
    @Test
    public void testContentIsDisplayedFromIntent() {

        onView(withId(R.id.name)).check(matches(withText("TaMed Da")));
        onView(withId(R.id.description)).check(matches(withText("Description of TaMed Da")));
        onView(withId(R.id.opentime)).check(matches(withText("09:00:00")));
        onView(withId(R.id.closetime)).check(matches(withText("17:00:00")));
    }
}