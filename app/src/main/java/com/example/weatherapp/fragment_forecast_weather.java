//package com.example.weatherapp;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.weatherapp.adapter.WeatherForecastAdapter;
//import com.example.weatherapp.common.Common;
//import com.example.weatherapp.model.WeatherForecastResult;
//import com.example.weatherapp.model.WeatherResult;
//import com.example.weatherapp.retrofit.IOpenWeatherMap;
//import com.example.weatherapp.retrofit.RetrofitClient;
//
//import io.reactivex.Scheduler;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.functions.Consumer;
//import io.reactivex.schedulers.Schedulers;
//import retrofit2.Retrofit;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class fragment_forecast_weather extends Fragment {
//
//    CompositeDisposable compositeDisposable;
//    IOpenWeatherMap mService;
//
//    TextView text_city,text_geo;
//    RecyclerView recycler_forecast;
//
//    static fragment_forecast_weather instance;
//
//    public static fragment_forecast_weather getInstance() {
//        if (instance == null){
//            instance = new fragment_forecast_weather();
//        }
//        return instance;
//    }
//
//    public fragment_forecast_weather() {
//        // Required empty public constructor
//        compositeDisposable =  new CompositeDisposable();
//        Retrofit retrofit = RetrofitClient.getInstance();
//        mService = retrofit.create(IOpenWeatherMap.class);
//
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View itemView = inflater.inflate(R.layout.fragment_forecast_weather,container,false);
//
//        text_city = (TextView)itemView.findViewById(R.id.text_city);
//        text_geo = (TextView)itemView.findViewById(R.id.text_geo);
//
//        recycler_forecast = (RecyclerView)itemView.findViewById(R.id.recycle_forecast);
//        recycler_forecast.setHasFixedSize(true);
//        recycler_forecast.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//
//        getForecastWeatherInformation();
//        return itemView;
//    }
//
//    @Override
//    public void onStop() {
//        compositeDisposable.clear();
//        super.onStop();
//    }
//
//    private void getForecastWeatherInformation() {
//        compositeDisposable.add(mService.getForecastWeatherByLatLng(
//                String.valueOf(Common.current_location.getLatitude()),
//                String.valueOf(Common.current_location.getLongitude()),
//                Common.APP_ID,
//                "metric")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<WeatherForecastResult>() {
//                    @Override
//                    public void accept(WeatherForecastResult weatherForecastResult) throws Exception {
//                        displayForecastWeather(weatherForecastResult);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.d("ERROR",""+throwable.getMessage());
//                    }
//                })
//
//        );
//    }
//
//    private void displayForecastWeather(WeatherForecastResult weatherForecastResult) {
//        text_city.setText(new StringBuilder(weatherForecastResult.city.name));
//        text_geo.setText(new StringBuilder(weatherForecastResult.city.coord.toString()));
//
//        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getContext(),weatherForecastResult);
//        recycler_forecast.setAdapter(adapter);
//    }
//}
