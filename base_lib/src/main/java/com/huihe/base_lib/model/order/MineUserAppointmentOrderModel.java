package com.huihe.base_lib.model.order;

import com.huihe.base_lib.model.MasterCommentEntity;
import com.huihe.base_lib.model.MasterinfoBean;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;
import com.huihe.base_lib.model.personal.MasterMechanismModel;

import java.util.List;

public class MineUserAppointmentOrderModel extends JsonListResult<MineUserAppointmentOrderModel.MineUserAppointmentOrderEntity> {
  public static class MineUserAppointmentOrderEntity{

    private String id;
    private int user_id;
    private int master_id;
    private String create_time;
    private String update_time;
    private String appointment_id;
    private int status;
    private String start_time;
    private String end_time;
    private String update_identity;
    private Float offset;
    private String title;
    private String update_appointment_id;
    private String master_type;
    private String language;
    private String earnings;
    private String earnings_status;
    private String update_title;
    private String remarks;
    private String pipeline_num;
    private String service_type;
    private String offline_count;
    private String offline_mobile;
    private String student_count;
    private String offline_address;
    private String mechanism_id;
    private String latitude;
    private String longitude;
    private Object userAppointmentEntities;
    private Object identity;
    private Object whether;
    private MapBean map;

    public MapBean getMap() {
      return map;
    }

    public static class MapBean{
      private List<MasterinfoBean> masterInfoEntities;
      private List<MasterCommentEntity> masterCommentEntities;
      private MasterMechanismModel.MasterMechanismEntity masterMechanismEntity;
      private UserInfoEntity userinfo;
      private UserInfoEntity masterinfo;
      private MasterAppointmentEntity masterAppointmentEntity;
      public List<MasterinfoBean> getMasterInfoEntities() {
        return masterInfoEntities;
      }

      public UserInfoEntity getUserinfo() {
        return userinfo;
      }

      public UserInfoEntity getMasterinfo() {
        return masterinfo;
      }

      public MasterAppointmentEntity getMasterAppointmentEntity() {
        return masterAppointmentEntity;
      }

      public MasterMechanismModel.MasterMechanismEntity getMasterMechanismEntity() {
        return masterMechanismEntity;
      }

      public List<MasterCommentEntity> getMasterCommentEntities() {
        return masterCommentEntities;
      }
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public int getUser_id() {
      return user_id;
    }

    public void setUser_id(int user_id) {
      this.user_id = user_id;
    }

    public int getMaster_id() {
      return master_id;
    }

    public void setMaster_id(int master_id) {
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

    public String getEarnings_status() {
      return earnings_status;
    }

    public void setEarnings_status(String earnings_status) {
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

    public String getService_type() {
      return service_type;
    }

    public void setService_type(String service_type) {
      this.service_type = service_type;
    }

    public String getOffline_count() {
      return offline_count;
    }

    public void setOffline_count(String offline_count) {
      this.offline_count = offline_count;
    }

    public String getOffline_mobile() {
      return offline_mobile;
    }

    public void setOffline_mobile(String offline_mobile) {
      this.offline_mobile = offline_mobile;
    }

    public String getStudent_count() {
      return student_count;
    }

    public void setStudent_count(String student_count) {
      this.student_count = student_count;
    }

    public String getOffline_address() {
      return offline_address;
    }

    public void setOffline_address(String offline_address) {
      this.offline_address = offline_address;
    }

    public String getMechanism_id() {
      return mechanism_id;
    }

    public void setMechanism_id(String mechanism_id) {
      this.mechanism_id = mechanism_id;
    }

    public String getLatitude() {
      return latitude;
    }

    public void setLatitude(String latitude) {
      this.latitude = latitude;
    }

    public String getLongitude() {
      return longitude;
    }

    public void setLongitude(String longitude) {
      this.longitude = longitude;
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
  }
}