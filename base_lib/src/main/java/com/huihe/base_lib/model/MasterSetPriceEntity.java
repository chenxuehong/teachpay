package com.huihe.base_lib.model;

import com.huihe.base_lib.model.base.BaseBean;
import com.huihe.base_lib.model.personal.MasterMechanismModel;
import com.huihe.base_lib.model.personal.MechanismAppointmentModel;

import java.util.List;

public class MasterSetPriceEntity extends BaseBean {

    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 8
     * create_time : 2020-08-25 15:19:20
     * update_time : 2020-08-25 15:29:37
     * title : 3455
     * course_num : 2
     * amout : 13
     * service_type : 0
     * discount : 0.5
     * discount_amout : 11
     * user_id : 4260
     * introduction_content : gggy
     * status : 2
     * titile_url :
     * first_free : false
     * type : exclusive_courses
     * connect_peoplenum : 4
     * mechanism_id : 9
     * face_url : http://img.huihejituan.com/1598340351098.png
     * pay_num : 0
     */

    private String id;
    private String create_time;
    private String update_time;
    private String title;
    private String course_num;
    private String amout;
    private double latitude;
    private double longitude;
    private String service_type;
    private String discount;
    private String default_discount_price;
    private String discount_amout;
    private String user_id;
    private String introduction_content;
    private int status;
    private String titile_url;
    private Boolean first_free;
    private String type;
    private Long sorted;
    private String connect_peoplenum;
    private String mechanism_id;
    private String face_url;
    private String language;
    private String pay_num;
    private Boolean is_attend_activities;
    private String introduction_url;
    private String introduction_text;
    private String master_appointment_id;
    private String label;
    private String categories;
    private String duration;
    private String price_list;
    private String original_price;
    private String live_streaming_id;
    private Boolean is_live_streaming;
    private Double live_stream_price;
    private Double living_single_session_price;
    private Boolean is_one_time_payment;

    public Boolean getIs_one_time_payment() {
        return is_one_time_payment;
    }

    public void setIs_one_time_payment(Boolean is_one_time_payment) {
        this.is_one_time_payment = is_one_time_payment;
    }

    public Double getLive_stream_price() {
        return live_stream_price;
    }

    public Double getLiving_single_session_price() {
        return living_single_session_price;
    }

    public Boolean getIs_live_streaming() {
        return is_live_streaming;
    }

    public String getLive_streaming_id() {
        return live_streaming_id;
    }

    public Long getSorted() {
        return sorted;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPrice_list() {
        return price_list;
    }

    public String getDuration() {
        return duration;
    }

    public String getDefault_discount_price() {
        return default_discount_price;
    }

    private String appointment_type;
    private Map map;

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public String getCategories() {
        return categories;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getIs_attend_activities() {
        return is_attend_activities;
    }

    public String getLabel() {
        return label;
    }

    public Map getMap() {
        return map;
    }

    public static class Map {

        private List<BusinessActivityTypeEntity> activityEntityList;

        public List<BusinessActivityTypeEntity> getActivityEntityList() {
            return activityEntityList;
        }

        private List<PriceEntity> priceList;
        private ActivityEntity activityEntity;
        public List<PriceEntity> getPriceList() {
            return priceList;
        }

        public ActivityEntity getActivityEntity() {
            return activityEntity;
        }

        public static class PriceEntity {

            private String id;
            private String master_set_price_id;
            private String create_time;
            private String update_time;
            private String status;
            private String type;
            private String upper_limit;
            private String lower_limit;
            private String price;
            private String course_num;

            public String getId() {
                return id;
            }

            public String getMaster_set_price_id() {
                return master_set_price_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public String getStatus() {
                return status;
            }

            public String getType() {
                return type;
            }

            public String getUpper_limit() {
                return upper_limit;
            }

            public String getLower_limit() {
                return lower_limit;
            }

            public String getPrice() {
                return price;
            }

            public String getCourse_num() {
                return course_num;
            }
        }

        private MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
        private UserInfoEntity masterinfo;
        private MechanismAppointmentModel.MechanismAppointmentEntity masterAppointmentEntity;

        private List<UserInfoEntity> mechanismEntityMasterList;

        public MechanismAppointmentModel.MechanismAppointmentEntity getMasterAppointmentEntity() {
            return masterAppointmentEntity;
        }

        public List<UserInfoEntity> getMechanismEntityMasterList() {
            return mechanismEntityMasterList;
        }

        public MasterMechanismModel.MasterMechanismEntity getMasterMechanismEntity() {
            return masterMechanismEntity;
        }

        private MasterSetPriceEntity masterSetPriceEntity;

        public MasterSetPriceEntity getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public UserInfoEntity getMasterinfo() {
            return masterinfo;
        }
    }

    public String getIntroduction_url() {
        return introduction_url;
    }

    public String getIntroduction_text() {
        return introduction_text;
    }

    public String getMaster_appointment_id() {
        return master_appointment_id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public String getAmout() {
        return amout;
    }

    public void setAmout(String amout) {
        this.amout = amout;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIntroduction_content() {
        return introduction_content;
    }

    public void setIntroduction_content(String introduction_content) {
        this.introduction_content = introduction_content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitile_url() {
        return titile_url;
    }

    public void setTitile_url(String titile_url) {
        this.titile_url = titile_url;
    }

    public boolean isFirst_free() {
        return first_free;
    }

    public void setFirst_free(boolean first_free) {
        this.first_free = first_free;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConnect_peoplenum() {
        return connect_peoplenum;
    }

    public void setConnect_peoplenum(String connect_peoplenum) {
        this.connect_peoplenum = connect_peoplenum;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }

    public String getFace_url() {
        return face_url;
    }

    public void setFace_url(String face_url) {
        this.face_url = face_url;
    }

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }
}
