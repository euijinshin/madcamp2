package com.example.myapplication.main;

import com.google.gson.annotations.SerializedName;



public class Post {
    @SerializedName("NAME")
    private String name;
    @SerializedName("AGE")
    private String age;
    @SerializedName("GENDER")
    private String gender;

    public Post(){}

    public Post(String age, String gender, String name) {
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String score) {
        this.gender = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Post{" +
                "age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}