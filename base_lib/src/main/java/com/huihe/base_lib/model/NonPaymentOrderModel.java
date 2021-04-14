package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;

public class NonPaymentOrderModel extends JsonListResult<NonPaymentOrderModel.NonPaymentOrderEntity> {
    public static class NonPaymentOrderEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 4305
         * user_id : 9765
         * rcharge_abstract : 母语学习卡
         * rcharge_account :
         * map : {"userInfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.huihejituan.com/1596707574400.png","background_pic":"http://img.huihejituan.com/1600824141257.png","binding_id":0,"birth":1596643200000,"cash":"0.000","cat_coin":"10663.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1596707548000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":true,"intro":"","invite_code":"FEC3T6EX","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":2,"local_time":1600824141793,"log_out_time":1600759405000,"mail":"1319817447@qq.com","master_recommender_id":12,"mechanism_id":43,"mechanism_recommender_id":15,"member_level":0,"message_num":0,"mobile":"","mother_tongue":"Bulgarian","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1600824141000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9765","user_id":9765},"masterSetPriceEntity":null}
         * source : ios
         * amount : 2998
         * rcharge_valid :
         * rcharge_time : 2020-10-13 16:02:57
         * flowing_no : FK20201013160257924709351787
         * trans_no :
         * finished_time : 2020-10-13 16:02:57
         * rcharge_type : study_card
         * finished : false
         * status : 4
         * member_level : 3
         * account : true
         * receipt_data :
         * member_duration : 3
         * study_type : mother_tongue
         * course_num : 36
         * studycard_id : 10
         * class_card_id : 0
         * appointment_id : 0
         * statistics_time : null
         * start_time : null
         * update_time : null
         * coupon_id : null
         * mechanism_id : 0
         * title :
         * master_id : 0
         * nickNameIDs : null
         * nick_name : null
         * code : null
         * grantType : null
         * auth_code : null
         * refresh_token : null
         */

        private int pageSize;
        private int currentPage;
        private int totalItem;
        private int startRow;
        private Object sortName;
        private Object orderBy;
        private Object fileds;
        private int totalPage;
        private int id;
        private int user_id;
        private String rcharge_abstract;
        private String rcharge_account;
        private MapBean map;
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
        private Object start_time;
        private Object update_time;
        private Object coupon_id;
        private String mechanism_id;
        private String title;
        private String master_id;
        private Object nickNameIDs;
        private Object nick_name;
        private Object code;
        private Object grantType;
        private Object auth_code;
        private Object refresh_token;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
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

        public Object getNickNameIDs() {
            return nickNameIDs;
        }

        public void setNickNameIDs(Object nickNameIDs) {
            this.nickNameIDs = nickNameIDs;
        }

        public Object getNick_name() {
            return nick_name;
        }

        public void setNick_name(Object nick_name) {
            this.nick_name = nick_name;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getGrantType() {
            return grantType;
        }

        public void setGrantType(Object grantType) {
            this.grantType = grantType;
        }

        public Object getAuth_code() {
            return auth_code;
        }

        public void setAuth_code(Object auth_code) {
            this.auth_code = auth_code;
        }

        public Object getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(Object refresh_token) {
            this.refresh_token = refresh_token;
        }

        public static class MapBean {
            /**
             * userInfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.huihejituan.com/1596707574400.png","background_pic":"http://img.huihejituan.com/1600824141257.png","binding_id":0,"birth":1596643200000,"cash":"0.000","cat_coin":"10663.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1596707548000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":true,"intro":"","invite_code":"FEC3T6EX","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":2,"local_time":1600824141793,"log_out_time":1600759405000,"mail":"1319817447@qq.com","master_recommender_id":12,"mechanism_id":43,"mechanism_recommender_id":15,"member_level":0,"message_num":0,"mobile":"","mother_tongue":"Bulgarian","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1600824141000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9765","user_id":9765}
             * masterSetPriceEntity : null
             */

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
