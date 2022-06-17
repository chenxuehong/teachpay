package com.huihe.base_lib.model.ApkVersion;

import android.os.Parcel;
import android.os.Parcelable;

public class AppInfo implements Parcelable {
    private String url;
    private String name;
    private boolean is_new;
    private String platform;
    private String version;
    private String iteration_content;
    private boolean is_iteration;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setIteration_content(String iteration_content) {
        this.iteration_content = iteration_content;
    }

    public void setIs_iteration(boolean is_iteration) {
        this.is_iteration = is_iteration;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public boolean isIs_new() {
        return is_new;
    }

    public String getPlatform() {
        return platform;
    }

    public String getIteration_content() {
        return iteration_content;
    }

    public boolean isIs_iteration() {
        return is_iteration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeByte(this.is_new ? (byte) 1 : (byte) 0);
        dest.writeString(this.platform);
        dest.writeString(this.iteration_content);
        dest.writeByte(this.is_iteration ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.url = source.readString();
        this.name = source.readString();
        this.is_new = source.readByte() != 0;
        this.platform = source.readString();
        this.iteration_content = source.readString();
        this.is_iteration = source.readByte() != 0;
    }

    public AppInfo() {
    }

    public AppInfo(String url, String name, boolean is_new, String platform, String iteration_content, boolean is_iteration) {
        this.url = url;
        this.name = name;
        this.is_new = is_new;
        this.platform = platform;
        this.iteration_content = iteration_content;
        this.is_iteration = is_iteration;
    }

    protected AppInfo(Parcel in) {
        this.url = in.readString();
        this.name = in.readString();
        this.is_new = in.readByte() != 0;
        this.platform = in.readString();
        this.iteration_content = in.readString();
        this.is_iteration = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>() {
        @Override
        public AppInfo createFromParcel(Parcel source) {
            return new AppInfo(source);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };
}
