package com.example.myapplication.activities.leave;

import com.google.gson.annotations.SerializedName;

public class RoomData2 {
    private int id;
    private int user_id;
    private String roomName;
    private String exitTime;
    private String other_id;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getOther_id() {
        return other_id;
    }

    public void setOther_id(String other_id) {
        this.other_id = other_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
