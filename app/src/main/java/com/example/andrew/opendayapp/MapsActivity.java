package com.example.andrew.opendayapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class is used to create the map activity
 * This activity displays the google map
 *
 *
 * @author Andrew Wynne Williams
 * @version 1.0
 * @since 17-4-2016
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
    }


    /**
     * Creates a menu so that the user can select
     * what markers are on the map
     *
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        //back button
        if (item.getItemId()== android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        //show depatments only
        if (item.getItemId()== R.id.departmentoption) {

            mMap.clear();
            getAcademicMarkers();
            return true;
        }
        //show accommodation only
        if (item.getItemId()== R.id.accommodationoption) {
            mMap.clear();
            getAccommodatiomMarkers();
            return true;
        }
        //show refrhments only
        if (item.getItemId()== R.id.refreshmentoption) {
            mMap.clear();
            getRefreshmentMarkers();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Creates the google map
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
       setTitle(getResources().getString(R.string.title_activity_maps));

        mMap = googleMap;
        //allows the user to controll the zoom level
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        //sets the defualt zoom and location. to do this code from the following website was used
        //http://stackoverflow.com/questions/14157536/how-do-i-set-default-location-and-zoom-level-for-google-map-api-v2
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.415549, -4.063668), 16.0f));

        //sows the academic markers by defulat
        getAcademicMarkers();

    }

    /**
     * This method displays the refreshment markers
     */
    public void getRefreshmentMarkers(){
        //two dimentional array to store th data
        String refreshmentmarkers [][] = {
                { "52.417892", "-4.065041", "TaMed Da Restaurant" },
                { "52.417756", "-4.064891", "TaMed Bach Café Bar" },
                { "52.416361", "-4.066775", "IBER bach" },
                { "52.420868", "-4.060332", "SGUBORfach" },
                { "52.410525", "-4.055534", "Blas Padarn" },
                { "52.415616", "-4.063142", "Arts Centre Café" },
                { "52.415056", "-4.063110", "Piazza Café" },
                { "52.415920", "-4.062579", "Theatre Bar" },
                { "52.415179", "-4.062920", "Student Shop" },
                { "52.415060", "-4.062952", "Cwtch Bar" },
                { "52.415664", "-4.061113", "Brynamlwg Tavern" }
        };

        for (int i = 0; i <refreshmentmarkers.length; i++){
            // create a marker for each item in arry
            Marker refreshment = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(refreshmentmarkers[i][0]),Double.parseDouble(refreshmentmarkers[i][1])))
                    .title(refreshmentmarkers[i][2])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
    }

    /**
     * This method creates the accommodation markers
     */
    public void getAccommodatiomMarkers(){
        // creates two dimentional array
        String accomomdationmarkers[][] = {
                { "52.418143", "-4.064024", "Penbryn " },
                { "52.420100", "-4.062829", "Pentre Jane Morgan" },
                { "52.420837", "-4.084336", "Seafront Residences" },
                { "52.418221", "-4.061256", "Cwrt Mawr" },
                { "52.417275", "-4.061907", "Rosser" },
                { "52.416408", "-4.061049", "Trefloyne" },
                { "52.421765", "-4.060265", "Fferm Penglais" }
        };
        for (int i = 0; i <accomomdationmarkers.length; i++){
            //create a marker for each item in an array
            Marker accommodation = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(accomomdationmarkers[i][0]),Double.parseDouble(accomomdationmarkers[i][1])))
                .title(accomomdationmarkers[i][2])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }

    /**
     * This method creates the academic markers
     */
    public void getAcademicMarkers(){
        //create a two dimentional array
        String[][] academicmarkers = {
                { "52.413572", "-4.077513", "Art Department" },
                { "52.415707", "-4.066443", "IBERS" },
                { "52.416269", "-4.065568", "Computer Science Departmet" },
                { "52.417590", "-4.063489", "Education and Lifelong Learning Department" },
                { "52.417125", "-4.064581", "Department Of English And Creative Writing" },
                { "52.416522", "-4.066432", "Geography & Earth Sciences Department" },
                { "52.417048", "-4.064439", "Department Of History & Welsh History" },
                { "52.410130", "-4.052488", "Department Information Studies" },
                { "52.415245", "-4.063916", "International Politics Department" },
                { "52.410880", "-4.054024", "Law and Criminology Department" },
                { "52.413602", "-4.064690", "School Of Management & Business" },
                { "52.416013", "-4.065482", "Institute of Mathematical and Physical Sciences" },
                { "52.416721", "-4.064321", "Modern Languages Department" },
                { "52.417654", "-4.063603", "Psychology Department" },
                { "52.416995", "-4.062832", "Theatre, Film & Television Studies Department" },
                { "52.416721", "-4.064321", "Department of Welsh and Celtic Studies" },
                { "52.413602", "-4.064690", "Department of Sport and Exercise Science" }
        };

        for (int i = 0; i < academicmarkers.length; i++) {
            //for each item in the array create a marker
            Marker aber = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(academicmarkers[i][0]), Double.parseDouble (academicmarkers[i][1])))
                    .title(academicmarkers[i][2]));
        }
    }
}
