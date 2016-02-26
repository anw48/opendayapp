package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageButton welcomebutton, departmentsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        welcomebutton = (ImageButton) findViewById(R.id.welcomeimageButton);
        welcomebutton.setOnClickListener(this);

        departmentsbutton = (ImageButton) findViewById(R.id.talksimageButton);
        departmentsbutton.setOnClickListener(this);
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


        }
    }



    }




