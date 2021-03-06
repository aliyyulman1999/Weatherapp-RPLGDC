package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ContactUsActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickMenu (View view){
        //Open drawer
        MenuActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        MenuActivity.closeDrawer(drawerLayout);
    }
    public void ClickUnit(View view){
        MenuActivity.redirectActivity(this,MenuActivity.class);

    }

    public void ClickContactUs(View view){
        recreate();
    }

    public void ClickProfil(View view){
        //redirect activity to dashboard
        MenuActivity.redirectActivity(this, ProfilActivity.class);
    }
    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();;
        //Close drawer
        MenuActivity.closeDrawer(drawerLayout);

    }
}