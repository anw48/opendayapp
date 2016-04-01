package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Student_Experience_Activity extends Activity implements View.OnClickListener {

    ImageButton accommodationbutton, refreshementsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_experience);
        setTitle(getResources().getString(R.string.studentexperiencetitle));
        getActionBar().setDisplayHomeAsUpEnabled(true);


        accommodationbutton = (ImageButton) findViewById(R.id.accommodationbutton);
        accommodationbutton.setOnClickListener(this);

        refreshementsbutton = (ImageButton) findViewById(R.id.refreshementsimageButton);
        refreshementsbutton.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accommodationbutton: {
                Intent myIntent = new Intent(Student_Experience_Activity.this,
                        Accommodation_Activity.class);
                startActivity(myIntent);

                break;
            }
            case R.id.refreshementsimageButton: {
                Intent myIntent = new Intent(Student_Experience_Activity.this,
                        Refreshments_Activity.class);
                startActivity(myIntent);

                break;
            }
        }
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
