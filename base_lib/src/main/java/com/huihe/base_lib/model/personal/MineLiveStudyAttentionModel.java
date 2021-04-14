package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

public class MineLiveStudyAttentionModel extends JsonListResult<MineLiveStudyAttentionModel.MineLiveStudyAttentionEntity> {

    public class MineLiveStudyAttentionEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 9
         * user_id : 4260
         * master_id : 9099
         * create_time : 2020-01-08 18:11:06
         * update_time : 2020-01-08 18:11:06
         * status : 1
         * map : {"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1578585600000,"cash":"0.000","cat_coin":"21430.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1578645219497,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1578645219000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}}
         */


        private String id;
        private String user_id;
        private String master_id;
        private String create_time;
        private String update_time;
        private int status;
        private MapBean map;


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

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public  class MapBean {
            /**
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1578585600000,"cash":"0.000","cat_coin":"21430.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1578645219497,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1578645219000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}
             */

            private UserInfoEntity userinfo;

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }

        }
    }
}
