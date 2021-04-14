package com.huihe.base_lib.model;

import com.huihe.base_lib.model.personal.MasterMechanismModel;

public class MechanismOrderEntity {

    private String id;
    private String user_id;
    private String rcharge_abstract;
    private String rcharge_account;
    private String source;
    private String amount;
    private String rcharge_valid;
    private String rcharge_time;
    private String flowing_no;
    private String trans_no;
    private String finished_time;
    private String rcharge_type;
    private Boolean finished;
    private String status;
    private String member_level;
    private String receipt_data;
    private String member_duration;
    private String study_type;
    private String course_num;
    private String studycard_id;
    private String class_card_id;
    private String appointment_id;
    private String mechanism_id;
    private String title;
    private String master_id;
    private Boolean is_experience;
    private Boolean is_one_time_payment;
    private String binding_mechanism_id;
    private String payee_logon_id;
    private String paying_for_identity;
    private String payer_userid;
    private String user_study_card_id;
    private String number_of_lessons;
    private String user_appointment_id;
    private Boolean is_turning_long_orders;
    private Map map;

    public String getUser_appointment_id() {
        return user_appointment_id;
    }

    public String getNumber_of_lessons() {
        return number_of_lessons;
    }

    public Boolean getFinished() {
        return finished;
    }

    public Boolean getIs_experience() {
        return is_experience;
    }

    public Boolean getIs_one_time_payment() {
        return is_one_time_payment;
    }

    public Boolean getIs_turning_long_orders() {
        return is_turning_long_orders;
    }

    public Map getMap() {
        return map;
    }

    public static class Map{
        private MasterSetPriceEntity masterSetPriceEntity;
        private MasterMechanismModel.MasterMechanismEntity mechanismEntity;

        public MasterMechanismModel.MasterMechanismEntity getMechanismEntity() {
            return mechanismEntity;
        }
        private Boolean isComment;
        private UserInfoEntity masterinfo;
        private UserInfoEntity userinfo;

        public MasterSetPriceEntity getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public Boolean getComment() {
            return isComment;
        }

        public UserInfoEntity getMasterinfo() {
            return masterinfo;
        }

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

    public String getRcharge_abstract() {
        return rcharge_abstract;
    }

    public String getRcharge_account() {
        return rcharge_account;
    }

    public String getSource() {
        return source;
    }

    public String getAmount() {
        return amount;
    }

    public String getRcharge_valid() {
        return rcharge_valid;
    }

    public String getRcharge_time() {
        return rcharge_time;
    }

    public String getFlowing_no() {
        return flowing_no;
    }

    public String getTrans_no() {
        return trans_no;
    }

    public String getFinished_time() {
        return finished_time;
    }

    public String getRcharge_type() {
        return rcharge_type;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getStatus() {
        return status;
    }

    public String getMember_level() {
        return member_level;
    }

    public String getReceipt_data() {
        return receipt_data;
    }

    public String getMember_duration() {
        return member_duration;
    }

    public String getStudy_type() {
        return study_type;
    }

    public String getCourse_num() {
        return course_num;
    }

    public String getStudycard_id() {
        return studycard_id;
    }

    public String getClass_card_id() {
        return class_card_id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getTitle() {
        return title;
    }

    public String getMaster_id() {
        return master_id;
    }

    public Boolean isIs_experience() {
        return is_experience;
    }

    public Boolean isIs_one_time_payment() {
        return is_one_time_payment;
    }

    public String getBinding_mechanism_id() {
        return binding_mechanism_id;
    }

    public String getPayee_logon_id() {
        return payee_logon_id;
    }

    public String getPaying_for_identity() {
        return paying_for_identity;
    }

    public String getPayer_userid() {
        return payer_userid;
    }

    public String getUser_study_card_id() {
        return user_study_card_id;
    }

    public Boolean isIs_turning_long_orders() {
        return is_turning_long_orders;
    }
}
