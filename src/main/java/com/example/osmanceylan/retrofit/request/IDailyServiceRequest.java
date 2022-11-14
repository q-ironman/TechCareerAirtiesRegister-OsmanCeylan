package com.example.osmanceylan.retrofit.request;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface IDailyServiceRequest {
    //CREATE
    @POST("/api/daily/v1/create")
    Call<JsonElement> save(JsonElement element);
    //LIST
    @GET("api/daily/v1/list")
    Call<List<JsonElement>> list();
    //DELETE
    @DELETE("api/daily/v1/list/{id}")
    Call<Void> delete(@Path("id")Long id);
}
