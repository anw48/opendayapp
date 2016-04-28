package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class is used to display detailed information about a refreshment option
 *
 * This information is retreived by intents from the previous activity
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class Refreshment_Info_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshment_info);
        setTitle(getResources().getString(R.string.refreshmentinfo));
        getActionBar().setDisplayHomeAsUpEnabled(true);


        //gets the intent values
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        String open = intent.getStringExtra("open");
        String close = intent.getStringExtra("close");

        ((TextView)findViewById(R.id.id)).setText(id);
        ((TextView)findViewById(R.id.name)).setText(name);
        ((TextView)findViewById(R.id.description)).setText(description);
        ((TextView)findViewById(R.id.opentime)).setText(open);
        ((TextView)findViewById(R.id.closetime)).setText(close);


        //sets the image
        ImageView imgView = (ImageView) findViewById(R.id.image);
        int resID = getResources().getIdentifier(image, "drawable", getPackageName());
        imgView.setImageResource(resID);

        Bundle bundle = intent.getExtras();
        String status = bundle.getString("status");
    }


    /**
     * Sets the home button value
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
