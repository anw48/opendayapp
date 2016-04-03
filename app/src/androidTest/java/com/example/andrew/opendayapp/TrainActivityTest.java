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
 *
 */

@RunWith(AndroidJUnit4.class)
public class TrainActivityTest {


    @Rule
    public ActivityTestRule<TrainActivity> activityTestRule
            = new ActivityTestRule<>(TrainActivity.class);



    @Before
    public void setUp() throws Exception{
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }


    @Test
    public void testContentisDisplayed(){

        onView(withId(R.id.trainheading)).check(matches(withText(R.string.trainheading)));
        onView(withId(R.id.trainbody)).check(matches(withText(R.string.trainbody)));
    }

}