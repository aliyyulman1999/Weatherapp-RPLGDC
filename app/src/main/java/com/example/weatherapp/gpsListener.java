package com.example.weatherapp;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class gpsListener implements LocationListener {
    private Activity activity;
    private LocationManager locManager;
    private int numOfUpdate;
    public static final int maxUpdate = 10;

    public gpsListener(Activity activity, LocationManager locManager){
        this.activity = activity;
        this.locManager = locManager;
    }

    @Override
    public void onLocationChanged(Location loc){
        if (numOfUpdate < maxUpdate){
            numOfUpdate++;
            Log.w("LAT",String.valueOf(loc.getLatitude()));
            Log.w("LONG",String.valueOf(loc.getLongitude()));
            Log.w("ACCURACY",String.valueOf(loc.getAccuracy()+ " m"));
            Log.w("PROVIDER",String.valueOf(loc.getLongitude()));
            Log.w("SPEED",String.valueOf(loc.getSpeed()+" m/s"));
            Log.w("ALTITUDE",String.valueOf(loc.getAltitude()));
            Log.w("BEARING",String.valueOf(loc.getBearing()+ " degrees of true north"));

            String message;
            if (loc != null){
                message = "Current location is : Latitude : "+loc.getAltitude()+
                        "Longitude : "+loc.getLongitude();
            }
            else {
                locManager.removeUpdates(this);
            }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(activity, "Gps Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(activity, "Gps Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
