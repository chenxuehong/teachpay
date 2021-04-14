package com.huihe.entities_lib.rep.schedule;

import java.util.List;

public class ScheduleItemBean<T>  {
    public boolean isAm;
    public List<T> tList;

    public ScheduleItemBean(boolean isAm, List<T> tList) {
        this.isAm = isAm;
        this.tList = tList;
    }
}
