package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonResult;

import java.util.List;

public class UserAppointmentManagerModel extends JsonResult<UserAppointmentManagerModel.ItemBean> {
    public static class ItemBean {
        private Integer total;
        private List<UserAppointmentManagerEntity> rows;

        public Integer getTotal() {
            return total;
        }

        public List<UserAppointmentManagerEntity> getRows() {
            return rows;
        }

        public static class UserAppointmentManagerEntity {

            /**
             * pageSize : 10
             * currentPage : 0
             * totalItem : 0
             * startRow : 0
             * sortName : null
             * orderBy : null
             * fileds : null
             * totalPage : 0
             * id : 827
             * user_id : 4260
             * master_id : 0
             * create_time : 2020-08-28 22:01:59
             * update_time : 2020-08-28 22:01:59
             * appointment_id : 12620
             * status : 6
             * start_time : 2020-09-12 20:30:00
             * end_time : 2020-09-12 21:00:00
             * update_identity :
             * offset : 8
             * title :
             * timezone_id : 0
             * update_appointment_id : 0
             * master_type : exclusive_courses
             * language :
             * earnings : 0
             * earnings_status : 1
             * update_title :
             * remarks :
             * pipeline_num :
             * service_type :
             * offline_count : 1
             * offline_mobile :
             * student_count : 1
             * offline_address :
             * mechanism_id : 4
             * latitude : 0
             * is_exclusive : true
             * longitude : 0
             * userAppointmentEntities : null
             * identity : null
             * whether : null
             * is_apply : null
             * update_type : null
             * studyCard : null
             * masterNotice_id : null
             * statistics_time : null
             * study_card_id : 141
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
            private Float offset;
            private String title;
            private String update_appointment_id;
            private String master_type;
            private String language;
            private String update_title;
            private String remarks;
            private String pipeline_num;
            private String service_type;
            private Integer offline_count;
            private String offline_mobile;
            private Integer student_count;
            private String offline_address;
            private String mechanism_id;
            private String latitude;
            private boolean is_exclusive;
            private String longitude;
            private Object userAppointmentEntities;
            private Object identity;
            private Object whether;
            private Object is_apply;
            private Object update_type;
            private Object studyCard;
            private Object masterNotice_id;
            private Object statistics_time;
            private String study_card_id;
            private MapBean map;

            public MapBean getMap() {
                return map;
            }

            public static class MapBean {
                private String identity_name;
                private UserInfoEntity userinfo;
                private UserInfoEntity masterinfo;

                public String getIdentity_name() {
                    return identity_name;
                }

                public UserInfoEntity getUserinfo() {
                    return userinfo;
                }

                public UserInfoEntity getMasterinfo() {
                    return masterinfo;
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

            public Float getOffset() {
                return offset;
            }

            public void setOffset(Float offset) {
                this.offset = offset;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getService_type() {
                return service_type;
            }

            public void setService_type(String service_type) {
                this.service_type = service_type;
            }

            public int getOffline_count() {
                return offline_count;
            }

            public void setOffline_count(int offline_count) {
                this.offline_count = offline_count;
            }

            public String getOffline_mobile() {
                return offline_mobile;
            }

            public void setOffline_mobile(String offline_mobile) {
                this.offline_mobile = offline_mobile;
            }

            public int getStudent_count() {
                return student_count;
            }

            public void setStudent_count(int student_count) {
                this.student_count = student_count;
            }

            public String getOffline_address() {
                return offline_address;
            }

            public void setOffline_address(String offline_address) {
                this.offline_address = offline_address;
            }

            public String getMechanism_id() {
                return mechanism_id;
            }

            public void setMechanism_id(String mechanism_id) {
                this.mechanism_id = mechanism_id;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public boolean isIs_exclusive() {
                return is_exclusive;
            }

            public void setIs_exclusive(boolean is_exclusive) {
                this.is_exclusive = is_exclusive;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
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

            public String getStudy_card_id() {
                return study_card_id;
            }

            public void setStudy_card_id(String study_card_id) {
                this.study_card_id = study_card_id;
            }
        }
    }
}
