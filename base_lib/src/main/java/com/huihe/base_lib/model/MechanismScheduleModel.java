package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonResult;
import com.huihe.base_lib.model.personal.AppointmentinfoBean;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;

import java.util.List;

public class MechanismScheduleModel extends JsonResult<MechanismScheduleModel.MechanismScheduleEntity> {
    public static class MechanismScheduleEntity {

        public List<MasterAppointmentEntity> single_class_info;
        public List<MasterAppointmentEntity> open_class_info;
        public List<AppointmentinfoBean> private_education_info;

        public List<MasterAppointmentEntity> getSingle_class_info() {
            return single_class_info;
        }

        public List<MasterAppointmentEntity> getOpen_class_info() {
            return open_class_info;
        }

        public List<AppointmentinfoBean> getPrivate_education_info() {
            return private_education_info;
        }
    }
}
