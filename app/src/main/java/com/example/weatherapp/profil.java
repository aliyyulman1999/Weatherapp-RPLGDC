package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class profil extends AppCompatActivity {
    private Button button;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        drawerLayout = findViewById(R.id.drawer_layout);

        button = (Button) findViewById(R.id.editprofil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profil.this, ProfilUpdate.class);
                startActivity(intent);
            }
        });

    }
    public void ClickMenu (View view){
        //Open drawer
        menuActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        menuActivity.closeDrawer(drawerLayout);
    }
    public void ClickUnit(View view){
        menuActivity.redirectActivity(this,menuActivity.class);
    }
    public void ClickContactUs(View view){
        menuActivity.redirectActivity(this,ContactUsActivity.class);
    }
    public void ClickProfil(View view){
        recreate();
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
        menuActivity.closeDrawer(drawerLayout);
    }
}
