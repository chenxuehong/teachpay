package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;

import java.util.List;

public class SummaryEntity {


    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 137
     * user_id : 9191
     * master_id : 9099
     * create_time : 2019-12-30 10:54:47
     * update_time : 2019-12-30 10:56:07
     * appointment_id : 756
     * status : 2
     * start_time : 2019-12-31 10:00:00
     * end_time : 2019-12-30 10:00:00
     * update_identity :
     * title : 话题交流/聊聊家人
     * offset : 8
     * timezone_id : 324
     * update_appointment_id : 0
     * master_type : major
     * language : 汉语
     * earnings : 25
     * earnings_status : 1
     * update_title :
     * remarks :
     * pipeline_num : PT20191205162211948392982
     * userAppointmentEntities : null
     * identity : null
     * whether : null
     * map : {"summaryinfo":[],"masterinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1578585600000,"cash":"0.000","cat_coin":"21430.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1578645219497,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1578645219000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"","background_pic":"","birth":1546272000000,"cash":"0.000","cat_coin":"60.000","chatting_count":0,"city":"北京市·市辖区·东城区","contacts_num":0,"country":"中国","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"北京市·市辖区·东城区","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1574328343943,"log_out_time":1574063904000,"mail":"15514766147@qq.com","member_level":0,"message_num":0,"mobile":"15514866147","mother_tongue":"Chinese","national_flag":"","nick_name":"Maxsddd","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"搞笑/习俗","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1574328317000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}}
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
    private Float offset;
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
    private MapBean map;
    private Object is_apply;
    private Object update_type;
    private Object studyCard;
    private Object masterNotice_id;
    private Object statistics_time;
    private Boolean isCourseware;
    private String url;


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

    public Boolean getCourseware() {
        return isCourseware;
    }

    public void setIsCourseware(boolean isCourseware) {
        this.isCourseware = isCourseware;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static class MapBean {
        /**
         * summaryinfo : []
         * masterinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1578585600000,"cash":"0.000","cat_coin":"21430.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1578645219497,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1578645219000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"","background_pic":"","birth":1546272000000,"cash":"0.000","cat_coin":"60.000","chatting_count":0,"city":"北京市·市辖区·东城区","contacts_num":0,"country":"中国","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"北京市·市辖区·东城区","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1574328343943,"log_out_time":1574063904000,"mail":"15514766147@qq.com","member_level":0,"message_num":0,"mobile":"15514866147","mother_tongue":"Chinese","national_flag":"","nick_name":"Maxsddd","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"搞笑/习俗","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1574328317000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
         */

        private UserInfoEntity masterinfo;
        private UserInfoEntity userinfo;
        private List<SummaryDetail> summaryinfo;

        public UserInfoEntity getMasterinfo() {
            return masterinfo;
        }

        public void setMasterinfo(UserInfoEntity masterinfo) {
            this.masterinfo = masterinfo;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserInfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public List<SummaryDetail> getSummaryinfo() {
            return summaryinfo;
        }

        public void setSummaryinfo(List<SummaryDetail> summaryinfo) {
            this.summaryinfo = summaryinfo;
        }
    }
}
