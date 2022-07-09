package com.example.myapplication.main;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("/get")
    Call<Post> getPosts(@Query("name") String name);

    @POST("/post")
    @FormUrlEncoded
    Call<Post> createPost(@Field("post") Post post);

}