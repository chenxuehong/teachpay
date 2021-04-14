package com.huihe.base_lib.model.personal;

public class MyClassRoomEntity {
    public String pic;
    public String theme;
    public Integer type;
    public String language;
    public Long total;

    public MyClassRoomEntity(String pic, String theme, Integer type, String language, Long total) {
        this.pic = pic;
        this.theme = theme;
        this.type = type;
        this.language = language;
        this.total = total;
    }
}
