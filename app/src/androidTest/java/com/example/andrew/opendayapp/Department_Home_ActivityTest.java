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
 * This test class is used to test the department home activity
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
@RunWith(AndroidJUnit4.class)
public class Department_Home_ActivityTest {



    @Rule
    public ActivityTestRule<Department_Home_Activity> activityTestRule
            = new ActivityTestRule<>(Department_Home_Activity.class);


    /**
     * This method starts the activity
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }

    /**
     * This method checks that the academic button
     * opens the academic departments activity
     */
    @Test
    public void testAcademicButton(){
        onView(withId(R.id.academicimagebutton)).perform(click());
        onView(withText(R.string.academicdepartments)).check(matches(withText(R.string.academicdepartments)));
    }


    /**
     * This method checks that the non academic button
     * opens the non academic departments activity
     */
    @Test
    public void testNonAcademicButton(){
        onView(withId(R.id.nonacademicimagebutton)).perform(click());
        onView(withText(R.string.departments)).check(matches(withText(R.string.departments)));
    }





}