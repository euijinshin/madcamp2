package com.example.myapplication.main;

import com.example.myapplication.activities.leave.RoomData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("get")
        //Call<com.example.myapplication.main.Post> getPosts(@Query("name") String name);
    Call<String> getPosts(@Query("name") String name);

    @POST("post/room")
    Call<String> posts(@Body RoomData roomData);

    @POST("post")
    Call<String> createPosts(@Body Post post );

}