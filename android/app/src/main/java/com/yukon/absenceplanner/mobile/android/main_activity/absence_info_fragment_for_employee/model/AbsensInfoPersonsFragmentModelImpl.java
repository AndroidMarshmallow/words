package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.model;

import com.yukon.absenceplanner.mobile.android.api.RequestManager;
import com.yukon.absenceplanner.mobile.android.api.RequestMethod;
import com.yukon.absenceplanner.mobile.android.api.RequestValues;
import com.yukon.absenceplanner.mobile.android.enteties.Absences;
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
public class AbsensInfoPersonsFragmentModelImpl implements AbsensInfoPersonsFragmentModel,
        Callback<JsonElement> {

    private OnLoadAbsensInfoPersonsFragmentModelFinished
            mOnLoadAbsensInfoPersonsFragmentModelFinished;
    private RequestManager requestManager =new RequestManager();

    private Map<String, String> options = new HashMap<>();

    @Override
    public void getAbsence(String timestamp) {
        if(timestamp=="") {
            options.put(RequestValues.TIMESTAMP, null);
            RequestManager.send(RequestMethod.GET_PERSON_INFO, options, this);
        } else {
            options.put(RequestValues.TIMESTAMP, timestamp);
            RequestManager.send(RequestMethod.GET_PERSON_INFO, options, this);
        }
    }

    @Override
    public void setCallback(OnLoadAbsensInfoPersonsFragmentModelFinished
                                    onLoadAbsensInfoPersonsFragmentModelFinished) {
        mOnLoadAbsensInfoPersonsFragmentModelFinished = onLoadAbsensInfoPersonsFragmentModelFinished;
    }

    @Override
    public void canceledAbsence(int valueId) {
        options.put(RequestValues.ID,
                    String.valueOf(valueId));
        options.put(RequestValues.STATUS,
                    null);
        requestManager.send(RequestMethod.CANCELED_ABSENCE, options,this);
    }

    @Override
    public void success(JsonElement jsonElement, Response response) {
        Type absences = new TypeToken<Absences>() {}.getType();
        Absences absence = new Gson().fromJson(jsonElement, absences);
        mOnLoadAbsensInfoPersonsFragmentModelFinished.onSuccess(absence);
    }

    @Override
    public void failure(RetrofitError error) {
        mOnLoadAbsensInfoPersonsFragmentModelFinished.onFailure();
    }
}
