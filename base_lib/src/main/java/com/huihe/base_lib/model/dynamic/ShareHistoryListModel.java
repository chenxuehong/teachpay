package com.huihe.base_lib.model.dynamic;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

public class ShareHistoryListModel extends JsonListResult<ShareHistoryListModel.ShareHistoryEntity> {
    public static class ShareHistoryEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 553
         * user_id : 9191
         * create_time : 2020-03-21 08:17:42
         * update_time : 2020-03-23 06:08:12
         * operation_type : share
         * history_id : 3487
         * address :
         * status : 2
         * history_type : note
         * destination : SinaWeibo
         * map : {"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584535512217.jpg","birth":1546243200000,"cash":"0.000","cat_coin":"29.000","chatting_count":0,"city":"艾因·德夫拉","contacts_num":2,"country":"阿尔及利亚","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1584787316020,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Chinese","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584758516000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}}
         */

        private String id;
        private String user_id;
        private String create_time;
        private String update_time;
        private String operation_type;
        private String history_id;
        private String address;
        private int status;
        private String history_type;
        private String destination;
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

        public String getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(String operation_type) {
            this.operation_type = operation_type;
        }

        public String getHistory_id() {
            return history_id;
        }

        public void setHistory_id(String history_id) {
            this.history_id = history_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHistory_type() {
            return history_type;
        }

        public void setHistory_type(String history_type) {
            this.history_type = history_type;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public static class MapBean {
            /**
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584535512217.jpg","birth":1546243200000,"cash":"0.000","cat_coin":"29.000","chatting_count":0,"city":"艾因·德夫拉","contacts_num":2,"country":"阿尔及利亚","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1584787316020,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Chinese","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584758516000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
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
