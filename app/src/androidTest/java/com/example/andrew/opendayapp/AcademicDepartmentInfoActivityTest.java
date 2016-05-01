package com.example.andrew.opendayapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * This class is used to test the information displayed in the department information class
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */


@RunWith(AndroidJUnit4.class)
public class AcademicDepartmentInfoActivityTest {

    @Rule
    public ActivityTestRule<AcademicDepartmentInfoActivity> activityTestRule
            = new ActivityTestRule<>(AcademicDepartmentInfoActivity.class, true, false);

    /**
     * Set up the data to be used in the test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra("id", "1");
        intent.putExtra("name", "Art");
        intent.putExtra("description", "test description");
        intent.putExtra("image", "welcome");

        activityTestRule.launchActivity(intent);
    }


    /**
     * Tests the correct content is displayed
     */
    @Test
    public void testContentIsDisplayedFromIntent() {

        onView(withId(R.id.name)).check(matches(withText("Art")));
        onView(withId(R.id.description)).check(matches(withText("test description")));
    }

    /**
     * tests to check if the events button works correctly
     */
    @Test
    public void testEventsButton() {

        onView(withId(R.id.buttonevents)).perform(click());
        onView(withText("Todays Events")).check(matches(withText("Todays Events")));

    }


}
