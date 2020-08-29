package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    SearchView mySearchView;
    ListView myList;
    String searchCityName;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        mySearchView = (SearchView)findViewById(R.id.searchView);
        myList = (ListView) findViewById(R.id.myList);

        list = new ArrayList<String>();

        list.add("Denpasar");
        list.add("Jakarta");
        list.add("Semarang");
        list.add("Medan");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                Intent newCity = new Intent(getApplicationContext(),SwipeActivity2.class);
                // Create a new Intent as container for the result
                final Intent data = new Intent();

                // Add the required data to be returned to the MainActivity
                 data.putExtra("EXTRA_DATA",mySearchView.getQuery().toString() );

                    // Set the resultCode to Activity.RESULT_OK to
                    // indicate a success and attach the Intent
                    // which contains our result data
                setResult(1, data);
                finish();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}