package com.eghuihe.module_schedule.ui.student.Bean;

public class StudentScheduleParam {
    public String title;
    public String status;
    public Boolean is_comment;

    public StudentScheduleParam(String title,String status, Boolean is_comment) {
        this.title = title;
        this.status = status;
        this.is_comment = is_comment;
    }
}
