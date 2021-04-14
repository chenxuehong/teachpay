package com.eghuihe.module_user.bean;

public class StageSyllabusBean {
    public String startIndex;
    public String totalNum;

    public StageSyllabusBean(String startIndex) {
        this.startIndex = startIndex;
    }

    public StageSyllabusBean(String startIndex, String totalNum) {
        this.startIndex = startIndex;
        this.totalNum = totalNum;
    }
}
