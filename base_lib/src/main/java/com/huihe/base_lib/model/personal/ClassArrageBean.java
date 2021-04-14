package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.MyStudyCenterModel;

import java.util.Date;

public class ClassArrageBean {
    public MyStudyCenterModel.MyStudyCenterEntity studyCenterEntity;
    public Date date;
    public String user_id;
    public String mechanism_id;
    public Boolean isMechanism;
    public String masterType;
    public String language;
    public ClassArrageBean(
            String user_id,
            Date date,
            String mechanism_id,
            String masterType,
            String language,
            Boolean isMechanism) {
        this.user_id = user_id;
        this.mechanism_id = mechanism_id;
        this.masterType = masterType;
        this.language = language;
        this.date = date;
        this.isMechanism = isMechanism;
    }

    public ClassArrageBean(MyStudyCenterModel.MyStudyCenterEntity studyCenterEntity, Date date) {
        this.studyCenterEntity = studyCenterEntity;
        this.date = date;
    }

}
