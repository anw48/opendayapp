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
import java.util.Locale;

/**
 * This class is used to retrieve the list of events for an non academic departments based
 * on a variable passed on by the department info activity.
 *
 * This class also finds the language to retrieve the information in.
 *
 * The data retrieve is retrieved from the database by the webservice and encoded as a JSON string and sent to this application
 *
 * This class acts as a setup class to define all the variables used in the class to retrieve the data from the database
 *
 *
 * Part of this class was constructed using code form the following website
 * http://mobilesiri.com/json-parsing-in-android-using-android-studio/
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class Non_Academic_Event_List extends ListActivity implements AdapterView.OnItemClickListener {

    //variable to hold the url
    private static String url;
    Locale location;

    //The following variables are used to store the retrieved data from the webservice
    //These variables should not be changed unless the JSON string is also changed

    private static final String TAG_EVENT_INFO = "Events";
    private static final String TAG_EVENT_ID = "eventId";
    private static final String TAG_EVENT_NAME = "name";
    private static final String TAG_START_TIME = "startTime";
    private static final String TAG_END_TIME = "endTime";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_DESCRIPTION = "description";


    /**
     * This method starts the activity when it is called
     * The title of the activity is also set here
     * The back button is also set here
     *
     * The department Id is retrieved from the previous activity
     * and used in the url to know what data to retrieve from the database
     *
     *  @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_academic_event_list);
        setTitle(getResources().getString(R.string.eventlist));


        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        ((TextView) findViewById(R.id.passid)).setText(id);
        Bundle bundle = intent.getExtras();


        findLocale();

        String locationparams = location.toString();

        url = "http://aber.dynamic-dns.net/AberOpenDay/eventWs.json?lang=" + locationparams + "&deptId=" + id;

        new GetNonAcademicEvents().execute();
    }


    /**
     * This method defines what happens when the user presses the back button
     * In this case return to the previous screen
     * @param item
     * @return
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
     * This method finds the current locale and is ues in the url
     * to know what language to retrieve the data in.
     */
    public void findLocale(){

        location = getResources().getConfiguration().locale;

    }

    /**
     * This class is used to retrieve the non academic events
     * this is where the actual logic of retrieving the data occurs
     * A JSON string is retrieve from the web sevice and the decoded by the following methods
     */
    private class GetNonAcademicEvents extends AsyncTask<Void, Void, Void> {

        ArrayList<HashMap<String, String>> eventlist;
        ProgressDialog pDialog;

        /**
         * This method sets the dialoag seen by the user when the data is being retrieved
         * usually only seen by the user when there is a slow network connection
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Non_Academic_Event_List.this);
            pDialog.setMessage("Please wait information is being retrieved");
            pDialog.show();
        }

        /**
         * This method calls the web request class and passes
         * in the url to make the call to web service
         *
         * @param arg0
         * @return
         */
        @Override
        protected Void doInBackground(Void... arg0) {
            WebRequest webreq = new WebRequest();
            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GET);
            Log.d("Response: ", "> " + jsonStr);
            eventlist = ParseJSON(jsonStr);
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
                    Non_Academic_Event_List.this, eventlist,
                    R.layout.activity_academic_event__list_, new String[]{TAG_EVENT_NAME}, new int[]{R.id.name});

            ListView lv = getListView();
            lv.setTextFilterEnabled(true);

            // sets a click listener for each item in list view
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // passes data to next activity
                    Intent myIntent = new Intent(Non_Academic_Event_List.this, Event_Info_Activity.class);

                    String eventid = eventlist.get(position).get(TAG_EVENT_ID);
                    String name = eventlist.get(position).get(TAG_EVENT_NAME);
                    String start = eventlist.get(position).get(TAG_START_TIME);
                    String end = eventlist.get(position).get(TAG_END_TIME);
                    String location = eventlist.get(position).get(TAG_LOCATION);
                    String description = eventlist.get(position).get(TAG_DESCRIPTION);

                    myIntent.putExtra("eventid", eventid);
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

    /**
     * This method sets the click listener for each list view item
     * @param adapter
     * @param arg1
     * @param position
     * @param arg3
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {

        String item = adapter.getItemAtPosition(position).toString();
    }

    /**
     * This method passes in the JSON response and loops through
     * all retrieved events and passes
     * each event into a JSON object
     * @param json
     * @return
     */
    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> eventlist = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray events = jsonObj.getJSONArray(TAG_EVENT_INFO);

                // looping through All events
                for (int i = 0; i < events.length(); i++) {
                    JSONObject c = events.getJSONObject(i);

                    String event_id = c.getString(TAG_EVENT_ID);
                    String event_name = c.getString(TAG_EVENT_NAME);
                    String start_time = c.getString(TAG_START_TIME);
                    String end_time = c.getString(TAG_END_TIME);
                    String location = c.getString(TAG_LOCATION);
                    String descrption = c.getString(TAG_DESCRIPTION);


                    // tmp hashmap for single event
                    HashMap<String, String> event = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    event.put(TAG_EVENT_ID, event_id);
                    event.put(TAG_EVENT_NAME, event_name);
                    event.put(TAG_START_TIME, start_time);
                    event.put(TAG_END_TIME, end_time);
                    event.put(TAG_LOCATION, location);
                    event.put(TAG_DESCRIPTION, descrption);


                    // adding event to events list

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
