package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class DepartmentActivity extends Activity implements View.OnClickListener {

    ImageButton academicbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        academicbutton = (ImageButton) findViewById(R.id.academic);
        academicbutton.setOnClickListener(this);



    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;

        // update with navigation stuff !!!!!!!!!!!!!!!!!
    }


    public void onClick(View v){

        switch(v.getId())
        {
            case R.id.academic:
            {
                Intent myIntent = new Intent(DepartmentActivity.this,AcademicDepartments.class);
                startActivity(myIntent);
                break;
            }
        }
    }
}
