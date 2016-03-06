package com.example.andrew.opendayapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return false;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.415549, -4.063668), 16.0f));




        String[][] markers = {
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

        for (int i = 0; i < markers.length; i++) {

            Marker aber = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(markers[i][0]),Double.parseDouble (markers[i][1])))
                    .title(markers[i][2]));

        }

    }
}
