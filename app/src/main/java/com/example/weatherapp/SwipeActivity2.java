package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.weatherapp.adapter.viewPagerAdapter2;
import com.example.weatherapp.common.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class SwipeActivity2 extends AppCompatActivity {

    private ViewPager viewPager;
    private RelativeLayout relativeLayout;
    private Integer[] colors = null;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private viewPagerAdapter2 adapter;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_swipe);
        relativeLayout = (RelativeLayout) findViewById(R.id.root_view);
        add_btn = (Button)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(search, 1);
            }
        });

        //request permission
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()){
                            buildLocationRequest();
                            buildLocationCallBack();
                            if (ActivityCompat.checkSelfPermission(SwipeActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                    ActivityCompat.checkSelfPermission(SwipeActivity2.this,
                                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(SwipeActivity2.this);
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Snackbar.make(relativeLayout,"Permission Denied", Snackbar.LENGTH_LONG).show();
                    }
                }).check();

        // color
        Integer[] colors_temp = {
//                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color2),
//                getResources().getColor(R.color.color4)
        };
        colors = colors_temp;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(resultCode == 1) {
                // Get the result from the returned Intent
                String cityName = data.getStringExtra("EXTRA_DATA");
                addItemSwipe(cityName);
                Log.e("newcity added :",cityName);

            }
//
        }
    }

    private void buildLocationCallBack() {
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult){
                super.onLocationResult(locationResult);
                if (locationResult == null){
                    return;
                }
                else {
                    Common.current_location = locationResult.getLastLocation();
                }


                viewPager = (ViewPager)findViewById(R.id.viewPager);
                viewPager.setOffscreenPageLimit(5);
                setupViewPager(viewPager);
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if (position<(adapter.getCount()-1)&& position<(colors.length-1)) {
                            viewPager.setBackgroundColor(
                                    (Integer) argbEvaluator.evaluate(
                                            positionOffset,
                                            colors[position],
                                            colors[position + 1]
                                    )
                            );
                        }else {
                            viewPager.setBackgroundColor(colors[colors.length-1]);

                        }
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });


//                log
                Log.d("location",locationResult.getLastLocation().getLatitude()+"/"+locationResult.getLastLocation().getLongitude());

            }
        };

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new viewPagerAdapter2(getSupportFragmentManager());
        adapter.addFragment(ItemSwipeToday.getInstance());
        addItemSwipe("Helsinki");
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

    }

    private void addItemSwipe(String cityName){
        adapter.addFragment(ItemSwipeCity.getInstance(cityName));
        adapter.notifyDataSetChanged();
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);

    }
}