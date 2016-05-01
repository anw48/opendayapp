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
 * This test class is used to test the user interface
 * of the tours activity.
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
@RunWith(AndroidJUnit4.class)
public class ToursActivityTest {


    @Rule
    public ActivityTestRule<ToursActivity> activityTestRule
            = new ActivityTestRule<>(ToursActivity.class);

    /**
     * Set up class to start the activity for the test.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{

        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }



    /**
     * This test checks that the heading are displayed correctly
     */
    @Test
    public void testHeadings(){

        onView(withId(R.id.penglaistoursheading)).check(matches(withText(R.string.penglaistoursheading)));
        onView(withId(R.id.accomoodationtoursheading)).check(matches(withText(R.string.accomodationtoursheading)));
        onView(withId(R.id.libraryheading)).check(matches(withText(R.string.librarytoursheading)));
        onView(withId(R.id.suheading)).check(matches(withText(R.string.studentsuniontoursheading)));

    }

    /**
     * This method tests that the information displayed is correct
     */
    @Test
    public void testContent(){

        onView(withId(R.id.pengliastours)).check(matches(withText(R.string.penglaistours)));
        onView(withId(R.id.accomoodationtours)).check(matches(withText(R.string.accomodationtours)));
        onView(withId(R.id.librarytours)).check(matches(withText(R.string.librarytours)));
        onView(withId(R.id.sutours)).check(matches(withText(R.string.studentsuniontours)));
    }
}