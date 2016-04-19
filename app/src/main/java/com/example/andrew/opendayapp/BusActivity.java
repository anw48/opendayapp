package com.example.andrew.opendayapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;



/**
 * This class is used to display the information about arriving at Aberystwyth by bus
 *
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class BusActivity extends Activity {

    /**
     * This method starts the activity when it is called
     * The title of the activity is also set here
     * The back button is also set here
     *
     *
     *  @param savedInstanceState auto generated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * This method defines what happens when the user presses the back button
     * In this case return to the previous screen
     * @param item android menu item
     * @return returns the action of the button click
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }
    }

