package com.huihe.base_lib.model;

public class ClassStudentBean {

    /**
     * id : 1030
     * create_time : 2021-01-22 14:12:37
     * update_time : 2021-01-22 14:12:37
     * user_id : 9120
     * type : mechanism_offline
     * studycard_id : 441
     * course_num : 10
     * status : 2
     * master_id : 0
     * language :
     * full_name : null
     * level : 0
     * start_time : 2021-01-22 14:12:37
     * end_time : 2021-11-22 14:12:38
     * mechanism_id : 4
     * binding_mechanism_id : 0
     * login_name : null
     * nick_name : null
     * is_one_time_payment : false
     * is_Interoperability : false
     * loginIds : null
     * nickNameIds : null
     * is_experience : false
     * settlement_one_third : false
     * settlement_two_thirds : false
     * settlement_all : false
     * original_course_num : 10
     * each_lesson_price : 0
     * user_group_id :
     * mechanism_class_id : 5
     * master_set_price_group_id : 23152767
     */

    private String id;
    private String create_time;
    private String update_time;
    private String user_id;
    private String type;
    private String studycard_id;
    private String course_num;
    private String status;
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
    private boolean is_one_time_payment;
    private boolean is_Interoperability;
    private boolean is_experience;
    private boolean settlement_one_third;
    private boolean settlement_two_thirds;
    private boolean settlement_all;
    private String original_course_num;
    private String each_lesson_price;
    private String user_group_id;
    private String mechanism_class_id;
    private String master_set_price_group_id;
    private Map map;

    public Map getMap() {
        return map;
    }

    public static class Map{
        private MasterSetPriceEntity masterSetPriceEntity;
        private UserAppointmentEntity userAppointmentEntity;
        private UserInfoEntity userinfo;

        public MasterSetPriceEntity getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public UserAppointmentEntity getUserAppointmentEntity() {
            return userAppointmentEntity;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }
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

    public String getStatus() {
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

    public boolean isIs_one_time_payment() {
        return is_one_time_payment;
    }

    public boolean isIs_Interoperability() {
        return is_Interoperability;
    }

    public boolean isIs_experience() {
        return is_experience;
    }

    public boolean isSettlement_one_third() {
        return settlement_one_third;
    }

    public boolean isSettlement_two_thirds() {
        return settlement_two_thirds;
    }

    public boolean isSettlement_all() {
        return settlement_all;
    }

    public String getOriginal_course_num() {
        return original_course_num;
    }

    public String getEach_lesson_price() {
        return each_lesson_price;
    }

    public String getUser_group_id() {
        return user_group_id;
    }

    public String getMechanism_class_id() {
        return mechanism_class_id;
    }

    public String getMaster_set_price_group_id() {
        return master_set_price_group_id;
    }
}
