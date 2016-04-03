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
public class WelcomeActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> activityTestRule
            = new ActivityTestRule<>(WelcomeActivity.class);



    @Before
    public void setUp() throws Exception{
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }


    @Test
    public void testWelcomeTitleIsDisplayed(){

        onView(withId(R.id.welcome)).check(matches(withText(R.string.welcome)));
    }


    @Test
    public void testFirstMessageIsDisplayed(){

        onView(withId(R.id.message)).check(matches(withText(R.string.message)));
    }

    @Test
    public void testSecondMessageIsDisplayed(){

        onView(withId(R.id.message2)).check(matches(withText(R.string.message2)));
    }


    @Test
    public void testThirdMessageIsDisplayed(){

        onView(withId(R.id.message3)).check(matches(withText(R.string.message3)));
    }







}