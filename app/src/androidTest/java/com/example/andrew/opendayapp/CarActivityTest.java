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
import static org.junit.Assert.*;

/**
 * Created by Andrew on 4/3/2016.


 */

@RunWith(AndroidJUnit4.class)
public class CarActivityTest {

    @Rule
    public ActivityTestRule<CarActivity> activityTestRule
            = new ActivityTestRule<>(CarActivity.class);



    @Before
    public void setUp() throws Exception{
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }


    @Test
    public void testCarHeadingDisplayed(){

        onView(withId(R.id.carheading)).check(matches(withText(R.string.carheading)));
        onView(withId(R.id.addressheading)).check(matches(withText(R.string.addressheading)));
    }



    @Test
    public void testCarContentDisplayed(){

        onView(withId(R.id.carbody)).check(matches(withText(R.string.carbody)));
        onView(withId(R.id.campusaddress)).check(matches(withText(R.string.campusaddress)));
    }

}