package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * This class is used to retrieve passed information from the aaccommodation list activity
 * the data is then displayed to the user
 *
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class Accommodation_Info_Activity extends Activity {

    /**
     * This method starts the activity when it is called
     * The title of the activity is also set here
     * The back button is also set here
     *
     * The department Id is retrieved from the previous activity
     * and used in the url to know what data to retrieve from the database
     *
     *  @param savedInstanceState auto generated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation__info);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getResources().getString(R.string.accommodationinfo));

        // gets the data from the intent
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");

        // sets the passed data to the correct place
        ((TextView)findViewById(R.id.id)).setText(id);
        ((TextView)findViewById(R.id.name)).setText(name);
        ((TextView)findViewById(R.id.description)).setText(description);

        ImageView imgView = (ImageView) findViewById(R.id.image);

        int resID = getResources().getIdentifier(image, "drawable", getPackageName());

        imgView.setImageResource(resID);

        Bundle bundle = intent.getExtras();
        String status = bundle.getString("status");




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
