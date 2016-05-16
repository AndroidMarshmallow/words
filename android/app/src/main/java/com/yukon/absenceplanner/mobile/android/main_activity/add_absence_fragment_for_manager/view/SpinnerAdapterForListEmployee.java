package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yukon.absenceplanner.mobile.android.R;

import java.util.List;

/**
 * Created by ruslan on 14.04.2016.
 */
public class SpinnerAdapterForListEmployee extends ArrayAdapter {
    public List<String> objects;
    public SpinnerAdapterForListEmployee(Context context, int textViewResourceId,
                                         List<String> objects) {
        super(context, textViewResourceId, objects);
        this.objects=objects;
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)  getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_spinner, parent, false);

        TextView tvLanguage = (TextView) layout
                .findViewById(R.id.tvLanguage);
        tvLanguage.setText(objects.get(position));
        tvLanguage.setTextColor(Color.rgb(75, 180, 225));
        return layout;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}