package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.model.employee;

import com.yukon.absenceplanner.mobile.android.api.RequestManager;
import com.yukon.absenceplanner.mobile.android.api.RequestMethod;
import com.yukon.absenceplanner.mobile.android.enteties.Employees;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ruslan on 20.04.2016.
 */
public class EmployeeInfoPersonsFragmentModelImpl implements EmployeeInfoPersonFragmentModel, Callback<JsonElement> {

    private OnLoadEmployeeInfoPersonsFragmentModelFinished
                onLoadEmployeeInfoPersonsFragmentModelFinished;

    private RequestManager requestManager =new RequestManager();

    @Override
    public void getEmployees() {
        requestManager.send(RequestMethod.GET_EMPLOYEE,null,this);
    }

    @Override
    public void setCallback(OnLoadEmployeeInfoPersonsFragmentModelFinished onLoadEmployeeInfoPersonsFragmentModelFinished) {
        this.onLoadEmployeeInfoPersonsFragmentModelFinished=onLoadEmployeeInfoPersonsFragmentModelFinished;
    }

    @Override
    public void success(JsonElement jsonElement, Response response) {
        Type employees = new TypeToken<Employees>() {}.getType();
        Employees employee = new Gson().fromJson(jsonElement, employees);
        onLoadEmployeeInfoPersonsFragmentModelFinished.onSuccess(employee);
    }

    @Override
    public void failure(RetrofitError error) {

    }
}
