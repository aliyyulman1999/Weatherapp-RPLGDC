package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.weatherapp.adapter.ExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }
  
    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Manage Account");
        listDataHeader.add("Contact Us");
        listDataHeader.add("Unit Setting");
        listDataHeader.add("Manage the Weather");

        List<String> edmtDev = new ArrayList<>();
        edmtDev.add("Management accounts are usually prepared on a regular and consistent basis to ensure a business owner or management team are getting the most out of monitoring their efforts. There is no set rule for this but typically they're produced monthly, or quarterly.\n" +
                "\n" +
                "Remember your reports will be most efficient when tailored to your business. That means covering what's most important to you as the owner manager. The below infographic is designed to inform and get you thinking, it's not a definitive format. ");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("This is where I would go hoping to find that [contact] information. It is misleading to have this page called Contact Us and then the content on the page is just more paragraphs. They don’t seem to be offering other methods of contact including social media, email, or a phone number. If I go to Contact Us, I’m already expecting the variety of methods of contact.");

        List<String> xamarin = new ArrayList<>();
        xamarin.add("Unit Setting: Selecting the Unit for Values. Select the unit of measurement for numeric values to be entered or displayed on the touch panel.");

        List<String> uwp = new ArrayList<>();
        uwp.add("Manage Weather Forecast. Providing a local hourly Manage weather forecast of rain, sun, wind, humidity and temperature.");

        listHash.put(listDataHeader.get(0),edmtDev);
        listHash.put(listDataHeader.get(1),androidStudio);
        listHash.put(listDataHeader.get(2),xamarin);
        listHash.put(listDataHeader.get(3),uwp);
    }
}