package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.MasterinfoBean;
import com.huihe.base_lib.model.RecordingInfoBean;
import com.huihe.base_lib.model.TimezoneBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.home.MessageGroupEntity;

import java.math.BigDecimal;
import java.util.List;

public class MasterAppointmentEntity {

    private String id;
    private String create_time;
    private String update_time;
    private String master_id;
    private String start_time;
    private String end_time;
    private Integer status;
    private String type;
    private Float offset;
    private String title;
    private String group_id;
    private String cover;
    private String age_grade;
    private String language_level;
    private String language;
    private String group_type;
    private Boolean is_appointment;
    private MapBean map;
    private Object entities;
    private Object timecode;
    private String teach_language;
    private String classroom_type;
    private String introduction_cover;
    private String introduction_content;
    public String service_type;
    public String mechanism_id;
    public String master_set_price_id;
    public String amout;
    public String discount;
    public String discount_amout;
    public String is_discount;
    public String teach_program;
    public String latitude;
    public String longitude;
    public String recommend_type;
    public String local_offset;
    public Boolean is_teenagers;
    public Integer user_id;
    public List<String> group_ids;
    public Integer connect_peoplenum;
    public Boolean is_recording;
    public String identity_type;
    public String room_id;
    public Integer like_count;
    public Integer comment_count;
    public Integer share_count;
    public Boolean is_special;

    public String getMaster_set_price_id() {
        return master_set_price_id;
    }

