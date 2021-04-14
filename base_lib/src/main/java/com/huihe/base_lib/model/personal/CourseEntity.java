package com.huihe.base_lib.model.personal;

public class CourseEntity {
    public String title;
    public String content;

    public CourseEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
