package com.example.andrew.opendayapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * This class defines the welcome activity
 * This activity contains the welcome message of the university
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class WelcomeActivity extends Activity {

    /**
     * This method starts the activity when it is called
     * The title of the activity is also set here
     * The back button is also set here
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTitle(getResources().getString(R.string.welcomepage));
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * This method defines what action to take when the user presses the back button
     * In this case navigate up to parent activity which is defined in the manifest file
     *
     *
     * @param item
     * @return
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
