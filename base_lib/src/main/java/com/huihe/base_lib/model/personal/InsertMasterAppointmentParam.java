package com.huihe.base_lib.model.personal;

public class InsertMasterAppointmentParam {

    private String connect_peoplenum;
    private String cover;
    private String end_time;
    private String group_id;
    private String classroom_type;
    private String language;
    private String language_level;
    private String master_id;
    private String identity_type;
    private Float offset;
    private int pageSize;
    private String start_time;
    private String teach_language;
    private String title;
    private String type;
    private String age_grade;
    private String introduction_content;
    private String introduction_cover;
    private String mechanism_id;
    private String group_type;
    private String ageGrade;


    public InsertMasterAppointmentParam(String connect_peoplenum, String cover, String end_time, String group_id,
                                        String classroom_type, String language, String language_level,
                                        String master_id, Float offset, int pageSize, String start_time,
                                        String title, String type, String age_grade,
                                        String introduction_content, String introduction_cover, String mechanism_id) {
        this.connect_peoplenum = connect_peoplenum;
        this.cover = cover;
        this.end_time = end_time;
        this.group_id = group_id;
        this.classroom_type = classroom_type;
        this.language = language;
        this.language_level = language_level;
        this.master_id = master_id;
        this.offset = offset;
        this.pageSize = pageSize;
        this.start_time = start_time;
        this.language = language;
        this.title = title;
        this.type = type;
        this.age_grade = age_grade;
        this.introduction_content = introduction_content;
        this.introduction_cover = introduction_cover;
        this.mechanism_id = mechanism_id;
    }

    public InsertMasterAppointmentParam(String end_time, String master_id, String identity_type, Float offset, int pageSize,
                                        String start_time, String language, String type, String mechanism_id, String connect_peoplenum, String title, String group_type, String ageGrade) {
        this.end_time = end_time;
        this.master_id = master_id;
        this.identity_type = identity_type;
        this.offset = offset;
        this.pageSize = pageSize;
        this.start_time = start_time;
        this.language = language;
        this.type = type;
        this.mechanism_id = mechanism_id;
        this.connect_peoplenum = connect_peoplenum;
        this.title = title;
        this.group_type = group_type;
        this.ageGrade = ageGrade;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getClassroom_type() {
        return classroom_type;
    }

    public void getClassroom_type(String classroom_type) {
        this.classroom_type = classroom_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage_level() {
        return language_level;
    }

    public void setLanguage_level(String language_level) {
        this.language_level = language_level;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public Float getOffset() {
        return offset;
    }

    public void setOffset(Float offset) {
        this.offset = offset;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }


    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
