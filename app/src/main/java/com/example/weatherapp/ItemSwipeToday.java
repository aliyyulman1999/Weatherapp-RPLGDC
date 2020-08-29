package com.example.weatherapp;

import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemSwipeToday#getInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemSwipeToday extends Fragment {
    private TextView text_temp,text_city,text_humid,text_speed,text_date_time,text_sunrise,text_sunset,text_description;
    private LinearLayout weather_panel;
    private Button units_botton;
    private String units = "metric";

    private CompositeDisposable compositeDisposable;
    private IOpenWeatherMap mService;

    static ItemSwipeToday instance;


    public static ItemSwipeToday getInstance() {
        if(instance == null){
            instance = new ItemSwipeToday();
        }
        return instance;
    }


    public ItemSwipeToday() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new RetrofitClient().getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View itemView =  inflater.inflate(R.layout.fragment_item_swipe_today, container, false);
        text_city = (TextView)itemView.findViewById(R.id.text_city);
        text_temp = (TextView)itemView.findViewById(R.id.text_temp);
        //background
////        text_deg = (TextView)itemView.findViewById(R.id.text_deg);
////        text_pressure = (TextView)itemView.findViewById(R.id.text_pressure);
//        text_speed = (TextView)itemView.findViewById(R.id.text_speed);
//        text_humid = (TextView)itemView.findViewById(R.id.text_humid);
        text_sunrise = (TextView)itemView.findViewById(R.id.text_sunrise);
//        text_sunset = (TextView)itemView.findViewById(R.id.text_sunset);
//        text_description = (TextView)itemView.findViewById(R.id.text_description);
        text_date_time = (TextView)itemView.findViewById(R.id.text_date_time);
        units_botton =itemView.findViewById(R.id.btnAdd);
        units_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (units.equalsIgnoreCase("metric")){
                    units = "imperial";
                    units_botton.setText("Change Units To Metric");
                }
                else {
                    units = "metric";
                    units_botton.setText("Change Units To Imperial");
                }
                getWeatherInformation();
            }});
//
        weather_panel = itemView.findViewById(R.id.weather_panel);
//
        getWeatherInformation();

        return itemView;
    }

    private void getWeatherInformation() {
        compositeDisposable.add(mService.getWeatherByLatLng(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                units)
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
                                text_sunrise.setText(new StringBuilder(Common.convertUnixToDateHour(weatherResult.getSys().getSunrise())).append(" LT"));
//                                text_sunset.setText(new StringBuilder(Common.convertUnixToDateHour(weatherResult.getSys().getSunset())).append(" LT"));
//                                text_description.setText(new StringBuilder(String.valueOf(weatherResult.getWeather().get(0).getDescription())));
                                text_date_time.setText(new StringBuilder(Common.convertUnixToDateDay(weatherResult.getDt())));

//                      display panel
                                weather_panel.setVisibility(View.VISIBLE);


                            }
                        }, new Consumer<Throwable>(){
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getActivity(),""+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        })
        );
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}