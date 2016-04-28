package com.example.andrew.opendayapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;


/**
 * This class is used to display information about getting to aberystwyth by train
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class TrainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        setTitle(getResources().getString(R.string.trainheading));
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }
}
