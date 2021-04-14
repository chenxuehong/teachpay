package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.TimezoneBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.home.MessageGroupEntity;

public class MineLiveStudyCourseModel extends JsonListResult<MineLiveStudyCourseModel.MineLiveStudyCourseEntity> {
    public class MineLiveStudyCourseEntity{


        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 7
         * user_id : 9191
         * master_id : 9099
         * create_time : 2020-01-17 18:27:50
         * update_time : 2020-01-17 18:27:50
         * appointment_id : 956
         * status : 2
         * start_time : 2020-01-17 19:30:00
         * end_time : 2020-01-17 20:00:00
         * title :
         * offset : 8
         * timezone_id : 285
         * update_appointment_id : 0
         * class_type : open_class
         * language : Chinese
         * map : {"timezone":{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":285,"create_time":"2019-06-28 14:43:06","update_time":"2019-07-11 19:38:46","time_code":"Asia/Shanghai","offset":8,"describe_info":"北京标准时间","timezone":"GMT+8","platform":"ios"},"groupinfo":{"entry_password":"","show_time":"","fileds":"","sortName":"","tickets":0,"online_num":1,"owner_id":9099,"fee_standard":0,"orderBy":"","pageSize":10,"recommend_type":"","language":"Chinese","group_type":"Public","idList":[],"type":"online_video","age_grade":"成人","notification":"","update_time":"2020-01-17 16:27:07","people_num":4,"advance_group":false,"is_charge":false,"number_participants":1,"id":921,"map":null,"introduction":"","create_time":"2020-01-17 16:27:07","startRow":0,"group_name":"好纠结了还上唧富太太","is_open":true,"totalItem":0,"totalPage":0,"language_level":"","is_life":false,"watch_duration":0,"label":"Talk about work","operator_account":"","live_push_addr":"","faceUrl":"http://img.curiousmore.com/1579249697616.jpg","earnings":0,"group_id":"1579249627","live_duration":0,"currentPage":0,"status":1},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"","background_pic":"","birth":1546272000000,"cash":"0.000","cat_coin":"60.000","chatting_count":0,"city":"北京市·市辖区·东城区","contacts_num":0,"country":"中国","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"北京市·市辖区·东城区","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1574328343943,"log_out_time":1574063904000,"mail":"15514766147@qq.com","member_level":0,"message_num":0,"mobile":"15514866147","mother_tongue":"Chinese","national_flag":"","nick_name":"Maxsddd","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"搞笑/习俗","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1574328317000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191},"masterinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1578585600000,"cash":"0.000","cat_coin":"21430.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1578645219497,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1578645219000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}}
         */

        private String id;
        private String user_id;
        private String master_id;
        private String create_time;
        private String update_time;
        private String appointment_id;
        private int status;
        private String start_time;
        private String end_time;
        private String title;
        private Float offset;
        private int timezone_id;
        private String update_appointment_id;
        private String class_type;
        private String language;
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

        public String getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(String appointment_id) {
            this.appointment_id = appointment_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Float getOffset() {
            return offset;
        }

        public void setOffset(Float offset) {
            this.offset = offset;
        }

        public int getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(int timezone_id) {
            this.timezone_id = timezone_id;
        }

        public String getUpdate_appointment_id() {
            return update_appointment_id;
        }

        public void setUpdate_appointment_id(String update_appointment_id) {
            this.update_appointment_id = update_appointment_id;
        }

        public String getClass_type() {
            return class_type;
        }

        public void setClass_type(String class_type) {
            this.class_type = class_type;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public  class MapBean {
            /**
             * timezone : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":285,"create_time":"2019-06-28 14:43:06","update_time":"2019-07-11 19:38:46","time_code":"Asia/Shanghai","offset":8,"describe_info":"北京标准时间","timezone":"GMT+8","platform":"ios"}
             * groupinfo : {"entry_password":"","show_time":"","fileds":"","sortName":"","tickets":0,"online_num":1,"owner_id":9099,"fee_standard":0,"orderBy":"","pageSize":10,"recommend_type":"","language":"Chinese","group_type":"Public","idList":[],"type":"online_video","age_grade":"成人","notification":"","update_time":"2020-01-17 16:27:07","people_num":4,"advance_group":false,"is_charge":false,"number_participants":1,"id":921,"map":null,"introduction":"","create_time":"2020-01-17 16:27:07","startRow":0,"group_name":"好纠结了还上唧富太太","is_open":true,"totalItem":0,"totalPage":0,"language_level":"","is_life":false,"watch_duration":0,"label":"Talk about work","operator_account":"","live_push_addr":"","faceUrl":"http://img.curiousmore.com/1579249697616.jpg","earnings":0,"group_id":"1579249627","live_duration":0,"currentPage":0,"status":1}
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"","background_pic":"","birth":1546272000000,"cash":"0.000","cat_coin":"60.000","chatting_count":0,"city":"北京市·市辖区·东城区","contacts_num":0,"country":"中国","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"北京市·市辖区·东城区","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1574328343943,"log_out_time":1574063904000,"mail":"15514766147@qq.com","member_level":0,"message_num":0,"mobile":"15514866147","mother_tongue":"Chinese","national_flag":"","nick_name":"Maxsddd","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"搞笑/习俗","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1574328317000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
             * masterinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1578585600000,"cash":"0.000","cat_coin":"21430.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1578645219497,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1578645219000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}
             */

            private TimezoneBean timezone;
            private MessageGroupEntity groupinfo;
            private UserInfoEntity userinfo;
            private UserInfoEntity masterinfo;
            private MasterAppointmentEntity masterAppointmentEntity;

            public void setMasterAppointmentEntity(MasterAppointmentEntity masterAppointmentEntity) {
                this.masterAppointmentEntity = masterAppointmentEntity;
            }

            public MasterAppointmentEntity getMasterAppointmentEntity() {
                return masterAppointmentEntity;
            }

            public TimezoneBean getTimezone() {
                return timezone;
            }

            public void setTimezone(TimezoneBean timezone) {
                this.timezone = timezone;
            }

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

            public UserInfoEntity getMasterinfo() {
                return masterinfo;
            }

            public void setMasterinfo(UserInfoEntity masterinfo) {
                this.masterinfo = masterinfo;
            }


        }
    }
}
