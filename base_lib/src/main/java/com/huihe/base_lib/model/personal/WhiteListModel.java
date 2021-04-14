package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.home.MessageGroupEntity;

public class WhiteListModel extends JsonListResult<WhiteListModel.WhiteListEntity> {
    public static class WhiteListEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 23
         * user_id : 9191
         * create_time : 2020-05-20 16:39:18
         * update_time : 2020-05-20 16:39:18
         * group_id : 1323
         * status : 1
         * map : {"groupInfo":{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":1323,"owner_id":9420,"group_name":"好的创建","group_type":"Public","operator_account":"","status":2,"create_time":"2020-05-15 17:21:43","group_id":"1589534503","update_time":"2020-05-18 14:14:03","introduction":"","notification":"","faceUrl":"http://img.curiousmore.com/9420groupHead69895GF6","advance_group":false,"is_open":true,"is_charge":false,"fee_standard":0,"entry_password":"","people_num":0,"show_time":"","earnings":0,"number_participants":3,"live_push_addr":"","language":"Danish","label":"话题交流/怎么学好这个语言","type":"online_voice","recommend_type":"","online_num":7,"tickets":0,"live_duration":0,"watch_duration":0,"age_grade":"Teenagers","language_level":"","map":{"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTISXSibrc6mTSCaI90CNDHAIxqUVcZTL2hVoBFjoI855hSCPK8Xu7iaiayErGuYlEnyNlhY4blTDCFjA/132","background_pic":"","birth":1587031514000,"cash":"0.000","cat_coin":"2405.000","chatting_count":0,"city":"","contacts_num":2,"country":"","create_time":1587031514000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1589437836635,"log_out_time":1589433461000,"mail":"","member_level":0,"message_num":0,"mobile":"18716010220","mother_tongue":"English","national_flag":"","nick_name":"小乐","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","pay_pass":"1","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1589424815000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9420","user_id":9420}},"is_life":null,"idList":null}}
         * group_ids : null
         */

        private String id;
        private String user_id;
        private String create_time;
        private String update_time;
        private String group_id;
        private int status;
        private MapBeanX map;
        private Object group_ids;


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

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public MapBeanX getMap() {
            return map;
        }

        public void setMap(MapBeanX map) {
            this.map = map;
        }

        public Object getGroup_ids() {
            return group_ids;
        }

        public void setGroup_ids(Object group_ids) {
            this.group_ids = group_ids;
        }

        public static class MapBeanX {
            /**
             * groupInfo : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":1323,"owner_id":9420,"group_name":"好的创建","group_type":"Public","operator_account":"","status":2,"create_time":"2020-05-15 17:21:43","group_id":"1589534503","update_time":"2020-05-18 14:14:03","introduction":"","notification":"","faceUrl":"http://img.curiousmore.com/9420groupHead69895GF6","advance_group":false,"is_open":true,"is_charge":false,"fee_standard":0,"entry_password":"","people_num":0,"show_time":"","earnings":0,"number_participants":3,"live_push_addr":"","language":"Danish","label":"话题交流/怎么学好这个语言","type":"online_voice","recommend_type":"","online_num":7,"tickets":0,"live_duration":0,"watch_duration":0,"age_grade":"Teenagers","language_level":"","map":{"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTISXSibrc6mTSCaI90CNDHAIxqUVcZTL2hVoBFjoI855hSCPK8Xu7iaiayErGuYlEnyNlhY4blTDCFjA/132","background_pic":"","birth":1587031514000,"cash":"0.000","cat_coin":"2405.000","chatting_count":0,"city":"","contacts_num":2,"country":"","create_time":1587031514000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1589437836635,"log_out_time":1589433461000,"mail":"","member_level":0,"message_num":0,"mobile":"18716010220","mother_tongue":"English","national_flag":"","nick_name":"小乐","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","pay_pass":"1","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1589424815000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9420","user_id":9420}},"is_life":null,"idList":null}
             */

            private MessageGroupEntity groupInfo;

            public MessageGroupEntity getGroupInfo() {
                return groupInfo;
            }

            public void setGroupInfo(MessageGroupEntity groupInfo) {
                this.groupInfo = groupInfo;
            }

        }
    }
}
