package com.huihe.base_lib.model;

public class SchedulingTipMessage {
    public String result;
    public String sendId;
    public String receiveId;
    public String messageType;

    public SchedulingTipMessage(String result, String sendId, String receiveId, String messageType) {
        this.result = result;
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.messageType = messageType;
    }
}
