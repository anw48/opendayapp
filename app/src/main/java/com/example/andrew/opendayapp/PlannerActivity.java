package com.example.andrew.opendayapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PlannerActivity extends Activity {

    private final static String STORETEXT="storetext.txt";
    private EditText txtEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
        setTitle(getResources().getString(R.string.planner));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        txtEditor=(EditText)findViewById(R.id.plannertextbox);
        readFileInEditor();

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }

    public void clickedSave(View v){

        try{
            OutputStreamWriter out=
                    new OutputStreamWriter(openFileOutput(STORETEXT,0));
            out.write(txtEditor.getText().toString());
            out.close();

            Toast
                    .makeText(this, "The contents are saved",Toast.LENGTH_LONG)
                    .show();
        }
        catch (Throwable t){
            Toast
                    .makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG)
        .show();
        }

    }

    public void readFileInEditor()

    {

        try {

            InputStream in = openFileInput(STORETEXT);
            if (in != null) {

                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;
                StringBuilder buf=new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str);

                }

                in.close();
                txtEditor.setText(buf.toString());

            }

        }

        catch (java.io.FileNotFoundException e) {


        }

        catch (Throwable t) {
            Toast
                    .makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG)
                    .show();

        }

    }


}
