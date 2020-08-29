// package com.example.weatherapp;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsoluteLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.weatherapp.common.Common;
//import com.example.weatherapp.model.WeatherResult;
//import com.example.weatherapp.retrofit.IOpenWeatherMap;
//import com.example.weatherapp.retrofit.RetrofitClient;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.label305.asynctask.SimpleAsyncTask;
//import com.mancj.materialsearchbar.MaterialSearchBar;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.zip.GZIPInputStream;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.functions.Consumer;
//import io.reactivex.schedulers.Schedulers;
//import retrofit2.Retrofit;
//
//
// /**
// * A simple {@link Fragment} subclass.
// */
//public class CityFragment extends Fragment {
//    private List<String> lstCities;
//    private MaterialSearchBar searchBar;
//
//     TextView text_temp,text_city,text_humid,text_speed,text_deg,text_pressure,text_date_time,text_geo,text_sunrise,text_sunset,text_description;
//     LinearLayout city_fragment;
//     AbsoluteLayout city_weather;
//
//     CompositeDisposable compositeDisposable;
//     IOpenWeatherMap mService;
//
//     static CityFragment instance;
//
//     public static CityFragment getInstance() {
//         if(instance == null){
//             instance = new CityFragment();
//         }
//         return instance;
//     }
//
//    public CityFragment() {
//        compositeDisposable = new CompositeDisposable();
//        Retrofit retrofit = new RetrofitClient().getInstance();
//        mService = retrofit.create(IOpenWeatherMap.class);
//
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment
//        View itemView = inflater.inflate(R.layout.fragment_city, container, false);
//        text_city = (TextView)itemView.findViewById(R.id.text_city);
//        text_temp = (TextView)itemView.findViewById(R.id.text_temp);
//        text_deg = (TextView)itemView.findViewById(R.id.text_deg);
//        text_geo = (TextView)itemView.findViewById(R.id.text_geo);
//        text_pressure = (TextView)itemView.findViewById(R.id.text_pressure);
//        text_speed = (TextView)itemView.findViewById(R.id.text_speed);
//        text_humid = (TextView)itemView.findViewById(R.id.text_humid);
////        text_sunrise = (TextView)itemView.findViewById(R.id.text_sunrise);
////        text_sunset = (TextView)itemView.findViewById(R.id.text_sunset);
//        text_description = (TextView)itemView.findViewById(R.id.text_description);
//        text_date_time = (TextView)itemView.findViewById(R.id.text_date_time);
//
//        city_fragment = itemView.findViewById(R.id.city_fragment);
//        city_weather = itemView.findViewById(R.id.city_weather);
//
//        searchBar = (MaterialSearchBar)itemView.findViewById(R.id.searchBar);
//        searchBar.setEnabled(true);
//
//        city_weather.setVisibility(View.INVISIBLE);
//
//        new LoadCities().execute();
//
//        return itemView;
//    }
//
//     private class LoadCities extends SimpleAsyncTask<List<String>> {
//         @Override
//         protected List<String> doInBackgroundSimple() {
//             lstCities = new ArrayList<>();
//             try {
//                 StringBuilder builder = new StringBuilder();
//                 InputStream is = getResources().openRawResource(R.raw.city_list);
//                 GZIPInputStream gzipInputStream = new GZIPInputStream(is);
//
//                 InputStreamReader reader = new InputStreamReader(gzipInputStream);
//                 BufferedReader in = new BufferedReader(reader);
//
//                 String readed;
//                 while ((readed = in.readLine()) != null){
//                     builder.append(readed);
//                 }
//                 lstCities = new Gson().fromJson(builder.toString(),new TypeToken<List<String>>(){}.getType());
//
//             }catch (IOException e){
//                e.printStackTrace();
//             }
//             return lstCities;
//         }
//
//         @Override
//         protected void onSuccess(final List<String> listCity) {
//             super.onSuccess(listCity);
//
//             searchBar.setEnabled(true);
//             searchBar.addTextChangeListener(new TextWatcher() {
//                 @Override
//                 public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                 }
//
//                 @Override
//                 public void onTextChanged(CharSequence s, int start, int before, int count) {
////                     List<String> suggest = new ArrayList<>();
////                     for (String search: listCity){
////                         if (search.toLowerCase().contains(searchBar.getText().toLowerCase())){
////                             suggest.add(search);
////                         }
////                         searchBar.setLastSuggestions(suggest);
////                     }
//                 }
//
//                 @Override
//                 public void afterTextChanged(Editable s) {
//
//                 }
//             });
//             searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
//                 @Override
//                 public void onSearchStateChanged(boolean enabled) {
//
//                 }
//
//                 @Override
//                 public void onSearchConfirmed(CharSequence text) {
//                    getWeatherInformation(text.toString());
//                    searchBar.setLastSuggestions(listCity);
//                 }
//
//                 @Override
//                 public void onButtonClicked(int buttonCode) {
//
//                 }
//             });
//             city_weather.setVisibility(View.VISIBLE);
//         }
//     }
//
//     private void getWeatherInformation(String cityName) {
//         compositeDisposable.add(mService.getWeatherByCityName(cityName,
//                 Common.APP_ID,
//                 "metric")
//                 .subscribeOn(Schedulers.io())
//                 .observeOn(AndroidSchedulers.mainThread())
//                 .subscribe(new Consumer<WeatherResult>() {
//                     @Override
//                     public void accept(WeatherResult weatherResult) throws Exception {
//                         text_city.setText(weatherResult.getName());
//                         text_temp.setText(new StringBuilder(
//                                 String.valueOf(weatherResult.getMain().getTemp())).append("C").toString());
//                         text_geo.setText(new StringBuilder("").append(weatherResult.getCoord().toString()).append("").toString());
//                         text_humid.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append("%").toString());
//                         text_pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append("hpa").toString());
//                         text_speed.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getSpeed())).append("kmh").toString());
//                         text_deg.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getDeg())).append("deg").toString());
////                        text_sunrise.setText(new StringBuilder(Common.convertUnixToDate(weatherResult.getSys().getSunrise())));
////                        text_sunset.setText(new StringBuilder(Common.convertUnixToDate(weatherResult.getSys().getSunset())));
//                         text_description.setText(new StringBuilder(String.valueOf(weatherResult.getWeather().get(0).getDescription())));
//                         text_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherResult.getDt())));
//
////                      display panel
//                        city_weather.setVisibility(View.VISIBLE);
//                     }
//                 }));
//     }
//
//     @Override
//     public void onDestroy() {
//         compositeDisposable.clear();
//         super.onDestroy();
//     }
//
//     @Override
//     public void onStop() {
//         compositeDisposable.clear();
//         super.onStop();
//     }
// }