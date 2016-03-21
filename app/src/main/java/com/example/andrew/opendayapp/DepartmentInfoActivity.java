package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DepartmentInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_info);
        setTitle(getResources().getString(R.string.departmentinfo));
        getActionBar().setDisplayHomeAsUpEnabled(true);


        // 1. get passed intent
        Intent intent = getIntent();
        // 2. get message value from intent
        String my_data = intent.getStringExtra("my_data");
        // 3. show message on textView
        ((TextView)findViewById(R.id.name)).setText(my_data);
        // 4. get bundle from intent
        Bundle bundle = intent.getExtras();
        // 5. get status value from bundle
        String status = bundle.getString("status");
        // 6. show status on Toast
        Toast toast = Toast.makeText(this, status, Toast.LENGTH_LONG);
        toast.show();

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

