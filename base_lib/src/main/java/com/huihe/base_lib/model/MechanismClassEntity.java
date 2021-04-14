package com.huihe.base_lib.model;

import java.util.List;

public class MechanismClassEntity {

    private String id;
    private String create_time;
    private String update_time;
    private String mechanism_id;
    private String name;
    private String status;
    private String merger_id;
    private String student_count;
    private String student_count_max;
    private String master_set_price_id;
    private String master_id;
    private Object weekOfDays;
    private Object date;
    private Object start_date;
    private String start_time;
    private String end_time;
    private String type;
    private Map map;
    private boolean is_scheduling_over;
    private String classroom_name;

    public Map getMap() {
        return map;
    }

    public static class Map{
        private String lessonCount;
        private MasterSetPriceEntity masterSetPriceEntity;
        private List<ClassUserInfo> userInfoList;
        private String beginTime;
        private String endTime;
        private String needLessonCount;
        private String endLessonCount;

        public String getLessonCount() {
            return lessonCount;
        }

        public MasterSetPriceEntity getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public List<ClassUserInfo> getUserInfoList() {
            return userInfoList;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getNeedLessonCount() {
            return needLessonCount;
        }

        public String getEndLessonCount() {
            return endLessonCount;
        }
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMerger_id(String merger_id) {
        this.merger_id = merger_id;
    }

    public void setStudent_count(String student_count) {
        this.student_count = student_count;
    }

    public void setStudent_count_max(String student_count_max) {
        this.student_count_max = student_count_max;
    }

    public void setMaster_set_price_id(String master_set_price_id) {
        this.master_set_price_id = master_set_price_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public void setWeekOfDays(Object weekOfDays) {
        this.weekOfDays = weekOfDays;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public void setStart_date(Object start_date) {
        this.start_date = start_date;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIs_scheduling_over(boolean is_scheduling_over) {
        this.is_scheduling_over = is_scheduling_over;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }

    public String getId() {
        return id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getMerger_id() {
        return merger_id;
    }

    public String getStudent_count() {
        return student_count;
    }

    public String getStudent_count_max() {
        return student_count_max;
    }

    public String getMaster_set_price_id() {
        return master_set_price_id;
    }

    public String getMaster_id() {
        return master_id;
    }

    public Object getWeekOfDays() {
        return weekOfDays;
    }

    public Object getDate() {
        return date;
    }

    public Object getStart_date() {
        return start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getType() {
        return type;
    }

    public boolean isIs_scheduling_over() {
        return is_scheduling_over;
    }

    public String getClassroom_name() {
        return classroom_name;
    }
}
