package com.eghuihe.module_schedule.ui.student.Bean;

import com.huihe.base_lib.model.StudentScheduleModel;

public class SchedulePackageDetailBean {
    public String title;
    public StudentScheduleModel.StudentScheduleEntity studentScheduleEntity;
    public String appointment_type;
    public String getTitle() {
        return title;
    }

    public SchedulePackageDetailBean(String title, StudentScheduleModel.StudentScheduleEntity studentScheduleEntity,String appointment_type) {
        this.title = title;
        this.studentScheduleEntity = studentScheduleEntity;
        this.appointment_type = appointment_type;
    }

    public StudentScheduleModel.StudentScheduleEntity getStudentScheduleEntity() {
        return studentScheduleEntity;
    }
}
