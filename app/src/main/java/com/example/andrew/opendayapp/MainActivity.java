package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

/**
 * The main activity class is the main page of this Android application.
 * Users are directed to this screen first where they can then navigate to
 * other activities for the functionality of this application
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 *
 */
public class MainActivity extends Activity implements View.OnClickListener {

    /** Image button variables which are used to identify individual images button to navigate to other activities*/
    ImageButton welcomebutton, departmentsbutton, disclaimersbutton, plannerbutton, contactbutton, gettingaroundbutton, toursbutton, studentbutton;

    /** Locale variable used to set the current set locale to allow the user to change the applications language*/
    Locale myLocale;


    /**
     * This is the an Android generate method which is used to start the activity when it is called.
     * In this method the layout xml file is defined as well as the title of the activity.
     *
     * The Activity title is set here because of the language change feature
     *
     * This method also sets the image button variables defined above to a specific
     * image button defined in the layout file and sets a click listener to allow the
     * user to click the button
     *
     * @param savedInstanceState  This is a variable required to start the activity -- Auto generated
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //defines the layout of the activity
        setContentView(R.layout.activity_main);

        //sets the title on the action bar of the activity
        setTitle(getResources().getString(R.string.app_name));


        // Initialises the image button variable to map to the correct image button in the layout xml file
        welcomebutton = (ImageButton) findViewById(R.id.welcomeimageButton);
        welcomebutton.setOnClickListener(this);

        departmentsbutton = (ImageButton) findViewById(R.id.talksimageButton);
        departmentsbutton.setOnClickListener(this);

        disclaimersbutton = (ImageButton) findViewById(R.id.disclaimersimageButton);
        disclaimersbutton.setOnClickListener(this);

        plannerbutton = (ImageButton) findViewById(R.id.plannerimageButton);
        plannerbutton.setOnClickListener(this);

        contactbutton = (ImageButton) findViewById(R.id.contactimageButton);
        contactbutton.setOnClickListener(this);

        gettingaroundbutton = (ImageButton) findViewById(R.id.gettingaroundimageButton);
        gettingaroundbutton.setOnClickListener(this);

        toursbutton = (ImageButton) findViewById(R.id.toursimageButton);
        toursbutton.setOnClickListener(this);

        studentbutton = (ImageButton) findViewById(R.id.refreshementsimageButton);
        studentbutton.setOnClickListener(this);


    }

    /**
     *
     * This method sets defines what action to preform when the user clicks on a specific view of the activity
     * In this case all the images buttons are defined to navigate away from MainActivity to another Activity
     *
     *
     * @param v   Variable to define what part of the view is being used by the user
     */
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.welcomeimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        WelcomeActivity.class);
                startActivity(myIntent);

                break;
            }
            case R.id.talksimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        Department_Home_Activity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.disclaimersimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        DiscliamersActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.plannerimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        PlannerActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.contactimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        Contact_Activity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.gettingaroundimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        Getting_Around_Activity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.toursimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        ToursActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.refreshementsimageButton: {
                Intent myIntent = new Intent(MainActivity.this,
                        Student_Experience_Activity.class);
                startActivity(myIntent);
                break;
            }


        }

    }


    /**
     *
     * This method creates a options menu for the language selection
     *
     * @param menu  menu parameter to create a menu
     * @return returns true to create a options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     *
     * This method sets the items in the options menu and wht to do when that option is selected
     *
     * @param item
     * @return returns the item selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //If the user selects english changes the language to english
        if (item.getItemId()==R.id.english) {
            setLocale("en");
            return true;
        }

        // If the user selects welsh changes the language to welsh
        if (item.getItemId()==R.id.welsh) {
            setLocale("cy");
            return true;
        }
        return super.onOptionsItemSelected(item);
        }


    /**
     * This method sets the current locale which is used to change the language of the application
     * After setting the locale the activity refreshes
     *
     * @param lang
     */
    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
    }
}




