package com.huihe.base_lib.model;

import com.huihe.base_lib.model.personal.AppointmentinfoBean;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;

public class MechanismScheduleParam {
    public String type; // single_class、open_class、private_education
    public MasterAppointmentEntity single_class_info;
    public MasterAppointmentEntity open_class_info;
    public AppointmentinfoBean private_education_info;
    public String start_time;
    public String end_time;

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getType() {
        return type;
    }

    public MasterAppointmentEntity getSingle_class_info() {
        return single_class_info;
    }

    public MasterAppointmentEntity getOpen_class_info() {
        return open_class_info;
    }

    public AppointmentinfoBean getPrivate_education_info() {
        return private_education_info;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSingle_class_info(MasterAppointmentEntity single_class_info) {
        this.single_class_info = single_class_info;
    }

    public void setOpen_class_info(MasterAppointmentEntity open_class_info) {
        this.open_class_info = open_class_info;
    }

    public void setPrivate_education_info(AppointmentinfoBean private_education_info) {
        this.private_education_info = private_education_info;
    }
}
