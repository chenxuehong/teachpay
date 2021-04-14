package com.huihe.base_lib.model.event;

public class ClassDurationEvent {
    public String startTime;
    public String endTime;

    public ClassDurationEvent(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
