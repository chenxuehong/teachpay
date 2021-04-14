package com.huihe.base_lib.model;

public class TimezoneBean {
    /**
     * pageSize : 10.0
     * currentPage : 0.0
     * totalItem : 0.0
     * startRow : 0.0
     * totalPage : 0.0
     * id : 324.0
     * create_time : 2019-06-28 14:43:07
     * update_time : 2019-07-12 14:23:36
     * time_code : Australia/Perth
     * offset : 8.0
     * describe_info : 澳大利亚/珀斯
     * timezone : GMT+8
     * platform : ios
     */

    private String id;
    private String create_time;
    private String update_time;
    private String time_code;
    private Float offset;
    private String describe_info;
    private String timezone;
    private String platform;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getTime_code() {
        return time_code;
    }

    public void setTime_code(String time_code) {
        this.time_code = time_code;
    }

    public Float getOffset() {
        return offset;
    }

    public void setOffset(Float offset) {
        this.offset = offset;
    }

    public String getDescribe_info() {
        return describe_info;
    }

    public void setDescribe_info(String describe_info) {
        this.describe_info = describe_info;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}