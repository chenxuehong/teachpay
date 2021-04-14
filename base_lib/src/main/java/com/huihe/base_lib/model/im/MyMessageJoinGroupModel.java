package com.huihe.base_lib.model.im;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.home.MessageGroupEntity;

public class MyMessageJoinGroupModel extends JsonListResult<MyMessageJoinGroupModel.MyMessageJoinGroupEntity> {

    public static class MyMessageJoinGroupEntity {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 1657
         * group_id : 785
         * user_id : 9191
         * create_time : 2019-12-09 08:12:28
         * status : 4
         * remark_name :
         * join_type : Apply
         * operator_account : 9191
         * exit_type : Quit
         * map : {"groupinfo":{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":785,"owner_id":9163,"group_name":"恍恍惚惚和红红火火","group_type":"Public","operator_account":"","status":2,"create_time":"2019-12-09 05:34:36","group_id":"1575869676","update_time":"2020-03-18 03:31:17","introduction":"","notification":"","faceUrl":"http://img.curiousmore.com/50E-163AF5C3E089/Documents/1huihe1575869800.png","advance_group":false,"is_open":true,"is_charge":false,"fee_standard":0,"entry_password":"","people_num":0,"show_time":"2019-12-11 01:00:00","earnings":0,"number_participants":10,"live_push_addr":"","language":"English","label":"话题交流/聊聊美食","type":"recreation_live","recommend_type":"","online_num":0,"tickets":0,"live_duration":0,"watch_duration":0,"age_grade":"0","language_level":"","map":null,"is_life":null,"idList":null},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584517825803.jpg","birth":1546243200000,"cash":"0.000","cat_coin":"289.000","chatting_count":0,"city":"北京","contacts_num":2,"country":"中国","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"中国·北京","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1584517826883,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Chinese","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584489026000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}}
         */


        private String id;
        private String group_id;
        private String user_id;
        private String create_time;
        private int status;
        private String remark_name;
        private String join_type;
        private String operator_account;
        private String exit_type;
        private MapBean map;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark_name() {
            return remark_name;
        }

        public void setRemark_name(String remark_name) {
            this.remark_name = remark_name;
        }

        public String getJoin_type() {
            return join_type;
        }

        public void setJoin_type(String join_type) {
            this.join_type = join_type;
        }

        public String getOperator_account() {
            return operator_account;
        }

        public void setOperator_account(String operator_account) {
            this.operator_account = operator_account;
        }

        public String getExit_type() {
            return exit_type;
        }

        public void setExit_type(String exit_type) {
            this.exit_type = exit_type;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public static class MapBean {
            /**
             * groupinfo : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":785,"owner_id":9163,"group_name":"恍恍惚惚和红红火火","group_type":"Public","operator_account":"","status":2,"create_time":"2019-12-09 05:34:36","group_id":"1575869676","update_time":"2020-03-18 03:31:17","introduction":"","notification":"","faceUrl":"http://img.curiousmore.com/50E-163AF5C3E089/Documents/1huihe1575869800.png","advance_group":false,"is_open":true,"is_charge":false,"fee_standard":0,"entry_password":"","people_num":0,"show_time":"2019-12-11 01:00:00","earnings":0,"number_participants":10,"live_push_addr":"","language":"English","label":"话题交流/聊聊美食","type":"recreation_live","recommend_type":"","online_num":0,"tickets":0,"live_duration":0,"watch_duration":0,"age_grade":"0","language_level":"","map":null,"is_life":null,"idList":null}
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584517825803.jpg","birth":1546243200000,"cash":"0.000","cat_coin":"289.000","chatting_count":0,"city":"北京","contacts_num":2,"country":"中国","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"中国·北京","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1584517826883,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Chinese","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584489026000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
             */

            private MessageGroupEntity groupinfo;
            private UserInfoEntity userinfo;

            public MessageGroupEntity getGroupinfo() {
                return groupinfo;
            }

            public void setGroupinfo(MessageGroupEntity groupinfo) {
                this.groupinfo = groupinfo;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }

        }
    }
}
