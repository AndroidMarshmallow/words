package com.yukon.absenceplanner.mobile.android.api;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * @author jordan on 12.12.15.
 */
public interface RestApi {

    @GET("/mobile/v1/absences")
    void getAbsences(@Query("timestamp") String timestamp, Callback<JsonElement> responseCallback);

    @GET("/mobile/v1/employees/current")
    void getEmployee(Callback<JsonElement> responseCallback);

    @GET("/v1/types")
    void login(Callback<JsonElement> callback);

    @FormUrlEncoded
    @POST("/common/v1/absences")
    void addAbsences(@Field("absenceTypeId")String absenceTypeId,
                     @Field("startDateTime")String startDateTime,
                     @Field("endDateTime")String endDateTime,
                     @Field("note") String note, Callback<JsonElement> jsonElementCallback);

    @FormUrlEncoded
    @PUT("/common/v1/absences/{id}?status=CANCELED")
    void canceledAbsences(@Path("id") int id,@Field("status") String status ,
                          Callback<JsonElement> jsonElementCallback);

    @PUT("/common/v1/absences")
    void updateAbsences(@Field("statusId") int statusId, Callback<JsonElement> jsonElementCallback);
}
