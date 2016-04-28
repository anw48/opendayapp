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
 * This test class tests the accommodation info activity to see if the correct data is displayed
 *
 * Created by Andrew on 4/6/2016.
 *
 */

@RunWith(AndroidJUnit4.class)
public class Accommodation_Info_ActivityTest {

    @Rule
    public ActivityTestRule<Accommodation_Info_Activity> activityTestRule
            = new ActivityTestRule<Accommodation_Info_Activity>(Accommodation_Info_Activity.class, true, false);

    /**
     * sets the data to be used in the test
     * @throws Exception
     */

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra("id", "1");
        intent.putExtra("name", "Seafront Residences");
        intent.putExtra("description", "Description of the seafront residences");
        intent.putExtra("image", "welcome");

        activityTestRule.launchActivity(intent);
    }


    /**
     * test to see if the correct content is diplayed
     */
    @Test
    public void testContentIsDisplayedFromIntent() {

        onView(withId(R.id.name)).check(matches(withText("Seafront Residences")));
        onView(withId(R.id.description)).check(matches(withText("Description of the seafront residences")));
      }



}