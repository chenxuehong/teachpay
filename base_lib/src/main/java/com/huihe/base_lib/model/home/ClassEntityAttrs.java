package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.personal.MasterAppointmentEntity;

public class ClassEntityAttrs {
    public MasterAppointmentEntity classListEntity;
    public MessageGroupEntity findMessageGroupIdEntity;

    public ClassEntityAttrs(MasterAppointmentEntity classListEntity, MessageGroupEntity messageGroupEntity) {
        this.classListEntity = classListEntity;
        this.findMessageGroupIdEntity = messageGroupEntity;
    }
}
