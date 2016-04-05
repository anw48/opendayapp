package com.example.andrew.opendayapp;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by Andrew on 4/1/2016.
 *
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    //Rule to launch the activity to run the tests.
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }



    @Test
    public void testWelcomeImageButton() {

        onView(withId(R.id.welcomeimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());

        onView(withId(R.id.welcome)).check(matches(withText(R.string.welcome)));
    }


    @Test
    public void testDepartmentImageButton() {

        onView(withId(R.id.talksimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());

        onView(withId(R.id.academicimagebutton)).check(matches(withId(R.id.academicimagebutton)));
    }


    @Test
    public void testStudentExperienceImageButton() {

        onView(withId(R.id.refreshementsimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());

        onView(withId(R.id.studentexperienceheader)).check(matches(withText(R.string.studentexperiencedescription)));
    }


    //fails because of scroll view look for solution


    @Test
    public void testToursImageButton() {

        onView(withId(R.id.toursimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click());

        onView(withId(R.id.penglaistoursheading)).check(matches(withText(R.string.penglaistoursheading)));
    }

    @Test
    public void testGettingAroundImageButton() {

        onView(withId(R.id.gettingaroundimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(scrollTo(), click());

        onView(withId(R.id.mapImageButton)).check(matches(withId(R.id.mapImageButton)));
    }


    @Test
    public void testPlannerImageButton() {

        onView(withId(R.id.plannerimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform( scrollTo(), click());

        onView(withId(R.id.plannertextbox)).check(matches(withId(R.id.plannertextbox)));
    }


    @Test
    public void testContactImageButton() {

        onView(withId(R.id.contactimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(scrollTo(), click());

        onView(withId(R.id.email)).check(matches(withText(R.string.contactmessage)));
    }



    @Test
    public void testDisclaimersImageButton() {

        onView(withId(R.id.disclaimersimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(scrollTo(), click());

        onView(withId(R.id.emergencyheading)).check(matches(withText(R.string.emergencyheading)));
    }



    @Test
    public void testWelshMenu(){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.welshoption))
                .perform(click());

        onView(withText(R.string.app_name)).check(matches(withText("Diwrnod Agored Prifysgol Aberystwyth")));

    }


    @Test
    public void testEnglishMenu(){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.englishoption))
                .perform(click());


        onView(withText(R.string.app_name)).check(matches(withText("Aberystwyth University Open Day")));

    }





}