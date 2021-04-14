package com.huihe.base_lib.model.request;

import java.util.Date;

public class OneOnFourEntity {

    private String mechanism_id;
    private String title;
    private String user_id;
    private Date date;
    private String languageLevel;
    private String language;
    private Float offset;
    private String masterType;
    private String type;
    private String startTime;
    private String endTime;
    private String group_type;
    private String age_grade;
    private Integer connect_peoplenum;

    public void setConnect_peoplenum(Integer connect_peoplenum) {
        this.connect_peoplenum = connect_peoplenum;
    }

    public Integer getConnect_peoplenum() {
        return connect_peoplenum;
    }

    public String getAge_grade() {
        return age_grade;
    }

    public void setAge_grade(String age_grade) {
        this.age_grade = age_grade;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getTitle() {
        return title;
    }

    public String getUser_id() {
        return user_id;
    }

    public Date getDate() {
        return date;
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public String getLanguage() {
        return language;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOffset(Float offset) {
        this.offset = offset;
    }

    public Float getOffset() {
        return offset;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public String getGroup_type() {
        return group_type;
    }
}
