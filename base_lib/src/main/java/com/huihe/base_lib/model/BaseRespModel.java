package com.huihe.base_lib.model;

public class BaseRespModel {
    private Integer code;
    private String message;
    private Long time;
    private String resultEnum;

    public Integer getCode() {
        return code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(String resultEnum) {
        this.resultEnum = resultEnum;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return (code != null && code == 0);
    }
}
