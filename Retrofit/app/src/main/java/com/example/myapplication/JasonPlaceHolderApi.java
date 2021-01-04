package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JasonPlaceHolderApi {

    @GET("posts")  // getting data from the server
    Call<List<Post>> getPosts();

}
