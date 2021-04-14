package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.AppointmentinfoBean;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

public class MyStudyCenterModel extends JsonListResult<MyStudyCenterModel.MyStudyCenterEntity> {
    public static class MyStudyCenterEntity {
        private Integer pageSize;
        private Integer currentPage;
        private String id;
        private String create_time;
        private String update_time;
        private String user_id;
        private String master_id;
        private String type;
        private String studycard_id;
        private String course_num;
        private Integer status;
        private String language;
        private String start_time;
        private String end_time;
        private String mechanism_id;
        private MapBean map;
        private Boolean is_experience;

        public static class MapBean {

            private String minute_count;
            private String curriculum_count;
            private String exclusive_courses_count;
            private String recording_list_count;
            private AppointmentinfoBean userAppointmentEntity;
            private MasterMechanismModel.MasterMechanismEntity mechanismEntity;
            private MasterSetPriceEntity masterSetPriceEntity;
            private UserInfoEntity masterinfo;

            public String getMinute_count() {
                return minute_count;
            }

            public String getCurriculum_count() {
                return curriculum_count;
            }

            public AppointmentinfoBean getUserAppointmentEntity() {
                return userAppointmentEntity;
            }

            public String getExclusive_courses_count() {
                return exclusive_courses_count;
            }

            public String getRecording_list_count() {
                return recording_list_count;
            }

            public MasterMechanismModel.MasterMechanismEntity getMechanismEntity() {
                return mechanismEntity;
            }

            public MasterSetPriceEntity getMasterSetPriceEntity() {
                return masterSetPriceEntity;
            }

            public UserInfoEntity getMasterinfo() {
                return masterinfo;
            }
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public String getId() {
            return id;
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

        public String getUser_id() {
            return user_id;
        }

        public String getType() {
            return type;
        }

        public String getStudycard_id() {
            return studycard_id;
        }

        public String getCourse_num() {
            return course_num;
        }

        public Integer getStatus() {
            return status;
        }

        public String getLanguage() {
            return language;
        }

        public String getStart_time() {
            return start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public String getMechanism_id() {
            return mechanism_id;
        }

        public MapBean getMap() {
            return map;
        }

        public Boolean getIs_experience() {
            return is_experience;
        }
    }
}
