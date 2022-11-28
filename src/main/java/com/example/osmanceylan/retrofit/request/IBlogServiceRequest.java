package com.example.osmanceylan.retrofit.request;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IBlogServiceRequest {
    //CREATE
    @POST("/api/blog/v1/create")
    Call<JsonElement> save(@Body JsonElement element);
    //LIST
    @GET("api/blog/v1/list")
    Call<List<JsonElement>> list();
    //FIND
    @GET("api/blog/v1/find/{id}")
    Call<JsonElement> find(@Path("id")Long id);
    //UPDATE
    @PUT("api/blog/v1/update/{id}")
    Call<JsonElement> update(@Path("id") Long id, @Body JsonElement element);
    //DELETE
    @DELETE("api/blog/v1/delete/{id}")
    Call<JsonElement> delete(@Path("id")Long id);

}
