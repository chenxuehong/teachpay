package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.base.JsonListResult;

public class OnSaleCourseIncomeModel extends JsonListResult<OnSaleCourseIncomeModel.OnSaleCourseIncomeEntity> {
    public static class OnSaleCourseIncomeEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 3992
         * user_id : 9765
         * rcharge_abstract : 录播课程购买
         * rcharge_account :
         * map : {"masterSetPriceEntity":{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":79,"create_time":"2020-09-04 16:30:12","update_time":"2020-09-08 11:06:22","title":"测试精品课程006号","course_num":1,"amout":100,"service_type":0,"discount":0.5,"discount_amout":10,"user_id":0,"introduction_content":"1231231","status":1,"titile_url":"213$*http://img.curiousmore.com/9099a1599207942322cundefined","first_free":false,"type":"recording","connect_peoplenum":1,"mechanism_id":4,"face_url":"","introduction_url":"","introduction_text":"","pay_num":1,"master_appointment_id":0,"label":"真的么马大","is_recommend":false,"full_name":null,"mechanism_name":null,"map":null}}
         * source : android
         * amount : 0.01
         * rcharge_valid :
         * rcharge_time : 2020-09-04 16:37:19
         * flowing_no : FK20200904163719559161406011
         * trans_no : 2020090422001407761401656753
         * finished_time : 2020-09-04 16:38:17
         * rcharge_type : study_card
         * finished : true
         * status : 2
         * member_level : 0
         * account : true
         * receipt_data :
         * member_duration : 12
         * study_type : recording
         * course_num : 1
         * studycard_id : 79
         * class_card_id : 0
         * appointment_id : 0
         * statistics_time : null
         * start_time : null
         * update_time : null
         * coupon_id : null
         * mechanism_id : 4
         * title : 213
         * master_id : 0
         */


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

        public static class MapBean {
            /**
             * masterSetPriceEntity : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":79,"create_time":"2020-09-04 16:30:12","update_time":"2020-09-08 11:06:22","title":"测试精品课程006号","course_num":1,"amout":100,"service_type":0,"discount":0.5,"discount_amout":10,"user_id":0,"introduction_content":"1231231","status":1,"titile_url":"213$*http://img.curiousmore.com/9099a1599207942322cundefined","first_free":false,"type":"recording","connect_peoplenum":1,"mechanism_id":4,"face_url":"","introduction_url":"","introduction_text":"","pay_num":1,"master_appointment_id":0,"label":"真的么马大","is_recommend":false,"full_name":null,"mechanism_name":null,"map":null}
             */

            private MasterSetPriceEntity masterSetPriceEntity;

            public MasterSetPriceEntity getMasterSetPriceEntity() {
                return masterSetPriceEntity;
            }

            public void setMasterSetPriceEntity(MasterSetPriceEntity masterSetPriceEntity) {
                this.masterSetPriceEntity = masterSetPriceEntity;
            }

        }
    }
}
