package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

public class RechargerecordListModel extends JsonListResult<RechargerecordListModel.RechargerecordEntity> {
    public static class RechargerecordEntity {

        private String id;
        private String user_id;
        private String rcharge_abstract;
        private String rcharge_account;
        private MapBean map;
        private String source;
        private String amount;
        private String rcharge_valid;
        private String rcharge_time;
        private String flowing_no;
        private String trans_no;
        private String finished_time;
        private String rcharge_type;
        private boolean finished;
        private String status;
        private String member_level;
        private boolean account;
        private String receipt_data;
        private String member_duration;
        private String study_type;
        private Integer course_num;
        private String studycard_id;
        private String class_card_id;
        private String appointment_id;
        private Object statistics_time;
        private Object start_time;
        private Object update_time;
        private Object coupon_id;
        private String mechanism_id;
        private String title;
        private String master_id;
        private boolean is_experience;
        private Object is_one_time_payment;
        private String binding_mechanism_id;

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

        public String getRcharge_abstract() {
            return rcharge_abstract;
        }

        public void setRcharge_abstract(String rcharge_abstract) {
            this.rcharge_abstract = rcharge_abstract;
        }

        public String getRcharge_account() {
            return rcharge_account;
        }

        public void setRcharge_account(String rcharge_account) {
            this.rcharge_account = rcharge_account;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getRcharge_valid() {
            return rcharge_valid;
        }

        public void setRcharge_valid(String rcharge_valid) {
            this.rcharge_valid = rcharge_valid;
        }

        public String getRcharge_time() {
            return rcharge_time;
        }

        public void setRcharge_time(String rcharge_time) {
            this.rcharge_time = rcharge_time;
        }

        public String getFlowing_no() {
            return flowing_no;
        }

        public void setFlowing_no(String flowing_no) {
            this.flowing_no = flowing_no;
        }

        public String getTrans_no() {
            return trans_no;
        }

        public void setTrans_no(String trans_no) {
            this.trans_no = trans_no;
        }

        public String getFinished_time() {
            return finished_time;
        }

        public void setFinished_time(String finished_time) {
            this.finished_time = finished_time;
        }

        public String getRcharge_type() {
            return rcharge_type;
        }

        public void setRcharge_type(String rcharge_type) {
            this.rcharge_type = rcharge_type;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }

        public boolean isAccount() {
            return account;
        }

        public void setAccount(boolean account) {
            this.account = account;
        }

        public String getReceipt_data() {
            return receipt_data;
        }

        public void setReceipt_data(String receipt_data) {
            this.receipt_data = receipt_data;
        }

        public String getMember_duration() {
            return member_duration;
        }

        public void setMember_duration(String member_duration) {
            this.member_duration = member_duration;
        }

        public String getStudy_type() {
            return study_type;
        }

        public void setStudy_type(String study_type) {
            this.study_type = study_type;
        }

        public Integer getCourse_num() {
            return course_num;
        }

        public void setCourse_num(Integer course_num) {
            this.course_num = course_num;
        }

        public String getStudycard_id() {
            return studycard_id;
        }

        public void setStudycard_id(String studycard_id) {
            this.studycard_id = studycard_id;
        }

        public String getClass_card_id() {
            return class_card_id;
        }

        public void setClass_card_id(String class_card_id) {
            this.class_card_id = class_card_id;
        }

        public String getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(String appointment_id) {
            this.appointment_id = appointment_id;
        }

        public Object getStatistics_time() {
            return statistics_time;
        }

        public void setStatistics_time(Object statistics_time) {
            this.statistics_time = statistics_time;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public Object getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(Object coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getMechanism_id() {
            return mechanism_id;
        }

        public void setMechanism_id(String mechanism_id) {
            this.mechanism_id = mechanism_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
        }

        public boolean isIs_experience() {
            return is_experience;
        }

        public void setIs_experience(boolean is_experience) {
            this.is_experience = is_experience;
        }

        public Object getIs_one_time_payment() {
            return is_one_time_payment;
        }

        public void setIs_one_time_payment(Object is_one_time_payment) {
            this.is_one_time_payment = is_one_time_payment;
        }

        public Object getBinding_mechanism_id() {
            return binding_mechanism_id;
        }

        public static class MapBean {

            private UserInfoEntity userInfo;
            private MasterSetPriceEntity masterSetPriceEntity;

            public UserInfoEntity getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoEntity userInfo) {
                this.userInfo = userInfo;
            }

            public MasterSetPriceEntity getMasterSetPriceEntity() {
                return masterSetPriceEntity;
            }

            public void setMasterSetPriceEntity(MasterSetPriceEntity masterSetPriceEntity) {
                this.masterSetPriceEntity = masterSetPriceEntity;
            }
        }
    }
}
