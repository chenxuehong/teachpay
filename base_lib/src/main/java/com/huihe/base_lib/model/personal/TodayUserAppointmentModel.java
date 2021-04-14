package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.MasterinfoBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonResult;
import com.huihe.base_lib.model.home.MessageGroupEntity;

import java.util.List;

public class TodayUserAppointmentModel extends JsonResult<TodayUserAppointmentModel.TodayUserAppointmentEntity> {
    public static class TodayUserAppointmentEntity {

        public List<MotherTongueInfo> mother_tongue_info;
        public List<MotherTongueInfo> major_info;
        public List<MotherTongueInfo> cross_border_info;
        public List<PrivateEducationInfoEntity> private_education_info;
        public List<LiveAppointment> live_appointment;

        public List<MotherTongueInfo> getCross_border_info() {
            return cross_border_info;
        }

        public List<MotherTongueInfo> getMajor_info() {
            return major_info;
        }

        public List<PrivateEducationInfoEntity> getPrivate_education_info() {
            return private_education_info;
        }

        public List<MotherTongueInfo> getMother_tongue_info() {
            return mother_tongue_info;
        }

        public List<LiveAppointment> getLive_appointment() {
            return live_appointment;
        }

        public static class MotherTongueInfo{

            public String id;
            public String user_id;
            public String master_id;
            public String create_time;
            public String update_time;
            public String appointment_id;
            public int status;
            public String start_time;
            public String end_time;
            public String update_identity;
            public Float offset;
            public String title;
            public String update_appointment_id;
            public String master_type;
            public String language;
            public Float earnings;
            public int earnings_status;
            public String update_title;
            public String remarks;
            public String pipeline_num;
            public String service_type;
            public String offline_count;
            public String offline_mobile;
            public String student_count;
            public String offline_address;
            public String mechanism_id;
            public String latitude;
            public String longitude;
            public Object userAppointmentEntities;
            public Object identity;
            public boolean whether;
            public Object is_apply;
            public Object update_type;
            public Object studyCard;
            public Object masterNotice_id;
            public Object statistics_time;
            public MapBean map;
        }

        public static class PrivateEducationInfoEntity {

            public String id;
            public String user_id;
            public String master_id;
            public String create_time;
            public String update_time;
            public String appointment_id;
            public int status;
            public String start_time;
            public String end_time;
            public String update_identity;
            public Float offset;
            public String title;
            public String update_appointment_id;
            public String master_type;
            public String language;
            public Float earnings;
            public int earnings_status;
            public String update_title;
            public String remarks;
            public String pipeline_num;
            public String service_type;
            public String offline_count;
            public String offline_mobile;
            public String student_count;
            public String offline_address;
            public String mechanism_id;
            public String latitude;
            public String longitude;
            public Object userAppointmentEntities;
            public String identity;
            public boolean whether;
            public Object is_apply;
            public Object update_type;
            public Object studyCard;
            public Object masterNotice_id;
            public Object statistics_time;
            public MapBean map;
            public MapBean getMap() {
                return map;
            }

        }

        public static class LiveAppointment {
            public String id;
            public String create_time;
            public String update_time;
            public String master_id;
            public String start_time;
            public String end_time;
            public Integer status;
            public String type;
            public Float offset;
            public String title;
            public String group_id;
            public String cover;
            public String age_grade;
            public String language_level;
            public String language;
            public String group_type;
            public boolean is_appointment;
            public Object entities;
            public Object timecode;
            public Object teach_language;
            public String classroom_type;
            public String introduction_cover;
            public String introduction_content;
            public String service_type;
            public String mechanism_id;
            public String amout;
            public String discount;
            public String discount_amout;
            public boolean is_discount;
            public String teach_program;
            public String latitude;
            public String longitude;
            public String recommend_type;
            public Object local_offset;
            public Boolean is_teenagers;
            public String user_id;
            public Object group_ids;
            public Integer connect_peoplenum;
            public boolean is_recording;
            public MapBean map;
            public MapBean getMap() {
                return map;
            }
        }
    }
    public static class MapBean {
        public MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
        public MessageGroupEntity groupinfo;
        public UserInfoEntity masterinfo;
        public UserInfoEntity userinfo;
        public MasterAppointmentEntity masterAppointmentEntity;
        public List<MasterinfoBean> masterInfoEntities;
        public MasterMechanismModel.MasterMechanismEntity getMasterMechanismEntity() {
            return masterMechanismEntity;
        }

        public MessageGroupEntity getGroupinfo() {
            return groupinfo;
        }

        public UserInfoEntity getMasterinfo() {
            return masterinfo;
        }
    }


}
