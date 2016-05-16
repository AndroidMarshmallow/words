package com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Pasha Laptev on 5/15/2016.
 */
public class DateFactory  {

    // Create Date and Time Picker for both Date and end Date buttons ☺
    public void getDateAndTimePicker(String button, String dateOrTime, FragmentManager fragmentManager){

        if(dateOrTime.equals("DatePicker")) new Date(button).show(fragmentManager,dateOrTime);
        if(dateOrTime.equals("TimePicker")) new Time(button).show(fragmentManager,dateOrTime);

    }

    public DateAndTime getCurrentDateAndTime (String dateOrTime){

        if(dateOrTime.equals("Date"))
            return new Date();
        else return new Time();

    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateFormat(String time) throws ParseException {
        SimpleDateFormat dateFormatStart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        java.util.Date convertedDateStart = dateFormatStart.parse(time);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm");
        return dateFormat.format(convertedDateStart);
    }


}