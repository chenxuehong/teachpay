package com.huihe.base_lib.model.base;

public class Video {
    private int id;
    private String title;
    private long length;
    private String imageUrl;
    private String videoUrl;

    public Video(int id,String title, long length,String imageUrl, String videoUrl) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
