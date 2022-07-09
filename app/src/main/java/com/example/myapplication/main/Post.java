package com.example.myapplication.main;

import com.google.gson.annotations.SerializedName;



public class Post {

    private String age;
    private String score;

    @SerializedName("body")
    private String name;

    public Post(){}

    public Post(String age, String score, String name) {
        this.age = age;
        this.score = score;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}