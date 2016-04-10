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

public class Refreshments_Activity extends ListActivity implements AdapterView.OnItemClickListener {


    private static String url;
    Locale location;

    private static final String TAG_REFRESHMENTS_INFO = "Refreshments";
    private static final String TAG_ID = "refreshmentId";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMAGE_NAME = "imageName";
    private static final String TAG_OPENING_TIME = "openingTime";
    private static final String TAG_CLOSING_TIME = "closingTime";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshments);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getResources().getString(R.string.refreshmentstitle));


        findLocale();

        String locationparam = location.toString();

       // url = getString(R.string.serverurl) + "get_refreshements.php?code=" + locationparam;

        url = "http://landare.dynamic-dns.net/AberOpenDay/refreshmentWs.json?lang=" + locationparam;

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

        ArrayList<HashMap<String, String>> refreshmentlist;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Refreshments_Activity.this);
            pDialog.setMessage("Please wait information is being retrieved");
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            WebRequest webreq = new WebRequest();
            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GET);
            Log.d("Response: ", "> " + jsonStr);
            refreshmentlist = ParseJSON(jsonStr);
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
                    Refreshments_Activity.this, refreshmentlist,
                    R.layout.activity_refreshments, new String[]{TAG_NAME}, new int[]{R.id.name});


            ListView lv = getListView();
            lv.setTextFilterEnabled(true);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent myIntent = new Intent(Refreshments_Activity.this, Refreshment_Info_Activity.class);

                    String accommodationid = refreshmentlist.get(position).get(TAG_ID);
                    String name = refreshmentlist.get(position).get(TAG_NAME);
                    String imagename = refreshmentlist.get(position).get(TAG_IMAGE_NAME);
                    String description = refreshmentlist.get(position).get(TAG_DESCRIPTION);
                    String open = refreshmentlist.get(position).get(TAG_OPENING_TIME);
                    String close = refreshmentlist.get(position).get(TAG_CLOSING_TIME);

                    myIntent.putExtra("id", accommodationid);
                    myIntent.putExtra("image", imagename);
                    myIntent.putExtra("name", name);
                    myIntent.putExtra("description", description);
                    myIntent.putExtra("open", open);
                    myIntent.putExtra("close", close);



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
                ArrayList<HashMap<String, String>> refreshmentlist = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray refresehments = jsonObj.getJSONArray(TAG_REFRESHMENTS_INFO);

                // looping through All Students
                for (int i = 0; i < refresehments.length(); i++) {
                    JSONObject c = refresehments.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String name= c.getString(TAG_NAME);
                    String image_name = c.getString(TAG_IMAGE_NAME);
                    String descrption = c.getString(TAG_DESCRIPTION);
                    String opening_time = c.getString(TAG_OPENING_TIME);
                    String closing_time = c.getString(TAG_CLOSING_TIME);


                    // tmp hashmap for single student
                    HashMap<String, String> refreshment = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    refreshment.put(TAG_ID, id);
                    refreshment.put(TAG_NAME, name);
                    refreshment.put(TAG_IMAGE_NAME, image_name);
                    refreshment.put(TAG_DESCRIPTION, descrption);
                    refreshment.put(TAG_OPENING_TIME, opening_time);
                    refreshment.put(TAG_CLOSING_TIME, closing_time);



                    // adding student to students list

                    refreshmentlist.add(refreshment);
                }
                return refreshmentlist;
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
