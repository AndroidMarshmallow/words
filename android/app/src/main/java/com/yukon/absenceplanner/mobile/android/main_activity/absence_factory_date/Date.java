package com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import com.yukon.absenceplanner.mobile.android.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Pasha Laptev on 5/15/2016.
 */
@SuppressLint("ValidFragment")
public class Date extends DialogFragment implements DateAndTime,DatePickerDialog.OnDateSetListener  {


    private int year;
    private int month;
    private int day;
    private String button;
    private Calendar dateUserPick;
    private Calendar dateToday;

    public Date(String button){

        this.button = button;
    }

    public Date(){}



    public Dialog onCreateDialog(Bundle savedInstanceState) {

        getCurrent();
        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        checkBelowDate(year,month,day);

    }

    @SuppressLint("SetTextI18n")
    private void checkBelowDate(int year, int month, int day){
        if(button.equals("startButton"))
            dateUserPick = new GregorianCalendar(year, month, day);
        else      dateUserPick = new GregorianCalendar(year, month+1, day);

        TextView textStartViewTime = (TextView) getActivity().findViewById(R.id.startTimeTextEdit);
        TextView textEndViewTime = (TextView) getActivity().findViewById(R.id.endTimeTextEdit);

        if(button.equals("startButton")){
            dateToday = new GregorianCalendar(this.year, this.month, this.day);
            textEndViewTime.setText("");
        }
        else {

            String splitDate =""+ textStartViewTime.getText().toString().split("\\s+")[0];
            String[] partsOfDate = splitDate.split("\\.");

            dateToday = new GregorianCalendar(Integer.valueOf(partsOfDate[2]), Integer.valueOf(partsOfDate[1]), Integer.valueOf(partsOfDate[0]));}


        if((dateToday.compareTo(dateUserPick) == 1)){

            Snackbar snackbar = Snackbar
                    .make(getActivity().findViewById(R.id.absenceLayout), R.string.wrongDate, Snackbar.LENGTH_LONG);
            snackbar.show();

            if(button.equals("startButton"))
                textStartViewTime.setText("");
            else textEndViewTime.setText("");

        } else {

            new DateFactory().getDateAndTimePicker(button,"TimePicker",getFragmentManager());

            if(button.equals("startButton"))
                textStartViewTime.setText(parserNaNull(day)+"."+parserNaNull(month+1)+"."+parserNaNull(year));
            else textEndViewTime.setText(parserNaNull(day)+"."+parserNaNull(month+1)+"."+parserNaNull(year));
        }

    }


    @Override
    public String getCurrent() {

        final Calendar calendar = Calendar.getInstance();

        year  =   calendar.get(Calendar.YEAR);
        month =   calendar.get(Calendar.MONTH);
        day   =   calendar.get(Calendar.DAY_OF_MONTH);

        return parserNaNull(day)+"."+parserNaNull(month+1)+"."+parserNaNull(year);

    }

    @Override
    public String parserNaNull(Integer value) {
        if(value<=9){
            return "0"+value;
        } else {
            return String.valueOf(value);
        }
    }
}
