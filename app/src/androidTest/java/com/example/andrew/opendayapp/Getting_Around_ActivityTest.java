package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by Andrew on 4/3/2016.
 *
 *
 */
public class Getting_Around_ActivityTest {

    @Rule
    public ActivityTestRule<Getting_Around_Activity> activityTestRule
            = new ActivityTestRule<>(Getting_Around_Activity.class);




    @Before
    public void setUp() throws Exception{
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }




    @Test
    public void testMapButton() {

        onView(withId(R.id.mapImageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());


        onView(withText(R.string.title_activity_maps)).check(matches(withText(R.string.title_activity_maps)));

    }


    @Test
    public void testCarButton() {

        onView(withId(R.id.carImageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());

        onView(withId(R.id.carheading)).check(matches(withText(R.string.carheading)));
    }


    @Test
    public void testTrainButton() {

        onView(withId(R.id.trainImageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());

        onView(withId(R.id.trainheading)).check(matches(withText(R.string.trainheading)));
    }



    @Test
    public void testBusButton() {

        onView(withId(R.id.busImageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());

        onView(withId(R.id.busheading)).check(matches(withText(R.string.busheading)));
    }




}