package com.example.andrew.opendayapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AcademicDepartments extends Activity {








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_departments);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        findLocale();
        TextView mytext = (TextView)findViewById(R.id.test);
        mytext.setText(test.toString());
        TextView example = (TextView)findViewById(R.id.test1);

        if (test.getLanguage().equals("cy")){
            example.setText("the application is in welsh");

        }
        else{
            example.setText("the application is in english");
        }

    }



     Locale test;

    public void findLocale(){
        test = getResources().getConfiguration().locale;
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
