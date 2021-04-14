package com.huihe.base_lib.model;

import com.huihe.base_lib.model.home.MessageGroupEntity;
import com.huihe.base_lib.model.personal.AppointmentinfoBean;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;

public class MasterCommentEntity {
    private int pageSize;
    private int currentPage;
    private int totalItem;
    private int startRow;
    private Object sortName;
    private Object orderBy;
    private Object fileds;
    private int totalPage;
    private String id;
    private String user_id;
    private int status;
    private String parent_id;
    private boolean is_reply;
    private boolean oneself;
    private String reply_id;
    private String content;
    private float score;
    private int like_count;
    private String master_id;
    private String create_time;
    private String update_time;
    private String appointment_id;
    private String group_id;
    private String mechanism_id;
    private MapBeam map;

    public static class MapBeam{
        private MessageGroupEntity groupinfo;
        private AppointmentinfoBean appointmentinfo;
        private MasterAppointmentEntity masterAppointmentInfo;
        private int subcount;
        private UserInfoEntity userinfo;

        public MessageGroupEntity getGroupinfo() {
            return groupinfo;
        }

        public AppointmentinfoBean getAppointmentinfo() {
            return appointmentinfo;
        }

        public MasterAppointmentEntity getMasterAppointmentInfo() {
            return masterAppointmentInfo;
        }

        public int getSubcount() {
            return subcount;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }
    }

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

    public Object getSortName() {
        return sortName;
    }

    public void setSortName(Object sortName) {
        this.sortName = sortName;
    }

    public Object getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Object orderBy) {
        this.orderBy = orderBy;
    }

    public Object getFileds() {
        return fileds;
    }

    public void setFileds(Object fileds) {
        this.fileds = fileds;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isIs_reply() {
        return is_reply;
    }

    public void setIs_reply(boolean is_reply) {
        this.is_reply = is_reply;
    }

    public boolean isOneself() {
        return oneself;
    }

    public void setOneself(boolean oneself) {
        this.oneself = oneself;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }
}
