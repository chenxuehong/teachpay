package com.huihe.base_lib.model.ApkVersion;

import android.os.Parcel;
import android.os.Parcelable;

public class NewInfo implements Parcelable {
    private int pageSize;
    private int currentPage;
    private int totalItem;
    private int startRow;
    private int totalPage;
    private int id;
    private int user_id;
    private String create_time;
    private String version;
    private String url;
    private int status;
    private String name;
    private boolean is_new;
    private String platform;
    private String iteration_content;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_new() {
        return is_new;
    }

    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIteration_content() {
        return iteration_content;
    }

    public void setIteration_content(String iteration_content) {
        this.iteration_content = iteration_content;
    }

    public NewInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pageSize);
        dest.writeInt(this.currentPage);
        dest.writeInt(this.totalItem);
        dest.writeInt(this.startRow);
        dest.writeInt(this.totalPage);
        dest.writeInt(this.id);
        dest.writeInt(this.user_id);
        dest.writeString(this.create_time);
        dest.writeString(this.version);
        dest.writeString(this.url);
        dest.writeInt(this.status);
        dest.writeString(this.name);
        dest.writeByte(this.is_new ? (byte) 1 : (byte) 0);
        dest.writeString(this.platform);
        dest.writeString(this.iteration_content);
    }

    public void readFromParcel(Parcel source) {
        this.pageSize = source.readInt();
        this.currentPage = source.readInt();
        this.totalItem = source.readInt();
        this.startRow = source.readInt();
        this.totalPage = source.readInt();
        this.id = source.readInt();
        this.user_id = source.readInt();
        this.create_time = source.readString();
        this.version = source.readString();
        this.url = source.readString();
        this.status = source.readInt();
        this.name = source.readString();
        this.is_new = source.readByte() != 0;
        this.platform = source.readString();
        this.iteration_content = source.readString();
    }

    protected NewInfo(Parcel in) {
        this.pageSize = in.readInt();
        this.currentPage = in.readInt();
        this.totalItem = in.readInt();
        this.startRow = in.readInt();
        this.totalPage = in.readInt();
        this.id = in.readInt();
        this.user_id = in.readInt();
        this.create_time = in.readString();
        this.version = in.readString();
        this.url = in.readString();
        this.status = in.readInt();
        this.name = in.readString();
        this.is_new = in.readByte() != 0;
        this.platform = in.readString();
        this.iteration_content = in.readString();
    }

    public static final Creator<NewInfo> CREATOR = new Creator<NewInfo>() {
        @Override
        public NewInfo createFromParcel(Parcel source) {
            return new NewInfo(source);
        }

        @Override
        public NewInfo[] newArray(int size) {
            return new NewInfo[size];
        }
    };
}
