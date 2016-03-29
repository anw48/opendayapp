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
import android.widget.SimpleAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Accommodation_Activity extends ListActivity implements AdapterView.OnItemClickListener{


    private static String url;
    Locale location;

    private static final String TAG_ACCOMMODATION_INFO = "Accommodation";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMAGE_NAME = "image_name";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getResources().getString(R.string.accommodationlist));

        findLocale();

        String locationparam = location.toString();

        url = getString(R.string.serverurl) + "get_accommodation.php?code=" + locationparam;

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


    public void findLocale(){

        location = getResources().getConfiguration().locale;
    }



private class GetDepartments extends AsyncTask<Void, Void, Void> {

    ArrayList<HashMap<String, String>> eventlist;
    ProgressDialog pDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(Accommodation_Activity.this);
        pDialog.setMessage("Please wait information is being retrieved");
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        WebRequest webreq = new WebRequest();
        String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GET);
        Log.d("Response: ", "> " + jsonStr);
        eventlist = ParseJSON(jsonStr);
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
                Accommodation_Activity.this, eventlist,
                R.layout.activity_accommodation, new String[]{TAG_NAME}, new int[]{R.id.name});


        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent myIntent = new Intent(Accommodation_Activity.this, Accommodation_Info_Activity.class);

                String accommodationid = eventlist.get(position).get(TAG_ID);
                String name = eventlist.get(position).get(TAG_NAME);
                String imagename = eventlist.get(position).get(TAG_IMAGE_NAME);
                String description = eventlist.get(position).get(TAG_DESCRIPTION);

                myIntent.putExtra("id", accommodationid);
                myIntent.putExtra("image", imagename);
                myIntent.putExtra("name", name);
                myIntent.putExtra("description", description);



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
        String item = adapter.getItemAtPosition(position).toString();
    }

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> eventlist = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray events = jsonObj.getJSONArray(TAG_ACCOMMODATION_INFO);

                // looping through All Students
                for (int i = 0; i < events.length(); i++) {
                    JSONObject c = events.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String name= c.getString(TAG_NAME);
                    String image_name = c.getString(TAG_IMAGE_NAME);
                    String descrption = c.getString(TAG_DESCRIPTION);


                    // tmp hashmap for single student
                    HashMap<String, String> event = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    event.put(TAG_ID, id);
                    event.put(TAG_NAME, name);
                    event.put(TAG_IMAGE_NAME, image_name);
                    event.put(TAG_DESCRIPTION, descrption);



                    // adding student to students list

                    eventlist.add(event);
                }
                return eventlist;
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

