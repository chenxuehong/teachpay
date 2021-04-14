package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class UserAppointHistoryModel extends JsonListResult<UserAppointHistoryModel.UserAppointHistoryEntity> {
    public static class UserAppointHistoryEntity {


        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 281
         * user_id : 9099
         * master_id : 9191
         * create_time : 2020-05-16 19:13:34
         * update_time : 2020-05-18 10:13:27
         * appointment_id : 6472
         * status : 1
         * start_time : 2020-05-16 22:00:00
         * end_time : 2020-05-16 22:30:00
         * update_identity : user
         * title : Topic/Talk about dressing
         * offset : 8
         * timezone_id : 324
         * update_appointment_id : 0
         * master_type : private_education
         * language : Russian
         * earnings : 55
         * earnings_status : 1
         * update_title :
         * remarks :
         * pipeline_num : PT20200516191334160429119
         * userAppointmentEntities : null
         * identity : null
         * whether : null
         * is_apply : null
         * update_type : null
         * studyCard : null
         * masterNotice_id : null
         * statistics_time : null
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
        private String update_identity;
        private String title;
        private float offset;
        private String timezone_id;
        private String update_appointment_id;
        private String master_type;
        private String language;
        private String earnings;
        private int earnings_status;
        private String update_title;
        private String remarks;
        private String pipeline_num;
        private Object userAppointmentEntities;
        private Object identity;
        private Object whether;
        private Object is_apply;
        private Object update_type;
        private Object studyCard;
        private Object masterNotice_id;
        private Object statistics_time;
        private String service_type;
        private int offline_count;
        private String offline_mobile;
        private int student_count;
        private String offline_address;
        private Map map;

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public void setOffline_count(int offline_count) {
            this.offline_count = offline_count;
        }

        public void setOffline_mobile(String offline_mobile) {
            this.offline_mobile = offline_mobile;
        }

        public void setStudent_count(int student_count) {
            this.student_count = student_count;
        }

        public void setOffline_address(String offline_address) {
            this.offline_address = offline_address;
        }

        public String getService_type() {
            return service_type;
        }

        public int getOffline_count() {
            return offline_count;
        }

        public String getOffline_mobile() {
            return offline_mobile;
        }

        public int getStudent_count() {
            return student_count;
        }

        public String getOffline_address() {
            return offline_address;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        public static class Map{
            private UserInfoEntity userinfo;
            private UserInfoEntity masterinfo;
            private MasterAppointmentEntity masterAppointmentEntity;
            private List<SummaryDetail> summaryinfo;
            public List<SummaryDetail> getSummaryinfo() {
                return summaryinfo;
            }
            private List<PrivateMasterInfoEntity> masterInfoEntities;

            public void setMasterInfoEntities(List<PrivateMasterInfoEntity> masterInfoEntities) {
                this.masterInfoEntities = masterInfoEntities;
            }

            public List<PrivateMasterInfoEntity> getMasterInfoEntities() {
                return masterInfoEntities;
            }

            public void setSummaryinfo(List<SummaryDetail> summaryinfo) {
                this.summaryinfo = summaryinfo;
            }
            public void setMasterAppointmentEntity(MasterAppointmentEntity masterAppointmentEntity) {
                this.masterAppointmentEntity = masterAppointmentEntity;
            }

            public MasterAppointmentEntity getMasterAppointmentEntity() {
                return masterAppointmentEntity;
            }

            public void setMasterinfo(UserInfoEntity masterinfo) {
                this.masterinfo = masterinfo;
            }

            public UserInfoEntity getMasterinfo() {
                return masterinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }
        }


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

        public String getUpdate_identity() {
            return update_identity;
        }

        public void setUpdate_identity(String update_identity) {
            this.update_identity = update_identity;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getOffset() {
            return offset;
        }

        public void setOffset(float offset) {
            this.offset = offset;
        }

        public String getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(String timezone_id) {
            this.timezone_id = timezone_id;
        }

        public String getUpdate_appointment_id() {
            return update_appointment_id;
        }

        public void setUpdate_appointment_id(String update_appointment_id) {
            this.update_appointment_id = update_appointment_id;
        }

        public String getMaster_type() {
            return master_type;
        }

        public void setMaster_type(String master_type) {
            this.master_type = master_type;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getEarnings() {
            return earnings;
        }

        public void setEarnings(String earnings) {
            this.earnings = earnings;
        }

        public int getEarnings_status() {
            return earnings_status;
        }

        public void setEarnings_status(int earnings_status) {
            this.earnings_status = earnings_status;
        }

        public String getUpdate_title() {
            return update_title;
        }

        public void setUpdate_title(String update_title) {
            this.update_title = update_title;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getPipeline_num() {
            return pipeline_num;
        }

        public void setPipeline_num(String pipeline_num) {
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
    }
}
