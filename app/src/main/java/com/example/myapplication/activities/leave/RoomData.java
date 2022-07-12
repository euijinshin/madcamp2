package com.example.myapplication.activities.leave;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomData {
    @SerializedName("id")
    private int roomId;
    @SerializedName("img")
    private String imageUrl;
    @SerializedName("roomName")
    private String roomName;
    @SerializedName("exitTime")
    private String roomDetails;
//    private String hostName;
//    private String hostDetails;
    @SerializedName("user_id")
    private int hostCode;

    @SerializedName("other_id")
    private int otherId;

    public RoomData(String imageUrl,String roomName, String roomDetails, int hostCode) {
        this.roomName = roomName;
        this.roomDetails = roomDetails;
        this.hostCode = hostCode;
        this.imageUrl = imageUrl;
    }

    public RoomData(int otherId) {
        this.otherId = otherId;
    }

    public int getOtherId() {
        return otherId;
    }

    public void setOtherId(int otherId) {
        this.otherId = otherId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(String roomDetails) {
        this.roomDetails = roomDetails;
    }

    public int getHostCode() {
        return hostCode;
    }

    public void setHostCode(int hostCode) {
        this.hostCode = hostCode;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
