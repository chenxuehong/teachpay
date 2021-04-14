package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.MasterinfoBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class MasterAppointmentListModel extends JsonListResult<MasterAppointmentListModel.MasterAppointmentEntity> {

    public static class MasterAppointmentEntity {

        private String id;
        private String create_time;
        private String update_time;
        private String master_id;
        private String start_time;
        private String end_time;
        private int status;
        private String type;
        private float offset;
        private int timezone_id;
        private String title;
        private String group_id;
        private String cover;
        private String age_grade;
        private String language_level;
        private String language;
        private String group_type;
        private boolean is_appointment;
        private MapBean map;
        private String entities;
        private String timecode;
        private String teach_language;
        private String classroom_type;
        private String introduction_cover;
        private String introduction_content;
        public String service_type;
        public String mechanism_id;
        public String amout;
        public String discount;
        public String discount_amout;
        public Boolean is_discount;
        public String teach_program;
        public String latitude;
        public String longitude;
        public String recommend_type;
        public String local_offset;
        public Boolean is_teenagers;
        public String user_id;
        public String group_ids;
        public Integer connect_peoplenum;
        public Boolean is_recording;
        public String identity_type;
        public String master_type;

        public String getMaster_type() {
            return master_type;
        }

        public String getIdentity_type() {
            return identity_type;
        }

        public void setClassroom_type(String classroom_type) {
            this.classroom_type = classroom_type;
        }

        public void setIntroduction_cover(String introduction_cover) {
            this.introduction_cover = introduction_cover;
        }

        public void setIntroduction_content(String introduction_content) {
            this.introduction_content = introduction_content;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public void setMechanism_id(String mechanism_id) {
            this.mechanism_id = mechanism_id;
        }

        public void setAmout(String amout) {
            this.amout = amout;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public void setDiscount_amout(String discount_amout) {
            this.discount_amout = discount_amout;
        }

        public void setIs_discount(Boolean is_discount) {
            this.is_discount = is_discount;
        }

        public void setTeach_program(String teach_program) {
            this.teach_program = teach_program;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setRecommend_type(String recommend_type) {
            this.recommend_type = recommend_type;
        }

        public void setLocal_offset(String local_offset) {
            this.local_offset = local_offset;
        }

        public void setIs_teenagers(Boolean is_teenagers) {
            this.is_teenagers = is_teenagers;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setGroup_ids(String group_ids) {
            this.group_ids = group_ids;
        }

        public void setConnect_peoplenum(Integer connect_peoplenum) {
            this.connect_peoplenum = connect_peoplenum;
        }

        public void setIs_recording(Boolean is_recording) {
            this.is_recording = is_recording;
        }

        public String getClassroom_type() {
            return classroom_type;
        }

        public String getIntroduction_cover() {
            return introduction_cover;
        }

        public String getIntroduction_content() {
            return introduction_content;
        }

        public String getService_type() {
            return service_type;
        }

        public String getMechanism_id() {
            return mechanism_id;
        }

        public String getAmout() {
            return amout;
        }

        public String getDiscount() {
            return discount;
        }

        public String getDiscount_amout() {
            return discount_amout;
        }

        public Boolean getIs_discount() {
            return is_discount;
        }

        public String getTeach_program() {
            return teach_program;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getRecommend_type() {
            return recommend_type;
        }

        public String getLocal_offset() {
            return local_offset;
        }

        public Boolean getIs_teenagers() {
            return is_teenagers;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getGroup_ids() {
            return group_ids;
        }

        public Integer getConnect_peoplenum() {
            return connect_peoplenum;
        }

        public Boolean getIs_recording() {
            return is_recording;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public float getOffset() {
            return offset;
        }

        public void setOffset(float offset) {
            this.offset = offset;
        }

        public int getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(int timezone_id) {
            this.timezone_id = timezone_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getAge_grade() {
            return age_grade;
        }

        public void setAge_grade(String age_grade) {
            this.age_grade = age_grade;
        }

        public String getLanguage_level() {
            return language_level;
        }

        public void setLanguage_level(String language_level) {
            this.language_level = language_level;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }

        public boolean isIs_appointment() {
            return is_appointment;
        }

        public void setIs_appointment(boolean is_appointment) {
            this.is_appointment = is_appointment;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public String getEntities() {
            return entities;
        }

        public void setEntities(String entities) {
            this.entities = entities;
        }

        public String getTimecode() {
            return timecode;
        }

        public void setTimecode(String timecode) {
            this.timecode = timecode;
        }

        public String getTeach_language() {
            return teach_language;
        }

        public void setTeach_language(String teach_language) {
            this.teach_language = teach_language;
        }

        public static class MapBean {
            /**
             * studentCount : 3
             * classCount : 13
             * masterinfo : []
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1578585600000,"cash":"0.000","cat_coin":"21430.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1578645219497,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1578645219000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}
             */

            private int studentCount;
            private int classCount;

            /**
             * masterMechanismEntity : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":9,"create_time":"2020-07-01 10:51:06","update_time":"2020-07-13 20:06:04","mechanism_name":"英杰外语培训班","user_id":4260,"mechanism_addr":"杭州市杭海路601号三堡产业大厦-A座-1509","mechanism_logo":"http://img.huihejituan.com/1593572181822.png","mechanism_telephone":"13093793169","mechanism_language":"null/null","mechanism_advantage":"鱼鱼鱼，隐隐约约，古古怪怪","introduction_pic":"http://img.huihejituan.com/1593572182677.jpg,http://img.huihejituan.com/1593572183525.jpg","introduction_content":"呼呼呼吸","contacts":"陈小姐","contact_telephone":"138672660941","contact_information":"","contacts_title":"英语外教","status":2,"support_means":"http://img.huihejituan.com/1593572184303.jpg","mechanism_no":"1593571866763","sort_weight":0,"latitude":30.2758686432,"longitude":120.2342040143,"distance":null,"map":null,"type":"offline_mechanism"}
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.huihejituan.com/1586841228603.jpg","background_pic":"http://img.huihejituan.com/1594021918802.png","binding_id":0,"birth":811555200000,"cash":"0.000","cat_coin":"198816.000","chatting_count":45,"city":"顺庆区","contacts_num":2,"country":"阿尔巴尼亚","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"喀布尔","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"Polish/Danish/Arabic","like_category":"","like_num":10,"local_time":1594021919848,"log_out_time":1594012816000,"mail":"","mechanism_id":0,"member_level":1,"message_num":0,"mobile":"13956398572","mother_tongue":"Chinese","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"外语学习/交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1594021919000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
             * masterinfo : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":208,"nationality":"","user_id":4260,"language":"","create_time":"2020-07-02 17:31:48","introduction":"Ttttty","grade":1,"score":3,"address":"","update_time":"2020-07-14 17:03:04","mother_tongue":"","sex":1,"status":2,"type":"live_lecturer","identity":"","graduation_school":"","specialty":"","mobile":"13956398572","mail":"","teach_language":"Arabic","relevant_information":"http://img.curiousmore.com/983-B72A2A193143/Documents/com_tencent_imsdk_data/image/huihe_223782297,http://img.curiousmore.com/983-B72A2A193143/Documents/com_tencent_imsdk_data/image/huihe_235082298,http://img.curiousmore.com/983-B72A2A193143/Documents/com_tencent_imsdk_data/image/huihe_418882300,http://img.curiousmore.com/983-B72A2A193143/Documents/com_tencent_imsdk_data/image/huihe_312782303,http://img.curiousmore.com/983-B72A2A193143/Documents/com_tencent_imsdk_data/image/huihe_971082304","mother_tongue_url":"","language_url":"","teach_language_url":"http://img.curiousmore.com/userid4260/239011917","requirement_type":"","education":"","recommend_type":"","layout":0,"earnings":0,"recommend_video":"","cover":"","activity_type":"","popularity":0,"online":"","offline":"","service_type":3,"serve_people":"Adult/University Student/Teenagers/Childhood","language_level":"Senior/Intermediate/Primary","serve_range":0,"nhom_address":"杭州市江干区塘工局路与凤起东路交汇处-12","introduction_content":"","introduction_cover":"","max_student_count":12,"contact_information":"","charging_standard":200,"photo":"http://img.curiousmore.com/983-B72A2A193143/Documents/com_tencent_imsdk_data/image/huihe_477982294","full_name":"Kitychen","master_age":22,"course_duration":33,"mechanism_id":9,"is_self_modification":false,"stringList":null,"login_name":null,"login_pass":null,"latitude":30.278359,"longitude":120.23666,"distance":null,"price":0,"map":null,"start_time":null,"end_time":null,"nick_name":null,"idList":null,"searchParam":null,"verification_code":null}]
             */

            private MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
            private UserInfoEntity userinfo;
            private List<AppointmentinfoBean> appointmentinfo;
            private List<MasterinfoBean> masterinfo;

            public List<AppointmentinfoBean> getAppointmentinfo() {
                return appointmentinfo;
            }

            public int getStudentCount() {
                return studentCount;
            }

            public void setStudentCount(int studentCount) {
                this.studentCount = studentCount;
            }

            public int getClassCount() {
                return classCount;
            }

            public void setClassCount(int classCount) {
                this.classCount = classCount;
            }

            public MasterMechanismModel.MasterMechanismEntity getMasterMechanismEntity() {
                return masterMechanismEntity;
            }

            public void setMasterMechanismEntity(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity) {
                this.masterMechanismEntity = masterMechanismEntity;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }

            public List<MasterinfoBean> getMasterinfo() {
                return masterinfo;
            }

            public void setMasterinfo(List<MasterinfoBean> masterinfo) {
                this.masterinfo = masterinfo;
            }
        }
    }
}
