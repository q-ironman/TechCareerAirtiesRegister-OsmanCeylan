package com.example.osmanceylan.retrofit.request;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IDailyServiceRequest {
    //CREATE
    @POST("/api/daily/v1/create")
    Call<JsonElement> save(@Body JsonElement element);
    //LIST
    @GET("api/daily/v1/list")
    Call<List<JsonElement>> list();
    //FIND
    @GET("api/daily/v1/find/{id}")
    Call<JsonElement> find(@Path("id")Long id);
    //UPDATE
    @PUT("api/daily/v1/update/{id}")
    Call<JsonElement> update(@Path("id") Long id, @Body JsonElement element);
    //DELETE
    @DELETE("api/daily/v1/delete/{id}")
    Call<Void> delete(@Path("id")Long id);
}
