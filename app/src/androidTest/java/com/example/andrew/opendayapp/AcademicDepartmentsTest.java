package com.example.andrew.opendayapp;

import android.content.Intent;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by Andrew on 4/6/2016.
 */
@RunWith(AndroidJUnit4.class)
public class AcademicDepartmentsTest {

    @Rule
    public ActivityTestRule<AcademicDepartments> activityTestRule
            = new ActivityTestRule<AcademicDepartments>(AcademicDepartments.class);


    @Before
    public void setUp() throws Exception {
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);

    }

    /**
     * @Test public void testListViewClickSingleItem() {
     * <p/>
     * onView(allOf(withId(R.id.name), withText("Art"))).perform(click());
     * onView(withText("Department Information")).check(matches(withText("Department Information")));
     * <p/>
     * }
     * @Test public void testListIsDisplayed() {
     * <p/>
     * onData(anything())
     * .inAdapterView(allOf(withId(android.R.id.list), isDisplayed()))
     * .atPosition(0)
     * .onChildView(withId(R.id.name))
     * .check(matches(isDisplayed()));
     * }
     */

    @Test
    public void testListIsDisplayed() {


        final ListView listView = (ListView) AcademicDepartments.findViewById(android.R.id.list);

        int test = listView.getCount();

        onData(anything()).inAdapterView(withId(android.R.id.list))
                .atPosition(0)
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


    }

}