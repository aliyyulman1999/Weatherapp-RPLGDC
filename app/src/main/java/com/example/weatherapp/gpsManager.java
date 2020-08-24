//package com.example.weatherapp;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.provider.Settings;
//import android.widget.Toast;
//
//public class gpsManager {
//    private Activity activity;
//    private LocationManager locManager;
//    private LocationListener gpsListener;
//
////    public gpsManager(Activity activity){
////        this.activity = activity;
////    }
//
//    public void start(){
//        locManager = (LocationManager)activity.getSystemService(Context.LOCATION_SERVICE);
//        if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            setUp();
//            findLoc();
//        }
//        else {
//            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
//            alertBuilder
//                    .setMessage("GPS is disabled, do you want to enable it?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//                        @Override
//                        public void onClick(DialogInterface dialog, int id){
//                            Intent callGPSSetting = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                            activity.startActivity(callGPSSetting);
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    });
//            AlertDialog alertDialog = alertBuilder.create();
//            alertDialog.show();
//        }
//    }
//
//    public void setUp(){
//        gpsListener = new gpsListener(activity,locManager);
//    }
//
//    public void findLoc(){
//        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1,gpsListener);
//        if(locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER )== null){
//            Toast.makeText(activity,"Last location null",Toast.LENGTH_SHORT).show();
//        }
//        else{
//            gpsListener.onLocationChanged(locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
//        }
//    }
//}
