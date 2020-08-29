//package com.example.weatherapp;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//
//import com.example.weatherapp.adapter.viewPagerAdapter;
//import com.example.weatherapp.common.Common;
//import com.example.weatherapp.model.Coord;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.BaseTransientBottomBar;
//import com.google.android.material.snackbar.Snackbar;
//import com.google.android.material.tabs.TabLayout;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.MultiplePermissionsReport;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.coordinatorlayout.widget.CoordinatorLayout;
//import androidx.core.app.ActivityCompat;
//import androidx.viewpager.widget.ViewPager;
//
//import android.os.Looper;
//import android.provider.Settings;
//import android.util.Log;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//
//import java.security.acl.Permission;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//    private Toolbar toolbar;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    private CoordinatorLayout coordinatorLayout;
//    private FusedLocationProviderClient fusedLocationProviderClient;
//    private LocationCallback locationCallback;
//    private LocationRequest locationRequest;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.root_view);
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        //request permission
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                .withListener(new MultiplePermissionsListener(){
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()){
//                            buildLocationRequest();
//                            buildLocationCallBack();
//                            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                                    ActivityCompat.checkSelfPermission(MainActivity.this,
//                                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                return;
//                            }
//                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
//                            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        Snackbar.make(coordinatorLayout,"Permission Denied", Snackbar.LENGTH_LONG).show();
//                    }
//                }).check();
//
//    }
//
//    private void buildLocationCallBack() {
//        locationCallback = new LocationCallback(){
//            @Override
//            public void onLocationResult(LocationResult locationResult){
//                super.onLocationResult(locationResult);
//
//                Common.current_location = locationResult.getLastLocation();
//                viewPager = (ViewPager)findViewById(R.id.view_pager);
//                setupViewPager(viewPager);
//                tabLayout = (TabLayout)findViewById(R.id.tabs);
//                tabLayout.setupWithViewPager(viewPager);
//
////                log
//                Log.d("location",locationResult.getLastLocation().getLatitude()+"/"+locationResult.getLastLocation().getLongitude());
//
//            }
//        };
//
//    }
//
//    private void setupViewPager(ViewPager viewPager) {
//        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(TodayWeatherFragmentNew.getInstance(),"Today");
////        adapter.addFragment(fragment_forecast_weather.getInstance(),"5 Days");
//        adapter.addFragment(CityFragment.getInstance(),"Cities");
//        viewPager.setAdapter(adapter);
//
//    }
//
//    private void buildLocationRequest() {
//        locationRequest = new LocationRequest();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(5000);
//        locationRequest.setFastestInterval(3000);
//        locationRequest.setSmallestDisplacement(10.0f);
//
//    }
//}
