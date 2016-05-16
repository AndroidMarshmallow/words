package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_employee.model;

import com.yukon.absenceplanner.mobile.android.api.RequestManager;
import com.yukon.absenceplanner.mobile.android.api.RequestMethod;
import com.yukon.absenceplanner.mobile.android.api.RequestValues;
import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ruslan on 01.04.2016.
 */
public class AddAbsenceFragmentModelImpl implements AddAbsenceFragmentModel, Callback<JsonElement> {

    private OnLoadAddAbsenceFragmentModelFinished
            monLoadAddAbsenceFragmentModelFinished;
    private Map<String , String> options = new HashMap<>();
    private RequestManager requestManager =new RequestManager();

    @Override
    public void getAbsence() {
        RequestManager.sRestApi.login(this);
    }

    @Override
    public void setCallback(OnLoadAddAbsenceFragmentModelFinished
                                    onLoadAddAbsenceFragmentModelFinished) {
        monLoadAddAbsenceFragmentModelFinished=onLoadAddAbsenceFragmentModelFinished;
    }

    @Override
    public void addAbsence(String spinner, String startDate, String endDate,String note) {
        Absence myAbsence = new Absence(spinner,startDate,endDate);
        options.put(RequestValues.ABSENCE_TYPE_ID,
               new String(myAbsence.getAbsenceTypeName()));
               new String(myAbsence.getAbsenceTypeName());
        options.put(RequestValues.START_DATA_TIME,
               new String(myAbsence.getStartDateTime()));
        options.put(RequestValues.END_DATA_TIME,
               new String(myAbsence.getEndDateTime()));
        options.put(RequestValues.NOTE,
               new String(note));
        requestManager.send(RequestMethod.ADD_NEW_ABSENCE,options,this);
    }

    @Override
    public void success(JsonElement jsonElement, Response response) {
        Type absenceCollection = new TypeToken<Absence>(){}.getType();
        Absence absence = new Gson().fromJson(jsonElement ,absenceCollection);
        Type absenceCollectionType = new TypeToken<AbsenceTypes>(){}.getType();
        AbsenceTypes types = new Gson().fromJson(jsonElement ,absenceCollectionType);
        monLoadAddAbsenceFragmentModelFinished.onSuccess(types ,absence);
    }

    @Override
    public void failure(RetrofitError error) {
        monLoadAddAbsenceFragmentModelFinished.onFailure();
    }
}
