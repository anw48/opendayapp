package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageButton welcomebutton, departmentsbutton, disclaimersbutton, plannerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        welcomebutton = (ImageButton) findViewById(R.id.welcomeimageButton);
        welcomebutton.setOnClickListener(this);

        departmentsbutton = (ImageButton) findViewById(R.id.talksimageButton);
        departmentsbutton.setOnClickListener(this);

        disclaimersbutton = (ImageButton) findViewById(R.id.disclaimersimageButton);
        disclaimersbutton.setOnClickListener(this);

        plannerbutton = (ImageButton) findViewById(R.id.plannerimageButton);
        plannerbutton.setOnClickListener(this);
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
                        DepartmentActivity.class);
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


        }
    }





    }




