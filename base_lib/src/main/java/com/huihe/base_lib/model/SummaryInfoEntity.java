package com.huihe.base_lib.model;

public class SummaryInfoEntity {

    /**
     * id : 70
     * master_id : 10129
     * create_time : 2020-12-30 15:10:15
     * update_time : 2020-12-30 15:10:15
     * appointment_id : 13821
     * content : 体验活动
     * word :
     * grammar :
     * proposal :
     * photo_url :
     * mechanism_id : 43
     * user_id : null
     */

    private String id;
    private String master_id;
    private String create_time;
    private String update_time;
    private String appointment_id;
    private String content;
    private String word;
    private String grammar;
    private String proposal;
    private String photo_url;
    private String mechanism_id;
    private String user_id;
    private Map map;

    public Map getMap() {
        return map;
    }

    public static class Map{
        private UserInfoEntity userinfo;
        private MechanismOfflineScheduleEntity masterAppointmentEntity;

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        public MechanismOfflineScheduleEntity getMasterAppointmentEntity() {
            return masterAppointmentEntity;
        }
    }
    public String getId() {
        return id;
    }

    public String getMaster_id() {
        return master_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getContent() {
        return content;
    }

    public String getWord() {
        return word;
    }

    public String getGrammar() {
        return grammar;
    }

    public String getProposal() {
        return proposal;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getUser_id() {
        return user_id;
    }
}
