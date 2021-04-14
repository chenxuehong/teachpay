package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
public class CommentEntity {


    /**
     * id : 602
     * user_id : 4260
     * status : 1
     * parent_id : 0
     * is_reply : false
     * oneself : false
     * reply_id : 0
     * content : 鱼鱼鱼
     * score : 0
     * like_count : 0
     * master_id : 10129
     * create_time : 2020-12-30 17:51:56
     * update_time : 2020-12-30 17:51:56
     * appointment_id : 13819
     * group_id : 0
     * mechanism_id : 43
     * user_appointment_id : 1509
     * mastersetprice_id : 221
     * share_count : 0
     * type : teach_paypal_course
     * photo_url : http://img.huihejituan.com/1609321915411.jpg
     * course_quality : 4
     * environment : 5
     * cost_effectiveness : 5
     * attitude : 4
     * faculty : 5
     * average_score : 0
     * anonymous : false
     */

    public String id;
    public String user_id;
    public String status;
    public String parent_id;
    public boolean is_reply;
    public boolean oneself;
    public String reply_id;
    public String content;
    public Float score;
    public String like_count;
    public String master_id;
    public String create_time;
    public String update_time;
    public String appointment_id;
    public String group_id;
    public String mechanism_id;
    public String user_appointment_id;
    public String mastersetprice_id;
    public String share_count;
    public String type;
    public String photo_url;
    public String course_quality;
    public String environment;
    public String cost_effectiveness;
    public String attitude;
    public String faculty;
    public String average_score;
    public boolean anonymous;

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

    public boolean isIs_reply() {
        return is_reply;
    }

    public boolean isOneself() {
        return oneself;
    }

    public String getReply_id() {
        return reply_id;
    }

    public String getContent() {
        return content;
    }

    public Float getScore() {
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

    public boolean isAnonymous() {
        return anonymous;
    }

    public Map getMap() {
        return map;
    }

    public Map map;
    public static class Map {
        public UserInfoEntity userinfo;

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }
    }
}
