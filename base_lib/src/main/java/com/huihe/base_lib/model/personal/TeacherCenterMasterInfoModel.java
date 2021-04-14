package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.MastertypeBean;
import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.ui.widget.banner.entity.SimpleBannerInfo;

import java.util.List;

public class TeacherCenterMasterInfoModel extends JsonListResult<TeacherCenterMasterInfoModel.TeacherCenterMasterInfoEntity> {

    public class TeacherCenterMasterInfoEntity extends SimpleBannerInfo {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 125
         * nationality : 中国
         * user_id : 9191
         * language : English
         * create_time : 2019-10-29 13:54:09
         * introduction : 公民哦哦
         * grade : 1
         * score : 0
         * address :
         * update_time : 2019-12-30 09:59:13
         * mother_tongue : Chinese
         * sex : 1
         * status : 2
         * type : live_lecturer
         * identity : 境外工作
         * graduation_school : 哈弗
         * specialty : 语言
         * mobile : 186123456
         * mail : 164634848
         * teach_language : Chinese
         * relevant_information :
         * mother_tongue_url : http://img.curiousmore.com/userid36/798092946
         * language_url : http://img.curiousmore.com/userid36/794944496
         * teach_language_url : http://img.curiousmore.com/userid36/798092946
         * requirement_type :
         * education : 硕士
         * recommend_type :
         * layout : 0
         * earnings : 0
         * recommend_video : http://img.curiousmore.com/userid36/798092946
         * cover : http://img.curiousmore.com/userid36/798092946?vframe/jpg/offset/0
         * activity_type :
         * popularity : 0
         * price : 0
         * map : {"studentCount":0,"totalIncome":0,"classCount":38,"monthlyEarnings":0,"courseCount":11,"residueCount":0,"groupClassCount":0,"currentStudentCount":0,"latelyclass":[],"mastertype":{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":5,"name":"直播讲师","position_details":"负责为报名学员安排课程计划,辅导学员语言学习,在线和学员视频交流口语","type":"live_lecturer","requirement":"","price":398,"class_hour":12,"class_duration":1800,"create_time":"2019-06-12 09:41:38","update_time":"2019-11-06 09:39:26","earnings":55,"proportion":0.5}}
         * start_time : null
         * end_time : null
         * nick_name : null
         * idList : null
         */


        private String id;
        private String nationality;
        private String user_id;
        private String language;
        private String create_time;
        private String introduction;
        private int grade;
        private float score;
        private String address;
        private String update_time;
        private String mother_tongue;
        private int sex;
        private int status;
        private String type;
        private String identity;
        private String graduation_school;
        private String specialty;
        private String mobile;
        private String mail;
        private String teach_language;
        private String relevant_information;
        private String mother_tongue_url;
        private String language_url;
        private String teach_language_url;
        private String requirement_type;
        private String education;
        private String recommend_type;
        private int earnings;
        private String recommend_video;
        private String cover;
        private String activity_type;
        private MapBean map;
        private Object start_time;
        private Object end_time;
        private Object nick_name;
        private Object idList;
        private String service_type;
        private String serve_people;
        private String language_level;
        private String serve_range;
        private String nhom_address;
        private String introduction_content;
        private String introduction_cover;
        private Integer max_student_count;
        private String contact_information;
        private Float charging_standard;
        private Float charging_standard_offline;
        private String photo;
        private String full_name;
        private Integer master_age;
        private Integer course_duration;
        private String mechanism_id;
        private boolean is_self_modification;
        private String stringList;
        private String login_name;
        private String login_pass;
        private String latitude;
        private String longitude;
        private String refuse_contect;
        private String minimum_pay;
        private Boolean is_special;

        public Boolean getIs_special() {
            return is_special;
        }

        public String getMinimum_pay() {
            return minimum_pay;
        }

        public Float getCharging_standard_offline() {
            return charging_standard_offline;
        }

        public void setRefuse_contect(String refuse_contect) {
            this.refuse_contect = refuse_contect;
        }

        public String getRefuse_contect() {
            return refuse_contect;
        }
        public void setMechanism_id(String mechanism_id) {
            this.mechanism_id = mechanism_id;
        }

        public void setIs_self_modification(boolean is_self_modification) {
            this.is_self_modification = is_self_modification;
        }

        public void setStringList(String stringList) {
            this.stringList = stringList;
        }

        public void setLogin_name(String login_name) {
            this.login_name = login_name;
        }

        public void setLogin_pass(String login_pass) {
            this.login_pass = login_pass;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getMechanism_id() {
            return mechanism_id;
        }

        public boolean isIs_self_modification() {
            return is_self_modification;
        }

        public String getStringList() {
            return stringList;
        }

        public String getLogin_name() {
            return login_name;
        }

        public String getLogin_pass() {
            return login_pass;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public void setServe_people(String serve_people) {
            this.serve_people = serve_people;
        }

        public void setLanguage_level(String language_level) {
            this.language_level = language_level;
        }

        public void setServe_range(String serve_range) {
            this.serve_range = serve_range;
        }

        public void setNhom_address(String nhom_address) {
            this.nhom_address = nhom_address;
        }

        public void setIntroduction_content(String introduction_content) {
            this.introduction_content = introduction_content;
        }

        public void setIntroduction_cover(String introduction_cover) {
            this.introduction_cover = introduction_cover;
        }

        public void setMax_student_count(Integer max_student_count) {
            this.max_student_count = max_student_count;
        }

        public void setContact_information(String contact_information) {
            this.contact_information = contact_information;
        }

        public void setCharging_standard(Float charging_standard) {
            this.charging_standard = charging_standard;
        }

        public void setMaster_age(int master_age) {
            this.master_age = master_age;
        }

        public void setCourse_duration(int course_duration) {
            this.course_duration = course_duration;
        }

        public String getService_type() {
            return service_type;
        }

        public String getServe_people() {
            return serve_people;
        }

        public String getLanguage_level() {
            return language_level;
        }

        public String getServe_range() {
            return serve_range;
        }

        public String getNhom_address() {
            return nhom_address;
        }

        public String getIntroduction_content() {
            return introduction_content;
        }

        public String getIntroduction_cover() {
            return introduction_cover;
        }

        public Integer getMax_student_count() {
            return max_student_count;
        }

        public String getContact_information() {
            return contact_information;
        }

        public Float getCharging_standard() {
            return charging_standard;
        }

        public int getMaster_age() {
            return master_age;
        }

        public int getCourse_duration() {
            return course_duration;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhoto() {
            return photo;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getMother_tongue() {
            return mother_tongue;
        }

        public void setMother_tongue(String mother_tongue) {
            this.mother_tongue = mother_tongue;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getGraduation_school() {
            return graduation_school;
        }

        public void setGraduation_school(String graduation_school) {
            this.graduation_school = graduation_school;
        }

        public String getSpecialty() {
            return specialty;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getTeach_language() {
            return teach_language;
        }

        public void setTeach_language(String teach_language) {
            this.teach_language = teach_language;
        }

        public String getRelevant_information() {
            return relevant_information;
        }

        public void setRelevant_information(String relevant_information) {
            this.relevant_information = relevant_information;
        }

        public String getMother_tongue_url() {
            return mother_tongue_url;
        }

        public void setMother_tongue_url(String mother_tongue_url) {
            this.mother_tongue_url = mother_tongue_url;
        }

        public String getLanguage_url() {
            return language_url;
        }

        public void setLanguage_url(String language_url) {
            this.language_url = language_url;
        }

        public String getTeach_language_url() {
            return teach_language_url;
        }

        public void setTeach_language_url(String teach_language_url) {
            this.teach_language_url = teach_language_url;
        }

        public String getRequirement_type() {
            return requirement_type;
        }

        public void setRequirement_type(String requirement_type) {
            this.requirement_type = requirement_type;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getRecommend_type() {
            return recommend_type;
        }

        public void setRecommend_type(String recommend_type) {
            this.recommend_type = recommend_type;
        }

        public int getEarnings() {
            return earnings;
        }

        public void setEarnings(int earnings) {
            this.earnings = earnings;
        }

        public String getRecommend_video() {
            return recommend_video;
        }

        public void setRecommend_video(String recommend_video) {
            this.recommend_video = recommend_video;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(String activity_type) {
            this.activity_type = activity_type;
        }


        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
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

        public Object getNick_name() {
            return nick_name;
        }

        public void setNick_name(Object nick_name) {
            this.nick_name = nick_name;
        }

        public Object getIdList() {
            return idList;
        }

        public void setIdList(Object idList) {
            this.idList = idList;
        }

        @Override
        public Object getXBannerUrl() {
            return teach_language_url;
        }

        public  class MapBean {
            /**
             * studentCount : 0
             * totalIncome : 0
             * classCount : 38
             * monthlyEarnings : 0
             * courseCount : 11
             * residueCount : 0
             * groupClassCount : 0
             * currentStudentCount : 0
             * latelyclass : []
             * mastertype : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":5,"name":"直播讲师","position_details":"负责为报名学员安排课程计划,辅导学员语言学习,在线和学员视频交流口语","type":"live_lecturer","requirement":"","price":398,"class_hour":12,"class_duration":1800,"create_time":"2019-06-12 09:41:38","update_time":"2019-11-06 09:39:26","earnings":55,"proportion":0.5}
             */

            private String studentCount;
            private String totalIncome;
            private String classCount;
            private String monthlyEarnings;
            private String courseCount;
            private String residueCount;
            private String groupClassCount;
            private String currentStudentCount;
            private MastertypeBean mastertype;
            private List<ClassBean> latelyclass;

            public String getStudentCount() {
                return studentCount;
            }

            public void setStudentCount(String studentCount) {
                this.studentCount = studentCount;
            }

            public String getTotalIncome() {
                return totalIncome;
            }

            public void setTotalIncome(String totalIncome) {
                this.totalIncome = totalIncome;
            }

            public String getClassCount() {
                return classCount;
            }

            public void setClassCount(String classCount) {
                this.classCount = classCount;
            }

            public String getMonthlyEarnings() {
                return monthlyEarnings;
            }

            public void setMonthlyEarnings(String monthlyEarnings) {
                this.monthlyEarnings = monthlyEarnings;
            }

            public String getCourseCount() {
                return courseCount;
            }

            public void setCourseCount(String courseCount) {
                this.courseCount = courseCount;
            }

            public String getResidueCount() {
                return residueCount;
            }

            public void setResidueCount(String residueCount) {
                this.residueCount = residueCount;
            }

            public String getGroupClassCount() {
                return groupClassCount;
            }

            public void setGroupClassCount(String groupClassCount) {
                this.groupClassCount = groupClassCount;
            }

            public String getCurrentStudentCount() {
                return currentStudentCount;
            }

            public void setCurrentStudentCount(String currentStudentCount) {
                this.currentStudentCount = currentStudentCount;
            }

            public MastertypeBean getMastertype() {
                return mastertype;
            }

            public void setMastertype(MastertypeBean mastertype) {
                this.mastertype = mastertype;
            }

            public List<ClassBean> getLatelyclass() {
                return latelyclass;
            }

            public void setLatelyclass(List<ClassBean> latelyclass) {
                this.latelyclass = latelyclass;
            }

        }
    }
}
