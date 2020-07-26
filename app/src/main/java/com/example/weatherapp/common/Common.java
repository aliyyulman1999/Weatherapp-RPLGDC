package com.example.weatherapp.common;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String APP_ID = "832b3901bf48bd4cbb87d29ff511f5b1";
    public static Location current_location = null;

    public static String convertUnixToDate(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MM-yyyy HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }

    public static String convertUnixToDateDay(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String formatted = sdf.format(date);
        return formatted;
    }

    public static String convertUnixToDateHour(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }
}
