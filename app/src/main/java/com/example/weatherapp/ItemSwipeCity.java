package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.common.Common;
import com.example.weatherapp.model.WeatherResult;
import com.example.weatherapp.retrofit.IOpenWeatherMap;
import com.example.weatherapp.retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemSwipeCity#getInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemSwipeCity extends Fragment {
    private TextView text_temp,text_city,text_humid,text_speed,text_date_time,text_sunrise,text_sunset,text_description;
    private LinearLayout weather_panel;
    private Button add_botton;
    private String units = "metric";
    static String cityName;
//    private static final int req_code = 1;

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    static ItemSwipeCity instance;


    int res_code = 1;

    public static ItemSwipeCity getInstance(String cName) {
        instance = new ItemSwipeCity();
//        Bundle args = new Bundle();
//        args.putString("cityName",cName);
        cityName = cName;
        return instance;
    }


    public ItemSwipeCity() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new RetrofitClient().getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);

    }

    private String getCityName(){return cityName;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View itemView =  inflater.inflate(R.layout.fragment_item_swipe_city, container, false);
        text_city = (TextView)itemView.findViewById(R.id.text_city);
        text_temp = (TextView)itemView.findViewById(R.id.text_temp);
////        text_deg = (TextView)itemView.findViewById(R.id.text_deg);
////        text_pressure = (TextView)itemView.findViewById(R.id.text_pressure);
//        text_speed = (TextView)itemView.findViewById(R.id.text_speed);
//        text_humid = (TextView)itemView.findViewById(R.id.text_humid);
//        text_sunrise = (TextView)itemView.findViewById(R.id.text_sunrise);
//        text_sunset = (TextView)itemView.findViewById(R.id.text_sunset);
        text_description = (TextView)itemView.findViewById(R.id.text_description);
        text_date_time = (TextView)itemView.findViewById(R.id.text_date_time);

        weather_panel = itemView.findViewById(R.id.weather_panel);
        getWeatherInformation(cityName);

        return itemView;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // First we need to check if the requestCode matches the one we used.
//        if(requestCode == req_code) {
//
//                    // The resultCode is set by the AnotherActivity
//                    // By convention RESULT_OK means that what ever
//                    // AnotherActivity did was successful
//                    if(resultCode == res_code) {
//                        // Get the result from the returned Intent
//                       cityName = data.getStringExtra("EXTRA_DATA");
//                        Log.e("newcity",cityName);
//
//                        // Use the data - in this case, display it in a Toast.
//
////                        Toast.makeText(this, cityName + " city added", Toast.LENGTH_LONG).show();
//                    }
////                    else {
////                        // AnotherActivity was not successful. No data to retrieve.
////                    }
//        }
//    }


    private void getWeatherInformation(String cityName) {
        compositeDisposable.add(mService.getWeatherByCityName(cityName,
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        text_city.setText(weatherResult.getName());
                        if (units.equalsIgnoreCase("metric")){
                            text_temp.setText(new StringBuilder(
                                    String.valueOf(Math.round(weatherResult.getMain().getTemp()))).append("\u2103").toString());
//                                    text_speed.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getSpeed())).append(" m/s").toString());
                        }
                        else {
                            text_temp.setText(new StringBuilder(
                                    String.valueOf(Math.round(weatherResult.getMain().getTemp()))).append("\u2109").toString());
//                                    text_speed.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getSpeed())).append(" Mph").toString());
                        }

//                                text_geo.setText(new StringBuilder("").append(weatherResult.getCoord().toString()).append("").toString());
//                                text_humid.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append("%").toString());
//                                text_pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append("hpa").toString());

//                                text_deg.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getDeg())).append("deg").toString());
//                        text_sunrise.setText(new StringBuilder(Common.convertUnixToDateHour(weatherResult.getSys().getSunrise())).append(" LT"));
//                                text_sunset.setText(new StringBuilder(Common.convertUnixToDateHour(weatherResult.getSys().getSunset())).append(" LT"));
                        text_description.setText(new StringBuilder(String.valueOf(weatherResult.getWeather().get(0).getDescription())));
                        text_date_time.setText(new StringBuilder(Common.convertUnixToDateDay(weatherResult.getDt())));

//                      display panel
                        weather_panel.setVisibility(View.VISIBLE);
                    }
                }));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}