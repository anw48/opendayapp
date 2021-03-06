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


/**
 * This class is used to retrieve the list of accommodation options
 *
 * This class also finds the language to retrieve the information in.
 *
 * The data retrieve is retrieved from the database by the webservice
 * and encoded as a JSON string and sent to this application
 *
 * This class acts as a setup class to define all the variables used in the class to retrieve the data from the database
 *
 * Part of this class was constructed using code form the following website
 * http://mobilesiri.com/json-parsing-in-android-using-android-studio/
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class Accommodation_Activity extends ListActivity implements AdapterView.OnItemClickListener{


    private static String url;
    Locale location;

    //The following variables are used to store the retrieved data from the webservice
    //These variables should not be changed unless the JSON string is also changed
    private static final String TAG_ACCOMMODATION_INFO = "Accommodation";
    private static final String TAG_ID = "accommodationId";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMAGE_NAME = "imageName";




    /**
     * This method starts the activity when it is called
     * The title of the activity is also set here
     * The back button is also set here

     *
     *  @param savedInstanceState auto generated
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getResources().getString(R.string.accommodationlist));

        findLocale();

        String locationparam = location.toString();

        url = "http://aber.dynamic-dns.net/AberOpenDay/accommodationWs.json?lang=" + locationparam;

        new GetAccommodation().execute();

    }

    /**
     * This method defines what happens when the user presses the back button
     * In this case return to the previous screen
     * @param item android menu item
     * @return returns the action of the button click
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
     * This class is used to retrieve the accommodation
     * this is where the actual logic of retrieving the data occurs
     * A JSON string is retrieve from the web sevice and the decoded by the following methods
     */
private class GetAccommodation extends AsyncTask<Void, Void, Void> {

    ArrayList<HashMap<String, String>> eventlist;
    ProgressDialog pDialog;

        /**
         * This method sets the dialoag seen by the user when the data is being retrieved
         * usually only seen by the user when there is a slow network connection
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Accommodation_Activity.this);
            pDialog.setMessage("Please wait information is being retrieved");
            pDialog.show();
        }


        /**
         * This method calls the web request class and passes in the url to make the call to web service
         *
         * @param arg0 method arguments
         * @return returns null
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
                    Accommodation_Activity.this, eventlist,
                    R.layout.activity_accommodation, new String[]{TAG_NAME}, new int[]{R.id.name});


            ListView lv = getListView();
            lv.setTextFilterEnabled(true);

            // sets a click listener for each item
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // pass data to next activity
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


    /**
     * This method sets the click listener for each list view item
     * @param adapter clcik adapter
     * @param arg1 argument 1
     * @param position position of item
     * @param arg3 argument
     */
        @Override
        public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
            String item = adapter.getItemAtPosition(position).toString();
        }


    /**
     * This method passes in the JSON response and loops through
     * all retrieved events and passes
     * each event into a JSON object
     * @param json json string from web service
     * @return returns error message
     */
        private ArrayList<HashMap<String, String>> ParseJSON(String json) {
            if (json != null) {
                try {
                    // Hashmap for ListView
                    ArrayList<HashMap<String, String>> eventlist = new ArrayList<HashMap<String, String>>();

                    JSONObject jsonObj = new JSONObject(json);

                    // Getting JSON Array node
                    JSONArray events = jsonObj.getJSONArray(TAG_ACCOMMODATION_INFO);

                    // looping through All accommodation
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        String accommodationId = c.getString(TAG_ID);
                        String name= c.getString(TAG_NAME);
                        String image_name = c.getString(TAG_IMAGE_NAME);
                        String descrption = c.getString(TAG_DESCRIPTION);


                        // tmp hashmap for single accommodation option
                        HashMap<String, String> event = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        event.put(TAG_ID, accommodationId);
                        event.put(TAG_NAME, name);
                        event.put(TAG_IMAGE_NAME, image_name);
                        event.put(TAG_DESCRIPTION, descrption);



                        // adding accommodation to list

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

