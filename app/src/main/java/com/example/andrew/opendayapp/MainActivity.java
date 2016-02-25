package com.example.andrew.opendayapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // found online check links
        button = (ImageButton) findViewById(R.id.welcomeimageButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(MainActivity.this,
                        WelcomeActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
