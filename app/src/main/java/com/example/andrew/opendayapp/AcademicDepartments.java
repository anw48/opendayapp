package com.example.andrew.opendayapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * This class is used to retrieve the list of academic departments
 *
 * This class also finds the language to retrieve the information in.
 *
 * The data retrieve is retrieved from the database by the webservice
 * and encoded as a JSON string and sent to this application
 *
 * This class acts as a setup class to define all the variables
 * used in the class to retrieve the data from the database
 *
 *
 * Part of this class was constructed using code form the following website
 * http://mobilesiri.com/json-parsing-in-android-using-android-studio/
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class AcademicDepartments extends ListActivity implements AdapterView.OnItemClickListener {

    //variable to hold the url
    private static String urlparam;

    Locale location;

    //The following variables are used to store the retrieved data from the webservice
    //These variables should not be changed unless the JSON string is also changed
    private static final String TAG_DEPARTMENT_INFO = "Departments";
    private static final String TAG_DEPT_ID = "departmentId";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMAGE_NAME = "imageName";


    /**
     * This method starts the activity when it is called
     * The title of the activity is also set here
     * The back button is also set here
     *
     *
     *  @param savedInstanceState auto generated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        setTitle(getResources().getString(R.string.academicdepartments));
        getActionBar().setDisplayHomeAsUpEnabled(true);


        findLocale();
        String param = location.toString();


        urlparam = "http://aber.dynamic-dns.net/AberOpenDay/departmentWs.json?lang=" + param + "&academic=Y";

        new GetDepartments().execute();
    }

    /**
     * This method finds the current locale and is ues in the url
     * to know what language to retrieve the data in.
     */
    public void findLocale(){

        location  = getResources().getConfiguration().locale;
    }


    /**
     * This method defines what happens when the user presses the back button
     * In this case return to the previous screen
     * @param item item in the action bar
     * @return the return action
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }

    /**
     * This class is used to retrieve the academic departments
     * this is where the actual logic of retrieving the data occurs
     * A JSON string is retrieve from the web service and the decoded by the following methods
     */
    private class GetDepartments extends AsyncTask<Void, Void, Void> {

        ArrayList<HashMap<String, String>> departmentlist;
        ProgressDialog pDialog;

        /**
         * This method sets the dioloag seen by the user when the data is being retrieved
         * usually only seen by the user when there is a slow network connection
         */
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(AcademicDepartments.this);
            pDialog.setMessage("Please wait information is being retrieved");
            pDialog.show();
        }

        /**
         * This method calls the web request class and passes in the url to make the call to web service
         *
         * @param arg0 argument
         * @return returns
         */
        @Override
        protected Void doInBackground(Void... arg0) {
            WebRequest webreq = new WebRequest();

            String jsonStr = webreq.makeWebServiceCall(urlparam, WebRequest.GET);

            Log.d("Response: ", "> " + jsonStr);

            departmentlist = ParseJSON(jsonStr);

            return null;
        }

        /**
         * This method inserts the retrieved JSON data into a list view and
         * passes data to the next activity
         * @param result result of the json string
         */
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

            // sets a click listener for each item in list view
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // passes data to next activity
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

    /**
     * This method sets the click listener for each list view item
     * @param adapter item adapter
     * @param arg1 argument
     * @param position position of item
     * @param arg3 argument
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {

        String item = adapter.getItemAtPosition(position).toString();
    }

    /**
     * This method passes in the JSON response and loops through
     * all retrieved departments and passes
     * each event into a JSON object
     * @param json json string used
     * @return returns error message
     */
    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> departmentlist = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray departments = jsonObj.getJSONArray(TAG_DEPARTMENT_INFO);

                // looping through All departments
                for (int i = 0; i < departments.length(); i++) {
                    JSONObject c = departments.getJSONObject(i);

                    String dept_id = c.getString(TAG_DEPT_ID);
                    String name = c.getString(TAG_NAME);
                    String descrption = c.getString(TAG_DESCRIPTION);
                    String image_name = c.getString(TAG_IMAGE_NAME);


                    // tmp hashmap for single department
                    HashMap<String, String> department = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    department.put(TAG_DEPT_ID, dept_id);
                    department.put(TAG_NAME, name);
                    department.put(TAG_DESCRIPTION, descrption);
                    department.put(TAG_IMAGE_NAME, image_name);


                    // adding department to departments list

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



