package com.eghuihe.module_user.bean.params;

public class StageSyllabusParam {
    public String startIndex;
    public String endIndex;
    public String title;

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public void setEndIndex(String endIndex) {
        this.endIndex = endIndex;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StageSyllabusParam(String startIndex, String endIndex, String title) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.title = title;
    }
}
