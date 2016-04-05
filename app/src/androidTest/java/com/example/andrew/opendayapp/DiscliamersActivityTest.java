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
 * Created by Andrew on 4/5/2016.
 */

@RunWith(AndroidJUnit4.class)
public class DiscliamersActivityTest {


    @Rule
    public ActivityTestRule<DiscliamersActivity> activityTestRule
            = new ActivityTestRule<>(DiscliamersActivity.class);


    @Before
    public void setUp() throws Exception {
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);

    }

    @Test
    public void testHeadingsAreDisplayed(){

        onView(withId(R.id.emergencyheading)).check(matches(withText(R.string.emergencyheading)));
        onView(withId(R.id.safetyheading)).check(matches(withText(R.string.safetyheading)));
        onView(withId(R.id.buildingheading)).check(matches(withText(R.string.workheading)));
    }


    @Test
    public void testContentIsDisplayed(){

        onView(withId(R.id.emergencymessage)).check(matches(withText(R.string.emergencymessage)));
        onView(withId(R.id.safetymessage)).check(matches(withText(R.string.safetymessage)));
        onView(withId(R.id.buildingmessage)).check(matches(withText(R.string.buildingmessage)));
    }


}