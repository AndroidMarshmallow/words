package com.yukon.absenceplanner.mobile.android.api;

import android.util.Base64;

import com.yukon.absenceplanner.mobile.android.Values;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * @author jordan on 13.02.16.
 */
public class RequestManager {

    public static RestApi sRestApi;

    private static RestAdapter.Builder sBuilder = new RestAdapter.Builder()
            .setEndpoint(Values.LINK)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setClient(new OkClient(new OkHttpClient()))
            .setConverter(new GsonConverter(new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create())
            );


    public static void build(final String basic){
        sBuilder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Authorization", basic);
                request.addHeader("Accept", "application/json");
            }
        });
        RestAdapter restAdapter = sBuilder.build();
        sRestApi = restAdapter.create(RestApi.class);
    }

    public static String toBasic64(String userName, String userPass){
        String credentials = userName + ":" + userPass;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        return basic;
    }

    public static void send(RequestMethod requestMethod,
                            Map<String,
                            String> options,
                            Callback<JsonElement> callback) {
        switch (requestMethod) {
            case GET_ABSENCE_TYPE:
                sRestApi.login(callback);
                break;
            case CANCELED_ABSENCE:
                sRestApi.canceledAbsences(Integer.parseInt(options.get(RequestValues.ID)),
                                          options.get(RequestValues.STATUS),
                                          callback);
                break;
            case GET_PERSON_INFO:
                sRestApi.getAbsences(options.get(RequestValues.TIMESTAMP),callback);
                break;
            case ADD_NEW_ABSENCE:
                sRestApi.addAbsences(options.get(RequestValues.ABSENCE_TYPE_ID),
                                     options.get(RequestValues.START_DATA_TIME),
                                     options.get(RequestValues.END_DATA_TIME),
                                     options.get(RequestValues.NOTE),
                                     callback);
                break;
            case GET_EMPLOYEE:
                sRestApi.getEmployee(callback);
            default:
                break;
        }

    }
}
