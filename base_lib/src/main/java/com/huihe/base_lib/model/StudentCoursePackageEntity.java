package com.huihe.base_lib.model;

import com.huihe.base_lib.model.personal.MasterMechanismModel;

public class StudentCoursePackageEntity {

    private String id;
    private String create_time;
    private String update_time;
    private String user_id;
    private String type;
    private String studycard_id;
    private String user_study_card_id;
    private String course_num;
    private Integer status;
    private String master_id;
    private String language;
    private String full_name;
    private String level;
    private String start_time;
    private String end_time;
    private String mechanism_id;
    private String binding_mechanism_id;
    private String login_name;
    private String nick_name;
    private Boolean is_one_time_payment;
    private Boolean is_Interoperability;
    private Boolean whether_grouping;
    private String loginIds;
    private String nickNameIds;
    private Boolean is_experience;
    private Boolean settlement_one_third;
    private Boolean settlement_two_thirds;
    private Boolean settlement_all;
    private String original_course_num;
    private String user_group_id;
    private Map map;

    public Boolean getWhether_grouping() {
        return whether_grouping;
    }

    public String getUser_group_id() {
        return user_group_id;
    }

    public String getUser_study_card_id() {
        return user_study_card_id;
    }

    public static class Map{
      private MasterMechanismModel.MasterMechanismEntity mechanismEntity;
      private MasterSetPriceEntity masterSetPriceEntity;
      private UserAppointmentEntity userAppointmentEntity;

        public MasterMechanismModel.MasterMechanismEntity getMechanismEntity() {
            return mechanismEntity;
        }

        public MasterSetPriceEntity getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public UserAppointmentEntity getUserAppointmentEntity() {
            return userAppointmentEntity;
        }
         private String exclusiveSum;
         private UserInfoEntity masterinfo;

        public String getExclusiveSum() {
            return exclusiveSum;
        }

        public UserInfoEntity getMasterinfo() {
            return masterinfo;
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

    public String getUser_id() {
        return user_id;
    }

    public String getType() {
        return type;
    }

    public String getStudycard_id() {
        return studycard_id;
    }

    public String getCourse_num() {
        return course_num;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMaster_id() {
        return master_id;
    }

    public String getLanguage() {
        return language;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getLevel() {
        return level;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getBinding_mechanism_id() {
        return binding_mechanism_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public Boolean getIs_one_time_payment() {
        return is_one_time_payment;
    }

    public Boolean getIs_Interoperability() {
        return is_Interoperability;
    }

    public String getLoginIds() {
        return loginIds;
    }

    public String getNickNameIds() {
        return nickNameIds;
    }

    public Boolean getIs_experience() {
        return is_experience;
    }

    public Boolean getSettlement_one_third() {
        return settlement_one_third;
    }

    public Boolean getSettlement_two_thirds() {
        return settlement_two_thirds;
    }

    public Boolean getSettlement_all() {
        return settlement_all;
    }

    public String getOriginal_course_num() {
        return original_course_num;
    }
}
