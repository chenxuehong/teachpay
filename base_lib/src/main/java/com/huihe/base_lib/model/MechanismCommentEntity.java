package com.huihe.base_lib.model;

public class MechanismCommentEntity {
    private String id;
    private String user_id;
    private String status;
    private String parent_id;
    private Boolean is_reply;
    private Boolean oneself;
    private String reply_id;
    private String content;
    private String score;
    private String like_count;
    private String master_id;
    private String create_time;
    private String update_time;
    private String appointment_id;
    private String group_id;
    private String mechanism_id;
    private String user_appointment_id;
    private String mastersetprice_id;
    private String share_count;
    private String type;
    private String photo_url;
    private String course_quality;
    private String environment;
    private String cost_effectiveness;
    private String attitude;
    private String faculty;
    private String average_score;
    private Boolean anonymous;
    private Map map;

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Map getMap() {
        return map;
    }

    public static class Map{

        private UserInfoEntity userinfo;

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }
    }
    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }

    public String getParent_id() {
        return parent_id;
    }

    public Boolean getIs_reply() {
        return is_reply;
    }

    public Boolean getOneself() {
        return oneself;
    }

    public String getReply_id() {
        return reply_id;
    }

    public String getContent() {
        return content;
    }

    public String getScore() {
        return score;
    }

    public String getLike_count() {
        return like_count;
    }

    public String getMaster_id() {
        return master_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getUser_appointment_id() {
        return user_appointment_id;
    }

    public String getMastersetprice_id() {
        return mastersetprice_id;
    }

    public String getShare_count() {
        return share_count;
    }

    public String getType() {
        return type;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getCourse_quality() {
        return course_quality;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getCost_effectiveness() {
        return cost_effectiveness;
    }

    public String getAttitude() {
        return attitude;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getAverage_score() {
        return average_score;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }
}
