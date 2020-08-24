//package com.example.weatherapp.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.weatherapp.R;
//import com.example.weatherapp.common.Common;
//import com.example.weatherapp.model.WeatherForecastResult;
//
//public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {
//    Context context;
//    WeatherForecastResult  weatherForecastResult;
//
//    public WeatherForecastAdapter(Context context, WeatherForecastResult weatherForecastResult) {
//        this.context = context;
//        this.weatherForecastResult = weatherForecastResult;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(context).inflate(R.layout.item_weather_forecast,parent,false);
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.text_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherForecastResult.list.get(position)
//                .dt)));
//        holder.text_description.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position).weather.get(position).getDescription())));
//        holder.text_pressure.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position).main.getPressure())));
//        holder.text_temp.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position).main.getTemp())));
//    }
//
//    @Override
//    public int getItemCount() {
//        return weatherForecastResult.list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView text_date_time,text_temp,text_pressure,text_city,text_description;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            text_temp = (TextView)itemView.findViewById(R.id.text_temp);
//            text_pressure = (TextView)itemView.findViewById(R.id.text_pressure);
//            text_date_time = (TextView)itemView.findViewById(R.id.text_date_time);
//            text_description = (TextView)itemView.findViewById(R.id.text_description);
//        }
//    }
//}
