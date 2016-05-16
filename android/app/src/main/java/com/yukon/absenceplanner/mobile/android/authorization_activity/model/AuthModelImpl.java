package com.yukon.absenceplanner.mobile.android.authorization_activity.model;

import com.yukon.absenceplanner.mobile.android.api.RequestManager;
import com.yukon.absenceplanner.mobile.android.api.RequestMethod;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;



import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class AuthModelImpl implements AuthModel, Callback<JsonElement> {

    private OnAuthFinished mOnAuthFinished;

    @Override
    public void authin(String user, String pass) {
        final String basic = RequestManager.toBasic64(user, pass);
        RequestManager.build(basic);
        RequestManager.send(RequestMethod.GET_ABSENCE_TYPE, null, this);
    }

    @Override
    public void setCallback(OnAuthFinished onAuthFinished) {
        mOnAuthFinished = onAuthFinished;
    }

    @Override
    public void success(JsonElement response, Response response2) {
        Type absenceCollectionType = new TypeToken<AbsenceTypes>(){}.getType();
        AbsenceTypes types = new Gson().fromJson(response ,absenceCollectionType);

        mOnAuthFinished.onSuccess(types);
    }

    @Override
    public void failure(RetrofitError error) {
        mOnAuthFinished.onFailure();
    }
}
