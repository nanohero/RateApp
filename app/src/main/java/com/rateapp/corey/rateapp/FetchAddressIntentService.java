package com.rateapp.corey.rateapp;

import android.app.IntentService;
import android.content.Intent;
import android.location.Geocoder;

import java.util.Locale;

/**
 * Created by corey on 4/19/2016.
 */
public class FetchAddressIntentService extends IntentService {

    public FetchAddressIntentService() {
        super("fetch service");
    }

    protected void onHandleIntent(Intent intent) {
        Geocoder geocoder =new Geocoder(this, Locale.getDefault());

    }
}

