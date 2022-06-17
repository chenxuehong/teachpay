package com.eghuihe.module_schedule.ui.utils;

import com.huihe.base_lib.model.MechanismOfflineScheduleEntity;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.entities_lib.rep.schedule.ScheduleItemBean;

import java.util.ArrayList;
import java.util.List;

public class DataConverter {
    public static List<ScheduleItemBean> convertModel(List<MechanismOfflineScheduleEntity> mechanismOfflineScheduleEntities) {
        List<ScheduleItemBean> itemBeans = new ArrayList<>();
        List<MechanismOfflineScheduleEntity> amList = new ArrayList<>();
        List<MechanismOfflineScheduleEntity> pmList = new ArrayList<>();
        ScheduleItemBean amitemBeans = new ScheduleItemBean(true, amList);
        ScheduleItemBean pmitemBeans = new ScheduleItemBean(false, pmList);
        if (mechanismOfflineScheduleEntities != null) {
            for (int i = 0; i < mechanismOfflineScheduleEntities.size(); i++) {
                MechanismOfflineScheduleEntity mechanismOfflineScheduleEntity = mechanismOfflineScheduleEntities.get(i);
                if (mechanismOfflineScheduleEntity != null) {
                    String start_time = mechanismOfflineScheduleEntity.getStart_time();
                    String apm = DateUtils.getApm(start_time, DateUtils.YMDHMSFormatStr);
                    if ("上午".equals(apm)) {
                        amList.add(mechanismOfflineScheduleEntity);
                    } else {
                        pmList.add(mechanismOfflineScheduleEntity);
                    }
                }
            }
        }
        itemBeans.add(amitemBeans);
        itemBeans.add(pmitemBeans);
        return itemBeans;
    }
}
