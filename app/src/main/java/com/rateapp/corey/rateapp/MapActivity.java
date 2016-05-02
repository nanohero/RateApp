package com.rateapp.corey.rateapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback, ConnectionCallbacks,OnConnectionFailedListener {
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    public void onConnected(Bundle connectionHint) {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        setContentView(R.layout.activity_map);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        mLastLocation = null;

        Log.i("tag", "Connection started");
    }

    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();

        Log.i("tag", "Connection stopped");
    } // end onStop()

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i("tag", "Connection suspended - reconnecting...");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i("tag", "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.i("tag", "starting onMapReady()...");

        if (null == mLastLocation) {
            Log.i("tag", "Initalizing mlastlocation");

            int mCheckPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            if (mCheckPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        99);
                mCheckPermission = PackageManager.PERMISSION_GRANTED;
            }
            // temp test
            // mCheckPermission = 0;

            if (mCheckPermission == PackageManager.PERMISSION_GRANTED) {
                Log.i("tag", "permission granted");
               //setmylocation enabled
                mMap.setMyLocationEnabled(true);

                if (null == mLastLocation) {
                    Log.i("tag", "Pre: mlastlocation is null!");

                } else {
                    Log.i("tag", "Pre: mLastLocation is not null!");
                }

                if (null == mGoogleApiClient) {
                    Log.i("tag", "mGoogleApiClient is NULL!");
                }
                else {
                    Log.i("tag", "mGoogleApiClient is not NULL!");
                    Log.i("tag", "IsConnected = " + mGoogleApiClient.isConnected());
                    Log.i("tag", "toString = " + mGoogleApiClient.toString());
                }

                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                if (null == mLastLocation) {
                    Log.i("tag", "Post: mlastlocation is null!");
                } else {
                    Log.i("tag", "Post: mLastLocation is not null!");
                }
            } else {
                Log.i("tag", "no permission!");
                Log.i("tag", "returned " + mCheckPermission);
                Log.i("tag", "PackageManager.PERMISSION_GRANTED = " +  PackageManager.PERMISSION_GRANTED);

                ///ask for permission
                //return to previous screen or quit application
            }

            if (null == mLastLocation) {
                Log.i("tag", "Post: Post: mLastLocation is NULL!");
            }
        }

        googleMap.addMarker(new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude())).title("title"));
        //googleMap.addMarker(new MarkerOptions().position(new LatLng(25.0, 25.5)).title("title"));
    }




    }

