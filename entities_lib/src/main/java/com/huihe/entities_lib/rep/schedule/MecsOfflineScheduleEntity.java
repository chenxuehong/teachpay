package com.huihe.entities_lib.rep.schedule;

import com.huihe.entities_lib.rep.user.UserInfoEntity;

public class MecsOfflineScheduleEntity {
    private String id;
    private String create_time;
    private String update_time;
    private String master_id;
    private String start_time;
    private String end_time;
    private String status;
    private String type;
    private Float offset;
    private String timezone_id;
    private String title;
    private String group_id;
    private String cover;
    private String age_grade;
    private String language_level;
    private String language;
    private String group_type;
    private String room_id;
    private Object is_appointment;
    private Object entities;
    private Object timecode;
    private Object teach_language;
    private String classroom_type;
    private String introduction_cover;
    private String introduction_content;
    private String service_type;
    private String mechanism_id;
    private String amout;
    private String discount;
    private String discount_amout;
    private Boolean is_discount;
    private String teach_program;
    private String latitude;
    private String longitude;
    private String recommend_type;
    private String identity_type;
    private Object local_offset;
    private Object is_teenagers;
    private Object user_id;
    private Object group_ids;
    private String connect_peoplenum;
    private Boolean is_recording;
    private Boolean is_special;
    private Boolean is_firstpage_show;
    private String master_set_price_id;
    private String full_name;
    private String ids;
    private String study_card_ids;
    private String loginIDs;
    private String profit;
    private String masterRecommenderCash;
    private String finalEarn;
    private String recommender_id;
    private String log_id;
    private String masterLog_id;
    private String like_count;
    private String comment_count;
    private String share_count;
    private String group_name;
    private String study_card_id;
    private String student_id;
    private Boolean is_qualified;
    private String reason_for_refund;
    private String classroom;
    private String create_type;
    private String loginList;
    private String source;
    private String login_name;

    private Map map;

    public static class Map {
        private UserInfoEntity masterInfo;
        private MasterSetPriceEntity masterSetPriceEntity;
        private String studentNum;

        public UserInfoEntity getMasterInfo() {
            return masterInfo;
        }

        public MasterSetPriceEntity getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public String getStudentNum() {
            return studentNum;
        }
    }

    public Map getMap() {
        return map;
    }

    public String getId() {
        return id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getMaster_id() {
        return master_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public Float getOffset() {
        return offset;
    }

    public String getTimezone_id() {
        return timezone_id;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getCover() {
        return cover;
    }

    public String getAge_grade() {
        return age_grade;
    }

    public String getLanguage_level() {
        return language_level;
    }

    public String getLanguage() {
        return language;
    }

    public String getGroup_type() {
        return group_type;
    }

    public String getRoom_id() {
        return room_id;
    }

    public String getClassroom_type() {
        return classroom_type;
    }

    public String getIntroduction_cover() {
        return introduction_cover;
    }

    public String getIntroduction_content() {
        return introduction_content;
    }

    public String getService_type() {
        return service_type;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getAmout() {
        return amout;
    }

    public String getDiscount() {
        return discount;
    }

    public String getDiscount_amout() {
        return discount_amout;
    }

    public Boolean getIs_discount() {
        return is_discount;
    }

    public String getTeach_program() {
        return teach_program;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getRecommend_type() {
        return recommend_type;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public String getConnect_peoplenum() {
        return connect_peoplenum;
    }

    public Boolean getIs_recording() {
        return is_recording;
    }

    public Boolean getIs_special() {
        return is_special;
    }

    public Boolean getIs_firstpage_show() {
        return is_firstpage_show;
    }

    public String getMaster_set_price_id() {
        return master_set_price_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getIds() {
        return ids;
    }

    public String getStudy_card_ids() {
        return study_card_ids;
    }

    public String getLoginIDs() {
        return loginIDs;
    }

    public String getProfit() {
        return profit;
    }

    public String getMasterRecommenderCash() {
        return masterRecommenderCash;
    }

    public String getFinalEarn() {
        return finalEarn;
    }

    public String getRecommender_id() {
        return recommender_id;
    }

    public String getLog_id() {
        return log_id;
    }

    public String getMasterLog_id() {
        return masterLog_id;
    }

    public String getLike_count() {
        return like_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public String getShare_count() {
        return share_count;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getStudy_card_id() {
        return study_card_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public Boolean getIs_qualified() {
        return is_qualified;
    }

    public String getReason_for_refund() {
        return reason_for_refund;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getCreate_type() {
        return create_type;
    }

    public String getLoginList() {
        return loginList;
    }

    public String getSource() {
        return source;
    }

    public String getLogin_name() {
        return login_name;
    }
}
