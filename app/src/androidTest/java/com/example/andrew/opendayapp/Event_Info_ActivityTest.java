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
 * Created by Andrew on 4/6/2016.
 *
 */
@RunWith(AndroidJUnit4.class)
public class Event_Info_ActivityTest {

    @Rule
    public ActivityTestRule<Event_Info_Activity> activityTestRule
            = new ActivityTestRule<Event_Info_Activity>(Event_Info_Activity.class, true, false);



    @Before
    public void setUp()throws Exception {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra("eventid", "1");
        intent.putExtra("deptid", "1");
        intent.putExtra("name", "Student Projects");
        intent.putExtra("description", "demonstration of student projects");
        intent.putExtra("start", "10:00:00");
        intent.putExtra("end", "14:00:00");
        intent.putExtra("location", "Room B57, Llandinam Building");

        activityTestRule.launchActivity(intent);

    }

    @Test
    public void testContentIsDisplayedFromIntent() {


        onView(withId(R.id.eventname)).check(matches(withText("Student Projects")));
        onView(withId(R.id.description)).check(matches(withText("demonstration of student projects")));
        onView(withId(R.id.starttime)).check(matches(withText("10:00:00")));
        onView(withId(R.id.endtime)).check(matches(withText("14:00:00")));
        onView(withId(R.id.location)).check(matches(withText("Room B57, Llandinam Building")));
    }



}