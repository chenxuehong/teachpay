package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

public class StudentListModel extends JsonListResult<StudentListModel.StudentInfoEntity> {

    public class StudentInfoEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : null
         * user_id : 9120
         * master_id : null
         * create_time : null
         * update_time : null
         * appointment_id : null
         * status : null
         * start_time : null
         * end_time : null
         * update_identity : null
         * title : null
         * offset : null
         * timezone_id : null
         * update_appointment_id : null
         * master_type : null
         * language : null
         * earnings : null
         * earnings_status : null
         * update_title : null
         * remarks : null
         * pipeline_num : null
         * userAppointmentEntities : null
         * identity : null
         * whether : null
         * map : {"notCount":22,"totalCourse":73,"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/5415C8D6-5088-41EF-9CBA-2022C0E6A559/Documents/1huihe1558927624.png","background_pic":"http://img.curiousmore.com/CAB-378A08DEFFB3/Documents/1huihe1577450667.png","birth":1558886400000,"cash":"0.000","cat_coin":"410.000","chatting_count":220,"city":"沙迦","contacts_num":173,"country":"外教","create_time":1558927515000,"distrib_qrcode":"","duration":0,"hometown":"马扎里沙里夫","identity_auth":true,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"汉语/英语/法语","like_category":"","like_num":1,"local_time":1577927056567,"log_out_time":1571189258000,"mail":"","member_level":2,"message_num":0,"mobile":"13296728663","mother_tongue":"Chinese","national_flag":"","nick_name":"可口可乐","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"交友拍拖","qrcode":"","rating":7,"registr_num":0,"sex":2,"signature":"","update_time":1577927056000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9120","user_id":9120}}
         * is_apply : null
         * update_type : null
         * studyCard : null
         * masterNotice_id : null
         * statistics_time : null
         */

        private String id;
        private String user_id;
        private String master_id;
        private Object create_time;
        private Object update_time;
        private Object appointment_id;
        private Object status;
        private Object start_time;
        private Object end_time;
        private Object update_identity;
        private Object title;
        private Object offset;
        private Object timezone_id;
        private Object update_appointment_id;
        private Object master_type;
        private Object language;
        private String earnings;
        private Object earnings_status;
        private Object update_title;
        private Object remarks;
        private Object pipeline_num;
        private Object userAppointmentEntities;
        private Object identity;
        private Object whether;
        private MapBean map;
        private Object is_apply;
        private Object update_type;
        private Object studyCard;
        private Object masterNotice_id;
        private Object statistics_time;

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

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public Object getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(Object appointment_id) {
            this.appointment_id = appointment_id;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public Object getEnd_time() {
            return end_time;
        }

        public void setEnd_time(Object end_time) {
            this.end_time = end_time;
        }

        public Object getUpdate_identity() {
            return update_identity;
        }

        public void setUpdate_identity(Object update_identity) {
            this.update_identity = update_identity;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getOffset() {
            return offset;
        }

        public void setOffset(Object offset) {
            this.offset = offset;
        }

        public Object getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(Object timezone_id) {
            this.timezone_id = timezone_id;
        }

        public Object getUpdate_appointment_id() {
            return update_appointment_id;
        }

        public void setUpdate_appointment_id(Object update_appointment_id) {
            this.update_appointment_id = update_appointment_id;
        }

        public Object getMaster_type() {
            return master_type;
        }

        public void setMaster_type(Object master_type) {
            this.master_type = master_type;
        }

        public Object getLanguage() {
            return language;
        }

        public void setLanguage(Object language) {
            this.language = language;
        }

        public String getEarnings() {
            return earnings;
        }

        public void setEarnings(String earnings) {
            this.earnings = earnings;
        }

        public Object getEarnings_status() {
            return earnings_status;
        }

        public void setEarnings_status(Object earnings_status) {
            this.earnings_status = earnings_status;
        }

        public Object getUpdate_title() {
            return update_title;
        }

        public void setUpdate_title(Object update_title) {
            this.update_title = update_title;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public Object getPipeline_num() {
            return pipeline_num;
        }

        public void setPipeline_num(Object pipeline_num) {
            this.pipeline_num = pipeline_num;
        }

        public Object getUserAppointmentEntities() {
            return userAppointmentEntities;
        }

        public void setUserAppointmentEntities(Object userAppointmentEntities) {
            this.userAppointmentEntities = userAppointmentEntities;
        }

        public Object getIdentity() {
            return identity;
        }

        public void setIdentity(Object identity) {
            this.identity = identity;
        }

        public Object getWhether() {
            return whether;
        }

        public void setWhether(Object whether) {
            this.whether = whether;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public Object getIs_apply() {
            return is_apply;
        }

        public void setIs_apply(Object is_apply) {
            this.is_apply = is_apply;
        }

        public Object getUpdate_type() {
            return update_type;
        }

        public void setUpdate_type(Object update_type) {
            this.update_type = update_type;
        }

        public Object getStudyCard() {
            return studyCard;
        }

        public void setStudyCard(Object studyCard) {
            this.studyCard = studyCard;
        }

        public Object getMasterNotice_id() {
            return masterNotice_id;
        }

        public void setMasterNotice_id(Object masterNotice_id) {
            this.masterNotice_id = masterNotice_id;
        }

        public Object getStatistics_time() {
            return statistics_time;
        }

        public void setStatistics_time(Object statistics_time) {
            this.statistics_time = statistics_time;
        }

        public  class MapBean {
            /**
             * notCount : 22
             * totalCourse : 73
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/5415C8D6-5088-41EF-9CBA-2022C0E6A559/Documents/1huihe1558927624.png","background_pic":"http://img.curiousmore.com/CAB-378A08DEFFB3/Documents/1huihe1577450667.png","birth":1558886400000,"cash":"0.000","cat_coin":"410.000","chatting_count":220,"city":"沙迦","contacts_num":173,"country":"外教","create_time":1558927515000,"distrib_qrcode":"","duration":0,"hometown":"马扎里沙里夫","identity_auth":true,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"汉语/英语/法语","like_category":"","like_num":1,"local_time":1577927056567,"log_out_time":1571189258000,"mail":"","member_level":2,"message_num":0,"mobile":"13296728663","mother_tongue":"Chinese","national_flag":"","nick_name":"可口可乐","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"交友拍拖","qrcode":"","rating":7,"registr_num":0,"sex":2,"signature":"","update_time":1577927056000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9120","user_id":9120}
             */

            private String notCount;
            private String totalCourse;
            private String userstudycount;
            private UserInfoEntity userinfo;

            public String getUserstudycount() {
                return userstudycount;
            }

            public String getNotCount() {
                return notCount;
            }

            public void setNotCount(String notCount) {
                this.notCount = notCount;
            }

            public String getTotalCourse() {
                return totalCourse;
            }

            public void setTotalCourse(String totalCourse) {
                this.totalCourse = totalCourse;
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
