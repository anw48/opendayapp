package com.example.andrew.opendayapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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


import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class AcademicDepartments extends ListActivity implements AdapterView.OnItemClickListener {

    private static String urlparam;

    Locale location;

    private static final String TAG_DEPARTMENT_INFO = "AcademicDepartments";
    private static final String TAG_DEPT_ID = "dept_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMAGE_NAME = "image_name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        setTitle(getResources().getString(R.string.academicdepartments));
        getActionBar().setDisplayHomeAsUpEnabled(true);


        findLocale();
        String param = location.toString();

        urlparam = getString(R.string.academicdepartmentsurl)+ param;



        new GetDepartments().execute();
    }

    public void findLocale(){

        location  = getResources().getConfiguration().locale;
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
            pDialog = new ProgressDialog(AcademicDepartments.this);
            pDialog.setMessage("Please wait information is being retrieved");
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            WebRequest webreq = new WebRequest();

            String jsonStr = webreq.makeWebServiceCall(urlparam, WebRequest.GET);

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
                    AcademicDepartments.this, departmentlist,
                    R.layout.activity_department, new String[]{TAG_NAME}, new int[]{R.id.name});

            ListView lv = getListView();
            lv.setTextFilterEnabled(true);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent myIntent = new Intent(AcademicDepartments.this, AcademicDepartmentInfoActivity.class);

                    String name = departmentlist.get(position).get(TAG_NAME);
                    String deptid = departmentlist.get(position).get(TAG_DEPT_ID);
                    String description = departmentlist.get(position).get(TAG_DESCRIPTION);
                    String image = departmentlist.get(position).get(TAG_IMAGE_NAME);

                    myIntent.putExtra("id", deptid);
                    myIntent.putExtra("name", name);
                    myIntent.putExtra("description", description);
                    myIntent.putExtra("image", image);


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
                JSONArray departments = jsonObj.getJSONArray(TAG_DEPARTMENT_INFO);

                // looping through All Students
                for (int i = 0; i < departments.length(); i++) {
                    JSONObject c = departments.getJSONObject(i);

                    String dept_id = c.getString(TAG_DEPT_ID);
                    String name = c.getString(TAG_NAME);
                    String descrption = c.getString(TAG_DESCRIPTION);
                    String image_name = c.getString(TAG_IMAGE_NAME);


                    // tmp hashmap for single student
                    HashMap<String, String> department = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    department.put(TAG_DEPT_ID, dept_id);
                    department.put(TAG_NAME, name);
                    department.put(TAG_DESCRIPTION, descrption);
                    department.put(TAG_IMAGE_NAME, image_name);


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



