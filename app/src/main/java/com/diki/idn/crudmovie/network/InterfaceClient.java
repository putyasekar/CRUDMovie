package com.diki.idn.crudmovie.network;

import com.diki.idn.crudmovie.model.ResponseDeleteMovie;
import com.diki.idn.crudmovie.model.ResponseInsertMovie;
import com.diki.idn.crudmovie.model.ResponseReadMovie;
import com.diki.idn.crudmovie.model.ResponseUpdateMovie;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceClient {
    @FormUrlEncoded
    @POST("exec")
    Call<ResponseInsertMovie> insertMovie(
            @Field(value = "sheetName", encoded = true) String sheetName,
            @Field(value = "action", encoded = true) String action,
            @Field(value = "title", encoded = true) String title,
            @Field(value = "description", encoded = true) String description
    );

    @GET("exec")
    Call<ResponseReadMovie> getMovie(
            @Query("sheetName") String sheetName,
            @Query("action") String read,
            @Query("title") String title,
            @Query("description") String description
    );

    @FormUrlEncoded
    @POST("exec")
    Call<ResponseUpdateMovie> updateMovie(
            @Field(value = "sheetName", encoded = true) String sheetName,
            @Field(value = "action", encoded = true) String update,
            @Field(value = "title", encoded = true) String title,
            @Field(value = "description", encoded = true) String description
    );

    @FormUrlEncoded
    @POST("exec")
    Call<ResponseDeleteMovie> deleteMovie(
            @Field("title") String title
    );
}
