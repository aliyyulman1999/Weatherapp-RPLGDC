package com.example.weatherapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
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
 */
public class TodayWeatherFragment extends Fragment {

    TextView text_temp,text_city,text_humid,text_speed,text_deg,text_pressure,text_date_time,text_geo,text_sunrise,text_sunset,text_description;
    AbsoluteLayout weather_panel;

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    static TodayWeatherFragment instance;

    public static TodayWeatherFragment getInstance() {
        if(instance == null){
            instance = new TodayWeatherFragment();
        }
        return instance;
    }

    public TodayWeatherFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new RetrofitClient().getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView =  inflater.inflate(R.layout.fragment_today_weather, container, false);
        text_city = (TextView)itemView.findViewById(R.id.text_city);
        text_temp = (TextView)itemView.findViewById(R.id.text_temp);
        text_deg = (TextView)itemView.findViewById(R.id.text_deg);
        text_geo = (TextView)itemView.findViewById(R.id.text_geo);
        text_pressure = (TextView)itemView.findViewById(R.id.text_pressure);
        text_speed = (TextView)itemView.findViewById(R.id.text_speed);
        text_humid = (TextView)itemView.findViewById(R.id.text_humid);
//        text_sunrise = (TextView)itemView.findViewById(R.id.text_sunrise);
//        text_sunset = (TextView)itemView.findViewById(R.id.text_sunset);
        text_description = (TextView)itemView.findViewById(R.id.text_description);
        text_date_time = (TextView)itemView.findViewById(R.id.text_date_time);

        weather_panel = itemView.findViewById(R.id.absolute_panel);

        getWeatherInformation();

        return itemView;
    }

    private void getWeatherInformation() {
        compositeDisposable.add(mService.getWeatherByLatLng(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        text_city.setText(weatherResult.getName());
                        text_temp.setText(new StringBuilder(
                                String.valueOf(weatherResult.getMain().getTemp())).append("C").toString());
                        text_geo.setText(new StringBuilder("").append(weatherResult.getCoord().toString()).append("").toString());
                        text_humid.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append("%").toString());
                        text_pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append("hpa").toString());
                        text_speed.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getSpeed())).append("kmh").toString());
                        text_deg.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getDeg())).append("deg").toString());
//                        text_sunrise.setText(new StringBuilder(Common.convertUnixToDate(weatherResult.getSys().getSunrise())));
//                        text_sunset.setText(new StringBuilder(Common.convertUnixToDate(weatherResult.getSys().getSunset())));
                        text_description.setText(new StringBuilder(String.valueOf(weatherResult.getWeather().get(0).getDescription())));
                        text_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherResult.getDt())));

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
