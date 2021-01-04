package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private int id;
    private  String title;

    @SerializedName("body")  //java field text is represented by body in json file
    private  String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
