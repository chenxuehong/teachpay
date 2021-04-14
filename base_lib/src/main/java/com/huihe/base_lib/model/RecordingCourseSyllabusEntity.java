package com.huihe.base_lib.model;

public class RecordingCourseSyllabusEntity {
    public String title;
    public String url;
    public String id;

    public RecordingCourseSyllabusEntity(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }
}
