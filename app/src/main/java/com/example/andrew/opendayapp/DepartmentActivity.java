package com.example.andrew.opendayapp;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

///
public class DepartmentActivity extends ListActivity {


    private static String url = "http://ten32.co.uk/landare/get_all_departments.php";


    private static final String TAG_DEPARTMENT_INFO = "departmentinfo";
    private static final String TAG_DEPT_ID = "dept_id";
    private static final String TAG_EVENT_ID = "event_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMAGE_NAME = "image_name";
    private static final String TAG_EVENT_NAME = "event_name";
    private static final String TAG_START_TIME = "start_time";
    private static final String TAG_END_TIME = "end_time";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_EVENT_DESCRIPTION = "event_description";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        new GetDepartments().execute();

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }



    private class GetDepartments extends AsyncTask<Void, Void, Void> {

        ArrayList<HashMap<String, String>> departmentlist;

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(DepartmentActivity.this);
            pDialog.setMessage("Please wait information is being retrieved");
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            WebRequest webreq = new WebRequest();

            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GETRequest);

            Log.d("Response: ", "> " + jsonStr);

            departmentlist = ParseJSON(jsonStr);

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    DepartmentActivity.this, departmentlist,
                    R.layout.activity_department, new String[]{TAG_NAME}, new int[]{R.id.name});

            setListAdapter(adapter);
        }

    }

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> departmentlist = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray departments = jsonObj.getJSONArray(TAG_DEPARTMENT_INFO);

                // looping through All Students
                for (int i = 0; i < departments.length(); i++) {
                    JSONObject c = departments.getJSONObject(i);

                    String id = c.getString(TAG_DEPT_ID);
                    String name = c.getString(TAG_NAME);



                    // tmp hashmap for single student
                    HashMap<String, String> student = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    student.put(TAG_DEPT_ID, id);
                    student.put(TAG_NAME, name);


                    // adding student to students list
                    departmentlist.add(student);
                }
                return departmentlist;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }





}



