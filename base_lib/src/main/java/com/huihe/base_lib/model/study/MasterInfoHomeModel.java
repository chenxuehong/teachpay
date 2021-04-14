package com.huihe.base_lib.model.study;

import com.huihe.base_lib.model.MastertypeBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.AppointmentinfoBean;

import java.util.List;

public class MasterInfoHomeModel extends JsonListResult<MasterInfoHomeModel.MasterInfoHomeEntity> {
    public static class MasterInfoHomeEntity {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 130
         * nationality : 中国
         * user_id : 4260
         * language : English
         * create_time : 2019-10-29 13:54:09
         * introduction : 公民哦哦
         * grade : 1
         * score : 0
         * address :
         * update_time : 2020-01-13 22:48:02
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
         * recommend_type : hot
         * layout : 3
         * earnings : 0
         * recommend_video : http://img.curiousmore.com/userid36/798092946
         * cover : http://img.curiousmore.com/userid36/798092946?vframe/jpg/offset/0
         * activity_type :
         * popularity : 0
         * price : 0
         * map : {"studentCount":0,"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://www.huihejituan.com/tripPictstorage/user/2214/play/f49f0994-e4f7-4de8-807e-b0b400b1e028_200_200.jpg","background_pic":"http://img.curiousmore.com/A8F-713757C0D227/Documents/1huihe1576739038.png","birth":811555200000,"cash":"0.000","cat_coin":"20.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"尼加拉瓜","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":0,"local_time":1576738858472,"log_out_time":1576658501000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"跑腿代购","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1576738858000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}}
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
        private Integer grade;
        private float score;
        private String address;
        private String update_time;
        private String mother_tongue;
        private String sex;
        private String status;
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
        private Float earnings;
        private Boolean is_special;
        private String recommend_video;
        private String cover;
        private String activity_type;
        private String online;
        private String offline;
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
        private Boolean is_self_modification;
        private String stringList;
        private String login_name;
        private String login_pass;
        private String latitude;
        private String longitude;
        private String distance;
        private String minimum_pay;
        private String price;
        private MapBean map;

        public String getMinimum_pay() {
            return minimum_pay;
        }

        public Float getCharging_standard_offline() {
            return charging_standard_offline;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public void setOffline(String offline) {
            this.offline = offline;
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

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public void setMaster_age(Integer master_age) {
            this.master_age = master_age;
        }

        public void setCourse_duration(Integer course_duration) {
            this.course_duration = course_duration;
        }

        public void setMechanism_id(String mechanism_id) {
            this.mechanism_id = mechanism_id;
        }

        public void setIs_self_modification(Boolean is_self_modification) {
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

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getOnline() {
            return online;
        }

        public String getOffline() {
            return offline;
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

        public String getPhoto() {
            return photo;
        }

        public String getFull_name() {
            return full_name;
        }

        public Integer getMaster_age() {
            return master_age;
        }

        public Integer getCourse_duration() {
            return course_duration;
        }

        public String getMechanism_id() {
            return mechanism_id;
        }

        public Boolean getIs_self_modification() {
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

        public String getDistance() {
            return distance;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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


        public Float getEarnings() {
            return earnings;
        }

        public void setEarnings(Float earnings) {
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public static class MapBean {
            /**
             * studentCount : 0
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://www.huihejituan.com/tripPictstorage/user/2214/play/f49f0994-e4f7-4de8-807e-b0b400b1e028_200_200.jpg","background_pic":"http://img.curiousmore.com/A8F-713757C0D227/Documents/1huihe1576739038.png","birth":811555200000,"cash":"0.000","cat_coin":"20.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"尼加拉瓜","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":0,"local_time":1576738858472,"log_out_time":1576658501000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"跑腿代购","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1576738858000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
             */

            private String studentCount;
            private String totalIncome;
            private String classCount;
            private String monthlyEarnings;
            private String courseCount;
            private String residueCount;
            private String currentStudentCount;
            private List<AppointmentinfoBean> latelyclass;
            private UserInfoEntity userinfo;
            private MastertypeBean mastertype;

            public void setTotalIncome(String totalIncome) {
                this.totalIncome = totalIncome;
            }

            public void setClassCount(String classCount) {
                this.classCount = classCount;
            }

            public void setMonthlyEarnings(String monthlyEarnings) {
                this.monthlyEarnings = monthlyEarnings;
            }

            public void setCourseCount(String courseCount) {
                this.courseCount = courseCount;
            }

            public void setResidueCount(String residueCount) {
                this.residueCount = residueCount;
            }

            public void setCurrentStudentCount(String currentStudentCount) {
                this.currentStudentCount = currentStudentCount;
            }

            public void setLatelyclass(List<AppointmentinfoBean> latelyclass) {
                this.latelyclass = latelyclass;
            }

            public List<AppointmentinfoBean> getLatelyclass() {
                return latelyclass;
            }

            public String getTotalIncome() {
                return totalIncome;
            }

            public String getClassCount() {
                return classCount;
            }

            public String getMonthlyEarnings() {
                return monthlyEarnings;
            }

            public String getCourseCount() {
                return courseCount;
            }

            public String getResidueCount() {
                return residueCount;
            }

            public String getCurrentStudentCount() {
                return currentStudentCount;
            }

            public String getStudentCount() {
                return studentCount;
            }

            public void setStudentCount(String studentCount) {
                this.studentCount = studentCount;
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
