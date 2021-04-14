package com.eghuihe.module_user.bean.params;

public class MechanismCourseParam {
    public String id;
    public String title;
    public String appointment_type;
    public String label;
    public String course_num;
    public String amout;
    public String discount_amout;
    public String introduction_content;
    public Integer status;
    public String titile_url;
    public Boolean first_free;
    public String type;
    public String connect_peoplenum;
    public String mechanism_id;
    public String face_url;
    public String introduction_url;
    public String duration;
    public String price_list;
    public Boolean is_attend_activities;
    public String start_time;
    public String end_time;
    public String activity_price;
    public String categories;

    public void setTitile_url(String titile_url) {
        this.titile_url = titile_url;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public MechanismCourseParam(
            String id,
            String title,
            String appointment_type,
            String label,
            String course_num,
            String amout,
            String discount_amout,
            String introduction_content,
            Integer status,
            String titile_url,
            Boolean first_free,
            String type,
            String connect_peoplenum,
            String mechanism_id,
            String face_url,
            String introduction_url,
            String duration,
            String price_list,
            Boolean is_attend_activities,
            String start_time,
            String end_time,
            String activity_price,
            String categories) {
        this.id = id;
        this.title = title;
        this.appointment_type = appointment_type;
        this.label = label;
        this.course_num = course_num;
        this.amout = amout;
        this.discount_amout = discount_amout;
        this.introduction_content = introduction_content;
        this.status = status;
        this.titile_url = titile_url;
        this.first_free = first_free;
        this.type = type;
        this.connect_peoplenum = connect_peoplenum;
        this.mechanism_id = mechanism_id;
        this.face_url = face_url;
        this.introduction_url = introduction_url;
        this.duration = duration;
        this.price_list = price_list;
        this.is_attend_activities = is_attend_activities;
        this.start_time = start_time;
        this.end_time = end_time;
        this.activity_price = activity_price;
        this.categories = categories;
    }
}
