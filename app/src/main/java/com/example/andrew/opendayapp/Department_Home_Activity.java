package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


/**
 * This class is used as a an options selection screen where the user can
 * choose between non academic and academic departments which will then lead
 * to an activity that will load a list of departments
 *
 *
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class Department_Home_Activity extends Activity implements View.OnClickListener{

    ImageButton academicbutton, nonacademicbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department__home_);
        setTitle(getResources().getString(R.string.eventhome));
        getActionBar().setDisplayHomeAsUpEnabled(true);



        academicbutton = (ImageButton) findViewById(R.id.academicimagebutton);
        academicbutton.setOnClickListener(this);


        nonacademicbutton = (ImageButton) findViewById(R.id.nonacademicimagebutton);
        nonacademicbutton.setOnClickListener(this);

    }


    /**
     * This method finds the view selected by the user and loads the specified activity.
     *
     * @param v the item in the view
     */
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.academicimagebutton: {
                Intent myIntent = new Intent(Department_Home_Activity.this,
                        AcademicDepartments.class);
                startActivity(myIntent);

                break;
            }
            case R.id.nonacademicimagebutton: {
                Intent myIntent = new Intent(Department_Home_Activity.this,
                        DepartmentActivity.class);
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
