package com.huihe.base_lib.model.order;

import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

public class SaleOnCourseOrderModel extends JsonListResult<SaleOnCourseOrderModel.SaleOnCourseOrderEntity> {
    public static class SaleOnCourseOrderEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 3846
         * user_id : 9765
         * rcharge_abstract : 专属课程购买
         * rcharge_account :
         * source : android
         * amount : 0.01
         * rcharge_valid :
         * rcharge_time : 2020-08-27 21:06:40
         * flowing_no : FK20200827210640360673295707
         * trans_no : 2020082722001407761454527707
         * finished_time : 2020-08-27 21:06:49
         * rcharge_type : study_card
         * finished : true
         * status : 2
         * member_level : 0
         * account : true
         * receipt_data :
         * member_duration : 12
         * study_type : exclusive_courses
         * course_num : 10
         * studycard_id : 9
         * class_card_id : 0
         * appointment_id : 0
         * statistics_time : null
         * coupon_id : null
         * mechanism_id : 0
         * title :
         * master_id : 0
         */


        private String id;
        private String user_id;
        private String rcharge_abstract;
        private String rcharge_account;
        private String source;
        private double amount;
        private String rcharge_valid;
        private String rcharge_time;
        private String flowing_no;
        private String trans_no;
        private String finished_time;
        private String rcharge_type;
        private boolean finished;
        private int status;
        private int member_level;
        private boolean account;
        private String receipt_data;
        private int member_duration;
        private String study_type;
        private int course_num;
        private String studycard_id;
        private String class_card_id;
        private String appointment_id;
        private Object statistics_time;
        private Object coupon_id;
        private String mechanism_id;
        private String title;
        private String master_id;
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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getMember_level() {
            return member_level;
        }

        public void setMember_level(int member_level) {
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

        public int getMember_duration() {
            return member_duration;
        }

        public void setMember_duration(int member_duration) {
            this.member_duration = member_duration;
        }

        public String getStudy_type() {
            return study_type;
        }

        public void setStudy_type(String study_type) {
            this.study_type = study_type;
        }

        public int getCourse_num() {
            return course_num;
        }

        public void setCourse_num(int course_num) {
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

        private MapBean map;

        public MapBean getMap() {
            return map;
        }

        public static class MapBean{

            private UserInfoEntity masterinfo;
            private UserInfoEntity userinfo;
            private Boolean isComment;
            private MasterMechanismModel.MasterMechanismEntity mechanismEntity;

            public Boolean getIsComment() {
                return isComment;
            }

            public MasterMechanismModel.MasterMechanismEntity getMechanismEntity() {
                return mechanismEntity;
            }
            private MasterSetPriceEntity masterSetPriceEntity;
            private MasterAppointmentEntity masterAppointmentEntity;
            public MasterSetPriceEntity getMasterSetPriceEntity() {
                return masterSetPriceEntity;
            }

            public MasterAppointmentEntity getMasterAppointmentEntity() {
                return masterAppointmentEntity;
            }

            public UserInfoEntity getMasterinfo() {
                return masterinfo;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }
        }
    }
}
