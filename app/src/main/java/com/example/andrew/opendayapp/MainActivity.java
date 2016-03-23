package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageButton welcomebutton, departmentsbutton, disclaimersbutton, plannerbutton, contactbutton, gettingaroundbutton, toursbutton, testbutton;
    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.app_name));


        welcomebutton = (ImageButton) findViewById(R.id.welcomeimageButton);
        welcomebutton.setOnClickListener(this);

        departmentsbutton = (ImageButton) findViewById(R.id.talksimageButton);
        departmentsbutton.setOnClickListener(this);

        disclaimersbutton = (ImageButton) findViewById(R.id.disclaimersimageButton);
        disclaimersbutton.setOnClickListener(this);

        plannerbutton = (ImageButton) findViewById(R.id.plannerimageButton);
        plannerbutton.setOnClickListener(this);

        contactbutton = (ImageButton) findViewById(R.id.contactimageButton);
        contactbutton.setOnClickListener(this);

        gettingaroundbutton = (ImageButton) findViewById(R.id.gettingaroundimageButton);
        gettingaroundbutton.setOnClickListener(this);

        toursbutton = (ImageButton) findViewById(R.id.toursimageButton);
        toursbutton.setOnClickListener(this);

        testbutton = (ImageButton) findViewById(R.id.refreshementsimageButton);
        testbutton.setOnClickListener(this);


      }


    public void onClick(View v){


        switch(v.getId())
        {
            case R.id.welcomeimageButton:
            {
                Intent myIntent = new Intent(MainActivity.this,
                        WelcomeActivity.class);
                startActivity(myIntent);

                break;
            }
            case R.id.talksimageButton:
            {
                Intent myIntent = new Intent(MainActivity.this,
                        Department_Home_Activity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.disclaimersimageButton:
            {
                Intent myIntent = new Intent(MainActivity.this,
                        DiscliamersActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.plannerimageButton:
            {
                Intent myIntent = new Intent(MainActivity.this,
                        PlannerActivity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.contactimageButton:
            {
                Intent myIntent = new Intent(MainActivity.this,
                        Contact_Activity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.gettingaroundimageButton:
            {
                Intent myIntent = new Intent(MainActivity.this,
                        Getting_Around_Activity.class);
                startActivity(myIntent);
                break;
            }
            case R.id.toursimageButton:
            {
                Intent myIntent = new Intent(MainActivity.this,
                        ToursActivity.class);
                startActivity(myIntent);
                break;
            }



        }

    }
    //


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.title);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.english:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                setLocale("en");
                return true;
            case R.id.welsh:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                setLocale("cy");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
    }





    }




