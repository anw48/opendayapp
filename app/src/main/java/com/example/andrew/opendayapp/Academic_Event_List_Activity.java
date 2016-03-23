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
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Academic_Event_List_Activity extends ListActivity implements AdapterView.OnItemClickListener {


    private static String url;

    private static final String TAG_EVENT_INFO = "AcademicEvents";
    private static final String TAG_EVENT_ID = "event_id";
    private static final String TAG_DEPT_ID = "dept_id";
    private static final String TAG_EVENT_NAME = "event_name";
    private static final String TAG_START_TIME = "Start_time";
    private static final String TAG_END_TIME = "end_time";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_DESCRIPTION = "description";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_event__list_);
        setTitle(getResources().getString(R.string.eventlist));
        getActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        ((TextView)findViewById(R.id.passid)).setText(id);
        Bundle bundle = intent.getExtras();

url = "http://ten32.co.uk/openday/eventstest.php?id=" + id ;//change url and locale


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

        ArrayList<HashMap<String, String>> eventlist;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Academic_Event_List_Activity.this);
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
                    Academic_Event_List_Activity.this, eventlist,
                    R.layout.activity_academic_event__list_, new String[]{TAG_EVENT_NAME}, new int[]{R.id.name});

            ListView lv = getListView();
            lv.setTextFilterEnabled(true);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent myIntent = new Intent(Academic_Event_List_Activity.this, Event_Info_Activity.class);

                    String eventid = eventlist.get(position).get(TAG_EVENT_ID);
                    String name = eventlist.get(position).get(TAG_EVENT_NAME);
                    String deptid = eventlist.get(position).get(TAG_DEPT_ID);
                    String start = eventlist.get(position).get(TAG_START_TIME);
                    String end = eventlist.get(position).get(TAG_END_TIME);
                    String location = eventlist.get(position).get(TAG_LOCATION);
                    String description = eventlist.get(position).get(TAG_DESCRIPTION);

                    myIntent.putExtra("eventid", eventid);
                    myIntent.putExtra("id", deptid);
                    myIntent.putExtra("name", name);
                    myIntent.putExtra("description", description);
                    myIntent.putExtra("start", start);
                    myIntent.putExtra("end", end);
                    myIntent.putExtra("location", location);


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
                ArrayList<HashMap<String, String>> eventlist = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray events = jsonObj.getJSONArray(TAG_EVENT_INFO);

                // looping through All Students
                for (int i = 0; i < events.length(); i++) {
                    JSONObject c = events.getJSONObject(i);

                    String event_id = c.getString(TAG_EVENT_ID);
                    String dept_id = c.getString(TAG_DEPT_ID);
                    String event_name = c.getString(TAG_EVENT_NAME);
                    String start_time = c.getString(TAG_START_TIME);
                    String end_time = c.getString(TAG_END_TIME);
                    String location = c.getString(TAG_LOCATION);
                    String descrption = c.getString(TAG_DESCRIPTION);


                    // tmp hashmap for single student
                    HashMap<String, String> event = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    event.put(TAG_EVENT_ID, event_id);
                    event.put(TAG_DEPT_ID, dept_id);
                    event.put(TAG_EVENT_NAME, event_name);
                    event.put(TAG_START_TIME, start_time);
                    event.put(TAG_END_TIME, end_time);
                    event.put(TAG_LOCATION, location);
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



