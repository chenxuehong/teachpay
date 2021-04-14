package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

public class StudentScheduleModel extends JsonListResult<StudentScheduleModel.StudentScheduleEntity> {
    public static class StudentScheduleEntity {

        private String id;
        private String user_id;
        private String master_id;
        private String create_time;
        private String update_time;
        private String appointment_id;
        private Integer status;
        private String start_time;
        private String end_time;
        private String update_identity;
        private Float offset;
        private String title;
        private String timezone_id;
        private String update_appointment_id;
        private String master_type;
        private String language;
        private String earnings;
        private String earnings_status;
        private String update_title;
        private String remarks;
        private String pipeline_num;
        private String service_type;
        private String offline_count;
        private String offline_mobile;
        private String student_count;
        private String offline_address;
        private String source;
        private String mechanism_id;
        private String latitude;
        private Boolean is_exclusive;
        private String longitude;
        private String study_card_id;
        private Boolean is_pay;
        private String master_set_price_id;
        private Boolean is_comment;
        private String user_study_card_id;
        private String full_name;
        private String login_name;
        private String ids;
        private String loginIDs;
        private String number_of_lessons;

        public String getNumber_of_lessons() {
            return number_of_lessons;
        }

        public String getUser_study_card_id() {
            return user_study_card_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private Map map;

        public Map getMap() {
            return map;
        }

        public static class Map {
            private MasterSetPriceEntity masterSetPriceEntity;
            private MechanismOfflineScheduleEntity masterAppointment;
            private UserInfoEntity masterinfo;
            private MasterMechanismModel.MasterMechanismEntity mechanismEntity;

            public MasterMechanismModel.MasterMechanismEntity getMechanismEntity() {
                return mechanismEntity;
            }

            public MasterSetPriceEntity getMasterSetPriceEntity() {
                return masterSetPriceEntity;
            }

            public MechanismOfflineScheduleEntity getMasterAppointment() {
                return masterAppointment;
            }

            public UserInfoEntity getMasterinfo() {
                return masterinfo;
            }
        }

        public String getId() {
            return id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getMaster_id() {
            return master_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public String getAppointment_id() {
            return appointment_id;
        }

        public Integer getStatus() {
            return status;
        }

        public String getStart_time() {
            return start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public String getUpdate_identity() {
            return update_identity;
        }

        public Float getOffset() {
            return offset;
        }

        public String getTitle() {
            return title;
        }

        public String getTimezone_id() {
            return timezone_id;
        }

        public String getUpdate_appointment_id() {
            return update_appointment_id;
        }

        public String getMaster_type() {
            return master_type;
        }

        public String getLanguage() {
            return language;
        }

        public String getEarnings() {
            return earnings;
        }

        public String getEarnings_status() {
            return earnings_status;
        }

        public String getUpdate_title() {
            return update_title;
        }

        public String getRemarks() {
            return remarks;
        }

        public String getPipeline_num() {
            return pipeline_num;
        }

        public String getService_type() {
            return service_type;
        }

        public String getOffline_count() {
            return offline_count;
        }

        public String getOffline_mobile() {
            return offline_mobile;
        }

        public String getStudent_count() {
            return student_count;
        }

        public String getOffline_address() {
            return offline_address;
        }

        public String getSource() {
            return source;
        }

        public String getMechanism_id() {
            return mechanism_id;
        }

        public String getLatitude() {
            return latitude;
        }

        public Boolean getIs_exclusive() {
            return is_exclusive;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getStudy_card_id() {
            return study_card_id;
        }

        public Boolean getIs_pay() {
            return is_pay;
        }

        public String getMaster_set_price_id() {
            return master_set_price_id;
        }

        public Boolean getIs_comment() {
            return is_comment;
        }

        public String getFull_name() {
            return full_name;
        }

        public String getLogin_name() {
            return login_name;
        }

        public String getIds() {
            return ids;
        }

        public String getLoginIDs() {
            return loginIDs;
        }
    }
}
