package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class Event_Info_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();

        String eventid = intent.getStringExtra("eventid");
        String id = intent.getStringExtra("deptid");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String start = intent.getStringExtra("start");
        String end = intent.getStringExtra("end");
        String location = intent.getStringExtra("location");

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

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }


}
