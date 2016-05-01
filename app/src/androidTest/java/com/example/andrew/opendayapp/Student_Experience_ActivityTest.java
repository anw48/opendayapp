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
 *
 * This test class is used to test the student experience activity
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
@RunWith(AndroidJUnit4.class)
public class Student_Experience_ActivityTest {

    @Rule
    public ActivityTestRule<Student_Experience_Activity> activityTestRule
            = new ActivityTestRule<Student_Experience_Activity>(Student_Experience_Activity.class);


    /**
     * This method starts the activity
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }


    /**
     * This method checks that the refreshments button
     * opens the refreshments activity
     */
    @Test
    public void testRefreshementsButton() {

        onView(withId(R.id.refreshementsimageButton)).perform(click());
        onView(withText(R.string.refreshmentstitle)).check(matches(withText(R.string.refreshmentstitle)));
    }



    /**
     * This method checks that the accommodation button
     * opens the accommodation activity
     */
    @Test
    public void testAccommodationButton() {

        onView(withId(R.id.accommodationbutton)).perform(click());
        onView(withText(R.string.accommodationlist)).check(matches(withText(R.string.accommodationlist)));
    }
}