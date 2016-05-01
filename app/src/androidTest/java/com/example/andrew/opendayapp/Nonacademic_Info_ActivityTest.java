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
 * This test class is used to test the non academic info activity
 * this class requires information to be passed to it in order to display the information
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
@RunWith(AndroidJUnit4.class)
public class Nonacademic_Info_ActivityTest {

    @Rule
    public ActivityTestRule<Nonacademic_Info_Activity> activityTestRule
            = new ActivityTestRule<Nonacademic_Info_Activity>(Nonacademic_Info_Activity.class, true, false);


    /**
     * This method starts the activity and defines
     * what information is passed to the activity to be displayed
     * @throws Exception
     */
    @Before
    public void setUp()throws Exception{
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra("id", "1");
        intent.putExtra("name", "Accommodation Office");
        intent.putExtra("description", "This is a test description for the accommodation office");

        activityTestRule.launchActivity(intent);
    }


    /**
     * This method checks that the passed information matches the displayed information
     */
    @Test
    public void testContentIsDisplayedFromIntent() {

        onView(withId(R.id.name)).check(matches(withText("Accommodation Office")));
        onView(withId(R.id.description)).check(matches(withText("This is a test description for the accommodation office")));
    }

    /**
     * This method checks that the events button opens the events activity when clicked
     */
    @Test
    public void testEventsButton() {

        onView(withId(R.id.buttonevents)).perform(click());
        onView(withText("Todays Events")).check(matches(withText("Todays Events")));

    }


}