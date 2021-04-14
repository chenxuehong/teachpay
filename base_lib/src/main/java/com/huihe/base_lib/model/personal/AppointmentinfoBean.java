package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.TimezoneBean;
import com.huihe.base_lib.model.UserInfoEntity;

import java.util.List;

public class AppointmentinfoBean {
    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 159
     * user_id : 9191
     * master_id : 9099
     * create_time : 2020-02-11 13:10:12
     * update_time : 2020-02-11 13:10:12
     * appointment_id : 1072
     * status : 2
     * start_time : 2020-02-11 14:00:00
     * end_time : 2020-02-11 14:30:00
     * update_identity :
     * title : 话题交流/自我介绍
     * offset : 8
     * timezone_id : 324
     * update_appointment_id : 0
     * master_type : major
     * language : 汉语
     * earnings : 25
     * earnings_status : 1
     * update_title :
     * remarks :
     * pipeline_num : PT20200211131012878786558
     * userAppointmentEntities : null
     * identity : null
     * whether : null
     * map : null
     * is_apply : null
     * update_type : null
     * studyCard : null
     * masterNotice_id : null
     * statistics_time : null
     */

    private String sortName;
    private String orderBy;
    private String fileds;
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
    private String title;
    private Float offset;
    private String timezone_id;
    private String update_appointment_id;
    private String master_type;
    private String language;
    private String earnings;
    private Integer earnings_status;
    private String update_title;
    private String remarks;
    private String pipeline_num;
    private Object userAppointmentEntities;
    private String identity;
    private Object whether;
    private MapBean map;
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
    private int course_duration;
    private String latitude;
    private String longitude;
    private Boolean is_exclusive;
    public Integer connect_peoplenum;

    public Boolean getIs_exclusive() {
        return is_exclusive;
    }

    public void setConnect_peoplenum(Integer connect_peoplenum) {
        this.connect_peoplenum = connect_peoplenum;
    }

    public Integer getEarnings_status() {
        return earnings_status;
    }

    public Integer getConnect_peoplenum() {
        return connect_peoplenum;
    }

    public int getOffline_count() {
        return offline_count;
    }

    public String getOffline_mobile() {
        return offline_mobile;
    }

    public int getCourse_duration() {
        return course_duration;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setOffline_address(String offline_address) {
        this.offline_address = offline_address;
    }

    public void setStudent_count(int student_count) {
        this.student_count = student_count;
    }

    public int getStudent_count() {
        return student_count;
    }

    public String getOffline_address() {
        return offline_address;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_type() {
        return service_type;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getFileds() {
        return fileds;
    }

    public void setFileds(String fileds) {
        this.fileds = fileds;
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

    public Float getOffset() {
        return offset;
    }

    public void setOffset(Float offset) {
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
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

    public static class MapBean {
        /**
         * identity_name : 老师
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584535512217.jpg","birth":1585872000000,"cash":"0.000","cat_coin":"946.000","chatting_count":0,"city":"爱尔巴桑","contacts_num":2,"country":"阿尔巴尼亚","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"中国·北京","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":true,"is_mail":true,"is_member":false,"is_mobile":true,"is_name":true,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1587274161285,"log_out_time":1587274026000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Polish","national_flag":"","nick_name":"kitychen1","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1587274158000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
         * masterinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.huihejituan.com/1586841228603.jpg","background_pic":"http://img.curiousmore.com/1584354512798.jpg","birth":811555200000,"cash":"0.000","cat_coin":"299.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":8,"local_time":1586841230676,"log_out_time":1586765913000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"外语学习/交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1586841230000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
         */

        private String identity_name;
        private UserInfoEntity userinfo;
        private UserInfoEntity masterinfo;

        private TimezoneBean timezone;
        private List<SummaryDetail> summaryinfo;

        public List<SummaryDetail> getSummaryinfo() {
            return summaryinfo;
        }

        private List<PrivateMasterInfoEntity> masterInfoEntities;

        public TimezoneBean getTimezone() {
            return timezone;
        }

        private MasterAppointmentEntity masterAppointmentEntity;

        public String getIdentity_name() {
            return identity_name;
        }

        public void setIdentity_name(String identity_name) {
            this.identity_name = identity_name;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserInfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public UserInfoEntity getMasterinfo() {
            return masterinfo;
        }

        public void setMasterinfo(UserInfoEntity masterinfo) {
            this.masterinfo = masterinfo;
        }

        public List<PrivateMasterInfoEntity> getMasterInfoEntities() {
            return masterInfoEntities;
        }

        public MasterAppointmentEntity getMasterAppointmentEntity() {
            return masterAppointmentEntity;
        }
    }
}
