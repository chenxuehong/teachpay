package com.huihe.base_lib.model;

public class TutorClassMessageData {

    /**
     * roomID : 42601597458130
     * requestUser : 4260
     * appointment_id : 12386
     * version : 3
     */

    private String roomID;
    private String type = "SSChatMessageTypeVideoClassTip";
    private String requestUser;
    private String appointment_id;
    private String studenInfo;
    private int version;

    public TutorClassMessageData(String roomID, String requestUser, String appointment_id, String studenInfo, int version) {
        this.roomID = roomID;
        this.requestUser = requestUser;
        this.appointment_id = appointment_id;
        this.version = version;
        this.studenInfo = studenInfo;
    }

    public String getType() {
        return type;
    }

    public String getStudenInfo() {
        return studenInfo;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
