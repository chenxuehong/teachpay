package com.huihe.base_lib.model;

public class CourseMessageBean {
    private String id;
    private String face_url;
    private String original_price;
    private String discount_amout;
    private String title;
    private String course_num;
    private Boolean is_attend_activities;
    private String messageType;

    public CourseMessageBean(String id, String face_url, String original_price, String discount_amout, String title, String course_num, Boolean is_attend_activities, String messageType) {
        this.id = id;
        this.face_url = face_url;
        this.original_price = original_price;
        this.discount_amout = discount_amout;
        this.title = title;
        this.course_num = course_num;
        this.is_attend_activities = is_attend_activities;
        this.messageType = messageType;
    }

    public String getId() {
        return id;
    }

    public String getFace_url() {
        return face_url;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public String getDiscount_amount() {
        return discount_amout;
    }

    public String getTitle() {
        return title;
    }

    public String getCourse_num() {
        return course_num;
    }

    public Boolean getIs_attend_activities() {
        return is_attend_activities;
    }

    public String getMessageType() {
        return messageType;
    }
}
