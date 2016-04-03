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
        import static org.junit.Assert.*;

/**
 * Created by Andrew on 4/3/2016.
 *
 */
public class ToursActivityTest {


    @Rule
    public ActivityTestRule<ToursActivity> activityTestRule
            = new ActivityTestRule<>(ToursActivity.class);

    @Before
    public void setUp() throws Exception{

        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }



    @Test
    public void testHeadings(){

        onView(withId(R.id.penglaistoursheading)).check(matches(withText(R.string.penglaistoursheading)));
        onView(withId(R.id.accomoodationtoursheading)).check(matches(withText(R.string.accomodationtoursheading)));
        onView(withId(R.id.libraryheading)).check(matches(withText(R.string.librarytoursheading)));
        onView(withId(R.id.suheading)).check(matches(withText(R.string.studentsuniontoursheading)));

    }

    @Test
    public void testContent(){

        onView(withId(R.id.pengliastours)).check(matches(withText(R.string.penglaistours)));
        onView(withId(R.id.accomoodationtours)).check(matches(withText(R.string.accomodationtours)));
        onView(withId(R.id.librarytours)).check(matches(withText(R.string.librarytours)));
        onView(withId(R.id.sutours)).check(matches(withText(R.string.studentsuniontours)));



    }




}