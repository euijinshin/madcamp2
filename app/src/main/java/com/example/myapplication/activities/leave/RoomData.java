package com.example.myapplication.activities.leave;

public class RoomData {

    private String imageUrl;
    private String roomName;
    private String roomDetails;
//    private String hostName;
//    private String hostDetails;
//    private int hostCode;


    public RoomData(String imageUrl, String roomName, String roomDetails) {
        this.imageUrl = imageUrl;
        this.roomName = roomName;
        this.roomDetails = roomDetails;
//        this.hostCode = hostCode;
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

//    public int getHostCode() {
//        return hostCode;
//    }
//
//    public void setHostCode(int hostCode) {
//        this.hostCode = hostCode;
//    }
}
