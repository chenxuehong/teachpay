package com.huihe.base_lib.model;

public class SchedulingMessageBean {
    public String id;
    public String start_time;
    public String end_time;
    public String classroom;
    public String title;
    public String messageType;

    public SchedulingMessageBean(String id, String start_time, String end_time, String classroom, String title, String messageType) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.classroom = classroom;
        this.title = title;
        this.messageType = messageType;
    }
}
