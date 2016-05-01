package com.example.andrew.opendayapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 *
 * This test class is uesed to check of all the information is displayed correctly in the bus activity.
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */


public class BusActivityTest {


    @Rule
    public ActivityTestRule<BusActivity> activityTestRule
            = new ActivityTestRule<BusActivity>(BusActivity.class);


    /**
     * Starts the activity so it can be tested
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }


    /**
     * This test checks if the headings on the page are displayed correctly
     */
    @Test
    public void testHeadings(){

        onView(withId(R.id.busheading)).check(matches(withText(R.string.busheading)));
        onView(withId(R.id.pengliasheading)).check(matches(withText(R.string.penglaisheading)));
        onView(withId(R.id.llanbadarnheading)).check(matches(withText(R.string.llanbadarnheading)));
        onView(withId(R.id.gogerddanheading)).check(matches(withText(R.string.gogerddanheading)));
    }


    /**
     * This test checks if the content is displayed correctly
     */
    @Test
    public void testContentBusActivity(){

        onView(withId(R.id.busbody)).check(matches(withText(R.string.busbody)));
        onView(withId(R.id.penglaisbody)).check(matches(withText(R.string.penglaisbody)));
        onView(withId(R.id.llanbadarnbody)).check(matches(withText(R.string.llanbadarnbody)));
        onView(withId(R.id.gogerddanbody)).check(matches(withText(R.string.gogerddanbody)));
    }
}