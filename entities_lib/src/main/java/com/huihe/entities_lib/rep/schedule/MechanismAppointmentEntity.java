package com.huihe.entities_lib.rep.schedule;

import com.huihe.base_lib.model.MasterinfoBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.MechanismAppointmentModel;
import com.huihe.entities_lib.rep.user.MasterMechanismEntity;

import java.util.List;

public class MechanismAppointmentEntity {
    private String id;
    private String create_time;
    private String update_time;
    private String master_id;
    private String start_time;
    private String end_time;
    private int status;
    private String type;
    private float offset;
    private String title;
    private String group_id;
    private String cover;
    private String age_grade;
    private String language_level;
    private String language;
    private String group_type;
    private boolean is_appointment;
    private MechanismAppointmentModel.MechanismAppointmentEntity.MapBean map;
    private Object entities;
    private Object timecode;
    private String teach_language;
    private String classroom_type;
    private String introduction_cover;
    private String introduction_content;
    private String service_type;
    private String mechanism_id;
    private String master_set_price_id;
    private String amout;
    private String discount;
    private String discount_amout;
    private boolean is_discount;
    private String teach_program;
    private double latitude;
    private double longitude;
    private Object local_offset;
    private Boolean is_teenagers;
    private String user_id;
    private Object group_ids;
    private int connect_peoplenum;
    private boolean is_recording;

    public String getMaster_set_price_id() {
        return master_set_price_id;
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

    public MechanismAppointmentModel.MechanismAppointmentEntity.MapBean getMap() {
        return map;
    }

    public void setMap(MechanismAppointmentModel.MechanismAppointmentEntity.MapBean map) {
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

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }

    public String getAmout() {
        return amout;
    }

    public void setAmout(String amout) {
        this.amout = amout;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_amout() {
        return discount_amout;
    }

    public void setDiscount_amout(String discount_amout) {
        this.discount_amout = discount_amout;
    }

    public boolean isIs_discount() {
        return is_discount;
    }

    public void setIs_discount(boolean is_discount) {
        this.is_discount = is_discount;
    }

    public String getTeach_program() {
        return teach_program;
    }

    public void setTeach_program(String teach_program) {
        this.teach_program = teach_program;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Object getLocal_offset() {
        return local_offset;
    }

    public void setLocal_offset(Object local_offset) {
        this.local_offset = local_offset;
    }

    public Boolean getIs_teenagers() {
        return is_teenagers;
    }

    public void setIs_teenagers(Boolean is_teenagers) {
        this.is_teenagers = is_teenagers;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Object getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(Object group_ids) {
        this.group_ids = group_ids;
    }

    public int getConnect_peoplenum() {
        return connect_peoplenum;
    }

    public void setConnect_peoplenum(int connect_peoplenum) {
        this.connect_peoplenum = connect_peoplenum;
    }

    public boolean isIs_recording() {
        return is_recording;
    }

    public void setIs_recording(boolean is_recording) {
        this.is_recording = is_recording;
    }

    public static class MapBean {
        /**
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"","background_pic":"","birth":1593336445000,"cash":"0.000","cat_coin":"0.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1593336445000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1593363616863,"log_out_time":1593336445000,"mail":"","member_level":0,"message_num":0,"mobile":"999999","mother_tongue":"","national_flag":"","nick_name":"999999","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","pay_pass":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1593346201000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9596","user_id":9596}
         * masterinfo : [{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":199,"nationality":"","user_id":9596,"language":"","create_time":"2020-06-28 17:27:25","introduction":"全能选手","grade":1,"score":0,"address":"","update_time":"2020-06-28 20:10:25","mother_tongue":"","sex":1,"status":2,"type":"cross_border","identity":"","graduation_school":"","specialty":"","mobile":"13296728663","mail":"","teach_language":"English","relevant_information":"http://img.curiousmore.com/5C9-F9F60C4573E1/Documents/com_tencent_imsdk_data/image/huihe_172036443,http://img.curiousmore.com/5C9-F9F60C4573E1/Documents/com_tencent_imsdk_data/image/huihe_215636444","mother_tongue_url":"","language_url":"","teach_language_url":"http://img.curiousmore.com/userid9099/804756368","requirement_type":"","education":"","recommend_type":"","layout":0,"earnings":0,"recommend_video":"","cover":"","activity_type":"","popularity":0,"online":"","offline":"","service_type":3,"serve_people":"","language_level":"","serve_range":0,"nhom_address":"浙江省京杭运河陈列馆向东200米御景路与运河东路十字路口的东北角-101","introduction_content":"","introduction_cover":"","max_student_count":0,"contact_information":"","charging_standard":0,"photo":"http://img.curiousmore.com/5C9-F9F60C4573E1/Documents/com_tencent_imsdk_data/image/huihe_704436443","full_name":"舞台","master_age":25,"course_duration":30,"mechanism_id":4,"is_self_modification":false,"stringList":null,"login_name":null,"login_pass":null,"price":0,"map":null,"start_time":null,"end_time":null,"nick_name":null,"idList":null,"searchParam":null}]
         */

        private UserInfoEntity userinfo;
        private List<MasterinfoBean> masterinfo;
        private MasterMechanismEntity masterMechanismEntity;

        public void setMasterMechanismEntity(MasterMechanismEntity masterMechanismEntity) {
            this.masterMechanismEntity = masterMechanismEntity;
        }

        public MasterMechanismEntity getMasterMechanismEntity() {
            return masterMechanismEntity;
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
