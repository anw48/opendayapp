package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * This class is used to display the event information.
 * This is where content is recieved from the previous
 * screen and the event information diplayed.
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */

public class Event_Info_Activity extends Activity {

    /**
     * Auto generated method to create the activity.
     * also defines the title, layout file and the back button
     *
     * The information that is passed to this activity via intents
     * is also retrieved in this method.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        setTitle(getResources().getString(R.string.eventinfo));

        getActionBar().setDisplayHomeAsUpEnabled(true);

        //gets the intent from the previous screen
        Intent intent = getIntent();

        //sets the intents to a variable
        String eventid = intent.getStringExtra("eventid");
        String id = intent.getStringExtra("deptid");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String start = intent.getStringExtra("start");
        String end = intent.getStringExtra("end");
        String location = intent.getStringExtra("location");

        //sets the variables to a view so that it can be displayed
        ((TextView) findViewById(R.id.eventid)).setText(eventid);
        ((TextView) findViewById(R.id.deptid)).setText(id);
        ((TextView) findViewById(R.id.eventname)).setText(name);
        ((TextView) findViewById(R.id.description)).setText(description);
        ((TextView) findViewById(R.id.starttime)).setText(start);
        ((TextView) findViewById(R.id.endtime)).setText(end);
        ((TextView) findViewById(R.id.location)).setText(location);

        Bundle bundle = intent.getExtras();
        String status = bundle.getString("status");
    }


    /**
     * This method defines the action of the back button
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