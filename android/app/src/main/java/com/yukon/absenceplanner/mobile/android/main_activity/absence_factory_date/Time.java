package com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yukon.absenceplanner.mobile.android.R;

import java.util.Calendar;

/**
 * Created by Pasha Laptev on 5/15/2016.
 */
@SuppressLint("ValidFragment")
public class Time extends DialogFragment implements DateAndTime, TimePickerDialog.OnTimeSetListener{


    private String button;
    private int hour;
    private int minute;

    public Time(String button){
        this.button = button;
    }
    public Time(){}



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        getCurrent();
        return new TimePickerDialog(getActivity(),this,hour,minute, DateFormat.is24HourFormat(getActivity()));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        TextView textStartViewTime = (TextView) getActivity().findViewById(R.id.startTimeTextEdit);
        TextView textEndViewTime = (TextView) getActivity().findViewById(R.id.endTimeTextEdit);

        if(button.equals("startButton"))
            textStartViewTime.append(" "+parserNaNull(hourOfDay)+":"+parserNaNull(minute));
        else textEndViewTime.append(" "+parserNaNull(hourOfDay)+":"+parserNaNull(minute));

    }


    @Override
    public String getCurrent() {

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        return " "+parserNaNull(hour)+":"+parserNaNull(minute);
    }

    @Override
    public String parserNaNull(Integer value)
    {
        if(value<=9){
            return "0"+value;
        } else {
            return String.valueOf(value);
        }

    }


}