    public Boolean getIs_special() {
        return is_special;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public void setShare_count(Integer share_count) {
        this.share_count = share_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public Integer getShare_count() {
        return share_count;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public String getIdentity_type() {
        return identity_type;
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

    public String getIs_discount() {
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

    public Boolean getIs_recording() {
        return is_recording;
    }
    public String getService_type() {
        return service_type;
    }

    public Integer getConnect_peoplenum() {
        return connect_peoplenum;
    }

    public void setConnect_peoplenum(Integer connect_peoplenum) {
        this.connect_peoplenum = connect_peoplenum;
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

    public Boolean isIs_appointment() {
        return is_appointment;
    }

    public void setIs_appointment(Boolean is_appointment) {
        this.is_appointment = is_appointment;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public Object getEntities() {
        return entities;
    }

    public void setEntities(Object entities) {
        this.entities = entities;
    }

    public Object getTimecode() {
        return timecode;
    }

    public void setTimecode(Object timecode) {
        this.timecode = timecode;
    }

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
    }

    public String getClassroom_type() {
        return classroom_type;
    }

    public void setClassroom_type(String classroom_type) {
        this.classroom_type = classroom_type;
    }

    public String getIntroduction_cover() {
        return introduction_cover;
    }

    public void setIntroduction_cover(String introduction_cover) {
        this.introduction_cover = introduction_cover;
    }

    public String getIntroduction_content() {
        return introduction_content;
    }

    public void setIntroduction_content(String introduction_content) {
        this.introduction_content = introduction_content;
    }

    public String getLocal_offset() {
        return local_offset;
    }

    public void setLocal_offset(String local_offset) {
        this.local_offset = local_offset;
    }

    public Boolean getIs_teenagers() {
        return is_teenagers;
    }

    public void setIs_teenagers(Boolean is_teenagers) {
        this.is_teenagers = is_teenagers;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<String> getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(List<String> group_ids) {
        this.group_ids = group_ids;
    }

    private String profit;

    public String getProfit() {
        return profit;
    }

    public static class MapBean {
        /**
         * appointmentCount : 32
         * appointmentuserinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.huihejituan.com/1586841228603.jpg","background_pic":"http://img.huihejituan.com/1589006639804.jpg","birth":811555200000,"cash":"0.000","cat_coin":"199116.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":10,"local_time":1589437836133,"log_out_time":1589430439000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","pay_pass":"","preference":"外语学习/交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1589425883000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
         * appointmentinfo : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":270,"user_id":4260,"master_id":9191,"create_time":"2020-05-16 14:47:22","update_time":"2020-05-16 14:47:22","appointment_id":6446,"status":2,"start_time":"2020-05-16 15:30:00","end_time":"2020-05-16 16:00:00","update_identity":"","title":"Scene broadcast/Work and work scenes","offset":8,"timezone_id":324,"update_appointment_id":0,"master_type":"cross_border","language":"Chinese","earnings":30,"earnings_status":1,"update_title":"","remarks":"","pipeline_num":"PT20200516144722328773547","userAppointmentEntities":null,"identity":null,"whether":null,"map":null,"is_apply":null,"update_type":null,"studyCard":null,"masterNotice_id":null,"statistics_time":null}]
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.huihejituan.com/1588135033784.jpg","background_pic":"http://img.huihejituan.com/1589503809857.png","birth":1585872000000,"cash":"0.000","cat_coin":"1065.000","chatting_count":0,"city":"艾因·德夫拉","contacts_num":2,"country":"匈牙利","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"阿拉伯联合酋长国·迪拜","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":true,"is_mail":true,"is_member":false,"is_mobile":true,"is_name":true,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"Chinese/Korean","like_category":"","like_num":21,"local_time":1589503811245,"log_out_time":1589442952000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Russian","national_flag":"","nick_name":"心语星愿","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖/外语学习","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1589503811000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
         */
        public MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
        public MessageGroupEntity groupinfo;
        private int appointmentCount;
        private BigDecimal profit_total;
        private BigDecimal gift_total;
        private BigDecimal finalEarn;
        private BigDecimal earnTotal;
        private int studentCount;
        private int classCount;
        private Boolean isFollow;
        private Boolean isLike;
        private String recordingUrl;
        private String teacherIsLiving;
        private Map saleCourseMap;

        public BigDecimal getEarnTotal() {
            return earnTotal;
        }

        public Map getSaleCourseMap() {
            return saleCourseMap;
        }

        public static class Map{

            /**
             * total : 0
             * cash : 0
             */

            private BigDecimal total;
            private BigDecimal cash;

            public BigDecimal getTotal() {
                return total;
            }

            public void setTotal(BigDecimal total) {
                this.total = total;
            }

            public BigDecimal getCash() {
                return cash;
            }

            public void setCash(BigDecimal cash) {
                this.cash = cash;
            }
        }
        public BigDecimal getProfit_total() {
            return profit_total;
        }

        public void setFollow(Boolean isFollow) {
            this.isFollow = isFollow;
        }

        public void setLike(Boolean isLike) {
            this.isLike = isLike;
        }

        public void setTeacherIsLiving(String teacherIsLiving) {
            this.teacherIsLiving = teacherIsLiving;
        }

        public Boolean getFollow() {
            return isFollow;
        }

        public Boolean getLike() {
            return isLike;
        }

        public String getRecordingUrl() {
            return recordingUrl;
        }

        public String getTeacherIsLiving() {
            return teacherIsLiving;
        }

        public BigDecimal getFinalEarn() {
            return finalEarn;
        }

        public BigDecimal getGift_total() {
            return gift_total;
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
        private UserInfoEntity appointmentuserinfo;
        private UserInfoEntity userinfo;
        private UserInfoEntity masterinfo;
        public RecordingInfoBean recording_info;
        private TimezoneBean timezone;
        private List<MasterinfoBean> masterInfoEntities;

        public UserInfoEntity getMasterinfo() {
            return masterinfo;
        }

        public RecordingInfoBean getRecording_info() {
            return recording_info;
        }

        public void setMasterInfoEntities(List<MasterinfoBean> masterInfoEntities) {
            this.masterInfoEntities = masterInfoEntities;
        }

        public List<MasterinfoBean> getMasterInfoEntities() {
            return masterInfoEntities;
        }

        public void setTimezone(TimezoneBean timezone) {
            this.timezone = timezone;
        }

        public TimezoneBean getTimezone() {
            return timezone;
        }

        private List<AppointmentinfoBean> appointmentinfo;
        private List<AppointmentinfoBean> userAppointmentinfo;

        public List<AppointmentinfoBean> getUserAppointmentinfo() {
            return userAppointmentinfo;
        }

        private int peopleNum;
        private String profit;
        private int commentCoumt;

        public void setPeopleNum(int peopleNum) {
            this.peopleNum = peopleNum;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }

        public void setCommentCoumt(int commentCoumt) {
            this.commentCoumt = commentCoumt;
        }

        public int getPeopleNum() {
            return peopleNum;
        }

        public String getProfit() {
            return profit;
        }

        public int getCommentCoumt() {
            return commentCoumt;
        }

        public MessageGroupEntity getGroupinfo() {
            return groupinfo;
        }

        public void setGroupinfo(MessageGroupEntity messageGroupEntity) {
            this.groupinfo = messageGroupEntity;
        }

        public int getAppointmentCount() {
            return appointmentCount;
        }

        public void setAppointmentCount(int appointmentCount) {
            this.appointmentCount = appointmentCount;
        }

        public UserInfoEntity getAppointmentuserinfo() {
            return appointmentuserinfo;
        }

        public void setAppointmentuserinfo(UserInfoEntity appointmentuserinfo) {
            this.appointmentuserinfo = appointmentuserinfo;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        private List<CommentEntity> masterCommentEntities;

        public void setMasterMechanismEntity(MasterMechanismModel.MasterMechanismEntity masterMechanismEntity) {
            this.masterMechanismEntity = masterMechanismEntity;
        }

        public void setMasterCommentEntities(List<CommentEntity> masterCommentEntities) {
            this.masterCommentEntities = masterCommentEntities;
        }

        public MasterMechanismModel.MasterMechanismEntity getMasterMechanismEntity() {
            return masterMechanismEntity;
        }

        public List<CommentEntity> getMasterCommentEntities() {
            return masterCommentEntities;
        }

        public void setUserinfo(UserInfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public List<AppointmentinfoBean> getAppointmentinfo() {
            return appointmentinfo;
        }

        public void setAppointmentinfo(List<AppointmentinfoBean> appointmentinfo) {
            this.appointmentinfo = appointmentinfo;
        }

    }
}
