package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DepartmentInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_info);
        setTitle(getResources().getString(R.string.departmentinfo));
        getActionBar().setDisplayHomeAsUpEnabled(true);



        Intent intent = getIntent();
        final String id = intent.getStringExtra("deptid");
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



                Intent myIntent = new Intent(DepartmentInfoActivity.this, Event_List_Activity.class);
                myIntent.putExtra("id",id);

                Bundle extras = new Bundle();
                myIntent.putExtras(extras);
                startActivity(myIntent);

            }
        });

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

