package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This method is used to display information on a selected academic department
 * the information is passed via intents from the AcademicDepartments activity
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class AcademicDepartmentInfoActivity extends Activity {

    /**
     * This method starts the activity when it is called
     * The title of the activity is also set here
     * The back button is also set here
     *
     * This method also retrieves the data from the intents to be displayed in this activity
     * The department ID is also passes in another intent to the event list activity to be used in the url
     *
     * @param savedInstanceState auto generated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_department_info);
        setTitle(getResources().getString(R.string.departmentinfo));
        getActionBar().setDisplayHomeAsUpEnabled(true);


        // gets the data from the intent
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");

        ((TextView)findViewById(R.id.deptid)).setText(id);
        ((TextView)findViewById(R.id.name)).setText(name);
        ((TextView)findViewById(R.id.description)).setText(description);

        ImageView imgView = (ImageView) findViewById(R.id.image);

        int resID = getResources().getIdentifier(image, "drawable", getPackageName());

        imgView.setImageResource(resID);

        Bundle bundle = intent.getExtras();
        String status = bundle.getString("status");



        final Button button = (Button) findViewById(R.id.buttonevents);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //intent to pass the department id to the event list
                Intent myIntent = new Intent(AcademicDepartmentInfoActivity.this, Academic_Event_List_Activity.class);
                myIntent.putExtra("id", id);

                Bundle extras = new Bundle();
                myIntent.putExtras(extras);
                startActivity(myIntent);

            }
        });

    }



    /**
     * This method defines what action to take when the user presses the back button
     * In this case navigate up to parent activity which is defined in the manifest file
     *
     *
     * @param item the item in the action bar
     * @return returns the action
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

