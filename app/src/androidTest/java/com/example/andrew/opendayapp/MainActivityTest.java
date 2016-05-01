package com.example.andrew.opendayapp;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 *
 * This test class is used to test the main activity
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    //Rule to launch the activity to run the tests.
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    /**
     * This method starts the main activity
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Intent myIntent = new Intent();
        activityTestRule.launchActivity(myIntent);
    }




    /**
     * This method checks that the welcome button works correctly
     * it checks that the welcome activity has been started
     */
    @Test
    public void testWelcomeImageButton() {

        onView(withId(R.id.welcomeimageButton))
                .perform(click());

        onView(withId(R.id.welcome)).check(matches(withText(R.string.welcome)));
    }



    /**
     * This method checks that the department button works correctly
     * it checks that the department activity has been started
     */
    @Test
    public void testDepartmentImageButton() {

        onView(withId(R.id.talksimageButton))
                .perform(click());

        onView(withId(R.id.academicimagebutton)).check(matches(withId(R.id.academicimagebutton)));
    }



    /**
     * This method checks that the student experience button works correctly
     * it checks that the student experience activity has been started
     */
    @Test
    public void testStudentExperienceImageButton() {

        onView(withId(R.id.refreshementsimageButton))
                .perform(click());

        onView(withId(R.id.studentexperienceheader)).check(matches(withText(R.string.studentexperiencedescription)));
    }



    /**
     * This method checks that the tours button works correctly
     * it checks that the tours activity has been started
     */
    @Test
    public void testToursImageButton() {

        onView(withId(R.id.toursimageButton))
                .perform(click());

        onView(withId(R.id.penglaistoursheading)).check(matches(withText(R.string.penglaistoursheading)));
    }



    /**
     * This method checks that the getting around button works correctly
     * it checks that the getting around activity has been started
     */
    @Test
    public void testGettingAroundImageButton() {

        onView(withId(R.id.gettingaroundimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(scrollTo(), click());

        onView(withId(R.id.mapImageButton)).check(matches(withId(R.id.mapImageButton)));
    }



    /**
     * This method checks that the planner button works correctly
     * it checks that the planner activity has been started
     */
    @Test
    public void testPlannerImageButton() {

        onView(withId(R.id.plannerimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform( scrollTo(), click());

        onView(withId(R.id.plannertextbox)).check(matches(withId(R.id.plannertextbox)));
    }



    /**
     * This method checks that the contact button works correctly
     * it checks that the contact activity has been started
     */
    @Test
    public void testContactImageButton() {

        onView(withId(R.id.contactimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(scrollTo(), click());

        onView(withId(R.id.email)).check(matches(withText(R.string.contactmessage)));
    }




    /**
     * This method checks that the disclaimers button works correctly
     * it checks that the disclaimers activity has been started
     */
    @Test
    public void testDisclaimersImageButton() {

        onView(withId(R.id.disclaimersimageButton))            // withId(R.id.my_view) is a ViewMatcher
                .perform(scrollTo(), click());

        onView(withId(R.id.emergencyheading)).check(matches(withText(R.string.emergencyheading)));
    }


    /**
     * This method is used to test the language selection
     * This method opens the menu and selects the welsh option
     * the method then checks if the displayed content is in Welsh
     */
    @Test
    public void testWelshMenu(){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.welshoption))
                .perform(click());

        onView(withText(R.string.app_name)).check(matches(withText("Diwrnod Agored Prifysgol Aberystwyth")));

    }

    /**
     * This method is used to test the language selection
     * This method opens the menu and selects the english option
     * the method then checks if the displayed content is in english
     */
    @Test
    public void testEnglishMenu(){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.englishoption))
                .perform(click());


        onView(withText(R.string.app_name)).check(matches(withText("Aberystwyth University Open Day")));

    }





}