package com.huihe.base_lib.model.home;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;

public class MessageGroupEntity {

    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 821
     * owner_id : 9164
     * group_name : hi或恶意
     * group_type : Public
     * operator_account :
     * status : 1
     * create_time : 2019-12-19 16:43:04
     * group_id : 1576744983
     * update_time : 2019-12-23 23:35:12
     * introduction :
     * notification :
     * faceUrl : http://img.curiousmore.com/9164groupHeadFGD7F257
     * advance_group : false
     * is_open : false
     * is_charge : false
     * fee_standard : 0
     * entry_password :
     * people_num : 0
     * show_time :
     * earnings : 0
     * number_participants : 2
     * live_push_addr :
     * language : 韩语
     * label : 话题交流/聊聊家人
     * type : life_live
     * recommend_type :
     * online_num : 2
     * tickets : 0
     * live_duration : 0
     * watch_duration : 0
     * age_grade :
     * language_level :
     * map : {"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/tmp_3d06c9f512de537543a908f8d8d3b19267318268bd58741d.jpg","background_pic":"http://img.curiousmore.com/tmp_69fecd7021c097b72c4d41506bc454a1d8f4df41bbf0225c.jpg","birth":1563780560000,"cash":"0.000","cat_coin":"50.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1563780560000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1572078795178,"log_out_time":1571929738000,"mail":"","member_level":0,"message_num":0,"mobile":"15514866821","mother_tongue":"Romanian","national_flag":"","nick_name":"","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1572078795000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9164","user_id":9164}}
     * is_life : null
     * idList : null
     */

    private String id;
    private String owner_id;
    private String group_name;
    private String group_type;
    private String operator_account;
    private int status;
    private String create_time;
    private String group_id;
    private String update_time;
    private String introduction;
    private String notification;
    private String faceUrl;
    private boolean advance_group;
    private boolean is_open;
    private boolean is_charge;
    private int fee_standard;
    private String entry_password;
    private int people_num;
    private String show_time;
    private Float earnings;
    private int number_participants;
    private String live_push_addr;
    private String language;
    private String label;
    private String type;
    private String recommend_type;
    private int online_num;
    private int tickets;
    private int live_duration;
    private int watch_duration;
    private String age_grade;
    private String language_level;
    private MapBean map;
    private Boolean is_life;
    private Object idList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public String getOperator_account() {
        return operator_account;
    }

    public void setOperator_account(String operator_account) {
        this.operator_account = operator_account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public boolean isAdvance_group() {
        return advance_group;
    }

    public void setAdvance_group(boolean advance_group) {
        this.advance_group = advance_group;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public boolean isIs_charge() {
        return is_charge;
    }

    public void setIs_charge(boolean is_charge) {
        this.is_charge = is_charge;
    }

    public int getFee_standard() {
        return fee_standard;
    }

    public void setFee_standard(int fee_standard) {
        this.fee_standard = fee_standard;
    }

    public String getEntry_password() {
        return entry_password;
    }

    public void setEntry_password(String entry_password) {
        this.entry_password = entry_password;
    }

    public int getPeople_num() {
        return people_num;
    }

    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public Float getEarnings() {
        return earnings;
    }

    public void setEarnings(Float earnings) {
        this.earnings = earnings;
    }

    public int getNumber_participants() {
        return number_participants;
    }

    public void setNumber_participants(int number_participants) {
        this.number_participants = number_participants;
    }

    public String getLive_push_addr() {
        return live_push_addr;
    }

    public void setLive_push_addr(String live_push_addr) {
        this.live_push_addr = live_push_addr;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecommend_type() {
        return recommend_type;
    }

    public void setRecommend_type(String recommend_type) {
        this.recommend_type = recommend_type;
    }

    public int getOnline_num() {
        return online_num;
    }

    public void setOnline_num(int online_num) {
        this.online_num = online_num;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public int getLive_duration() {
        return live_duration;
    }

    public void setLive_duration(int live_duration) {
        this.live_duration = live_duration;
    }

    public int getWatch_duration() {
        return watch_duration;
    }

    public void setWatch_duration(int watch_duration) {
        this.watch_duration = watch_duration;
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

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public Boolean getIs_life() {
        return is_life;
    }

    public void setIs_life(Boolean is_life) {
        this.is_life = is_life;
    }

    public Object getIdList() {
        return idList;
    }

    public void setIdList(Object idList) {
        this.idList = idList;
    }

    public static class MapBean {
        /**
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/tmp_3d06c9f512de537543a908f8d8d3b19267318268bd58741d.jpg","background_pic":"http://img.curiousmore.com/tmp_69fecd7021c097b72c4d41506bc454a1d8f4df41bbf0225c.jpg","birth":1563780560000,"cash":"0.000","cat_coin":"50.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1563780560000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1572078795178,"log_out_time":1571929738000,"mail":"","member_level":0,"message_num":0,"mobile":"15514866821","mother_tongue":"Romanian","national_flag":"","nick_name":"","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1572078795000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9164","user_id":9164}
         */

        private UserInfoEntity userinfo;
        private MasterAppointmentEntity appointmentInfo;

        public String appointmentCount;

        public String getAppointmentCount() {
            return appointmentCount;
        }

        public void setAppointmentCount(String appointmentCount) {
            this.appointmentCount = appointmentCount;
        }

        public MasterAppointmentEntity getAppointmentInfo() {
            return appointmentInfo;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserInfoEntity userinfo) {
            this.userinfo = userinfo;
        }

    }
}
