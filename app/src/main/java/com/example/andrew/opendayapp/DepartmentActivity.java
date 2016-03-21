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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

////////
public class DepartmentActivity extends ListActivity implements AdapterView.OnItemClickListener {


    private static String url = "http://ten32.co.uk/landare/get_all_departments.php";


   private static final String TAG_EVENT_INFO = "events";
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
        setTitle(getResources().getString(R.string.departments));
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

            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GET);

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



            ListView lv = getListView();
            lv.setTextFilterEnabled(true);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {



                    Intent myIntent = new Intent(DepartmentActivity.this, DepartmentInfoActivity.class);



                    //myIntent.putExtra("test", R.id.name);
                    myIntent.putExtra("my_data", TAG_NAME);// NEED TO REFERENCE NAME AT BOTTOM OF THIS FILE RATHER THAN TOP
                    Bundle extras = new Bundle();
                    extras.putString("status", "Data Received!");
                    myIntent.putExtras(extras);
                    startActivity(myIntent);

                }
            });





            setListAdapter(adapter);





        }

    }



    @Override
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
        // TODO Auto-generated method stub
        String item = adapter.getItemAtPosition(position).toString();

    }



    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> departmentlist = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray departments = jsonObj.getJSONArray(TAG_EVENT_INFO);

                // looping through All Students
                for (int i = 0; i < departments.length(); i++) {
                    JSONObject c = departments.getJSONObject(i);

                    String dept_id = c.getString(TAG_DEPT_ID);
                    String name = c.getString(TAG_NAME);
                    String descrption = c.getString(TAG_DESCRIPTION);
                    String image_name = c.getString(TAG_IMAGE_NAME);
                    String event_id = c.getString(TAG_EVENT_ID);
                    String event_name = c.getString(TAG_EVENT_NAME);
                    String event_description = c.getString(TAG_EVENT_DESCRIPTION);
                    String start_time = c.getString(TAG_START_TIME);
                    String end_time = c.getString(TAG_END_TIME);
                    String location = c.getString(TAG_LOCATION);




                    // tmp hashmap for single student
                    HashMap<String, String> department = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    department.put(TAG_DEPT_ID, dept_id);
                    department.put(TAG_NAME, name);
                    department.put(TAG_DESCRIPTION, descrption);
                    department.put(TAG_IMAGE_NAME, image_name);
                    department.put(TAG_EVENT_ID, event_id);
                    department.put(TAG_EVENT_NAME, event_name);
                    department.put(TAG_EVENT_DESCRIPTION, event_description);
                    department.put(TAG_LOCATION, location);
                    department.put(TAG_START_TIME, start_time);
                    department.put(TAG_END_TIME, end_time);
////he

                    // adding student to students list
                    departmentlist.add(department);
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



