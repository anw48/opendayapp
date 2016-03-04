package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Getting_Around_Activity extends Activity implements View.OnClickListener {

    ImageButton mapbutton, carbutton, trainbutton, busbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_around_);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mapbutton = (ImageButton) findViewById(R.id.mapImageButton);
        mapbutton.setOnClickListener(this);

        carbutton = (ImageButton) findViewById(R.id.carImageButton);
        carbutton.setOnClickListener(this);

        trainbutton = (ImageButton) findViewById(R.id.trainImageButton);
        trainbutton.setOnClickListener(this);

        busbutton = (ImageButton) findViewById(R.id.busImageButton);
        busbutton.setOnClickListener(this);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.mapImageButton: {
                Intent myIntent = new Intent(Getting_Around_Activity.this,
                        WelcomeActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.carImageButton: {
                Intent myIntent = new Intent(Getting_Around_Activity.this,
                        CarActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.trainImageButton: {
                Intent myIntent = new Intent(Getting_Around_Activity.this,
                        TrainActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.busImageButton: {
                Intent myIntent = new Intent(Getting_Around_Activity.this,
                        BusActivity.class);
                startActivity(myIntent);
                break;
            }


        }
    }
}
