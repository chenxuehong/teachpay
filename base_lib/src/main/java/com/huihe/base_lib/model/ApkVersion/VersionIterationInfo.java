package com.huihe.base_lib.model.ApkVersion;

import android.os.Parcel;
import android.os.Parcelable;

public class VersionIterationInfo implements Parcelable {

    /**
     * is_iteration : true
     * new_info : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":1,"user_id":36,"create_time":"2020-02-21 14:12:31","version":2,"url":"http://www.huihejituan.com/tripPictstorage/qmore/apk/__UNI__1E98192_0606110202.apk","status":2,"name":"好气地奇","is_new":true,"platform":"android","iteration_content":""}
     */

    private boolean is_iteration;
    private NewInfo new_info;

    public boolean isIs_iteration() {
        return is_iteration;
    }

    public void setIs_iteration(boolean is_iteration) {
        this.is_iteration = is_iteration;
    }

    public NewInfo getNew_info() {
        return new_info;
    }

    public void setNew_info(NewInfo new_info) {
        this.new_info = new_info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.is_iteration ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.new_info, flags);
    }

    public void readFromParcel(Parcel source) {
        this.is_iteration = source.readByte() != 0;
        this.new_info = source.readParcelable(NewInfo.class.getClassLoader());
    }

    public VersionIterationInfo() {
    }

    protected VersionIterationInfo(Parcel in) {
        this.is_iteration = in.readByte() != 0;
        this.new_info = in.readParcelable(NewInfo.class.getClassLoader());
    }

    public static final Creator<VersionIterationInfo> CREATOR = new Creator<VersionIterationInfo>() {
        @Override
        public VersionIterationInfo createFromParcel(Parcel source) {
            return new VersionIterationInfo(source);
        }

        @Override
        public VersionIterationInfo[] newArray(int size) {
            return new VersionIterationInfo[size];
        }
    };
}
