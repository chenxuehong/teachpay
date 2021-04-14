package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.base.JsonListResult;

import java.util.List;

public class TeacherListModel extends JsonListResult<TeacherListModel.MineTeacherEntity> {
    public static class MineTeacherEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : null
         * user_id : null
         * master_id : 9099
         * create_time : null
         * update_time : null
         * appointment_id : null
         * status : null
         * start_time : null
         * end_time : null
         * update_identity : null
         * title : null
         * offset : null
         * timezone_id : null
         * update_appointment_id : null
         * master_type :
         * language : null
         * earnings : null
         * earnings_status : null
         * update_title : null
         * remarks : null
         * pipeline_num : null
         * userAppointmentEntities : null
         * identity : null
         * whether : null
         * map : {"latelyclass":[],"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/6EA-21000EE36D6B/Documents/1huihe1577325774.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1577289600000,"cash":"0.000","cat_coin":"21820.000","chatting_count":218,"city":"阿森松岛","contacts_num":676,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"123","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1577326417878,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"阿糖","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"Language Learning","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1577326417000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}}
         * is_apply : null
         * update_type : null
         * studyCard : null
         * masterNotice_id : null
         * statistics_time : null
         */


        private Object id;
        private Object user_id;
        private String master_id;
        private Object create_time;
        private Object update_time;
        private Object appointment_id;
        private Object status;
        private String start_time;
        private String end_time;
        private Object update_identity;
        private String title;
        private Object offset;
        private Object timezone_id;
        private Object update_appointment_id;
        private String master_type;
        private Object language;
        private String earnings;
        private Object earnings_status;
        private Object update_title;
        private Object remarks;
        private Object pipeline_num;
        private Object userAppointmentEntities;
        private String identity;
        private Object whether;
        private MapBean map;
        private Object is_apply;
        private Object update_type;
        private Object studyCard;
        private Object masterNotice_id;
        private Object statistics_time;


        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public String getMaster_id() {
            return master_id;
        }

        public void setMaster_id(String master_id) {
            this.master_id = master_id;
        }

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public Object getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(Object appointment_id) {
            this.appointment_id = appointment_id;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
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

        public Object getUpdate_identity() {
            return update_identity;
        }

        public void setUpdate_identity(Object update_identity) {
            this.update_identity = update_identity;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getOffset() {
            return offset;
        }

        public void setOffset(Object offset) {
            this.offset = offset;
        }

        public Object getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(Object timezone_id) {
            this.timezone_id = timezone_id;
        }

        public Object getUpdate_appointment_id() {
            return update_appointment_id;
        }

        public void setUpdate_appointment_id(Object update_appointment_id) {
            this.update_appointment_id = update_appointment_id;
        }

        public String getMaster_type() {
            return master_type;
        }

        public void setMaster_type(String master_type) {
            this.master_type = master_type;
        }

        public Object getLanguage() {
            return language;
        }

        public void setLanguage(Object language) {
            this.language = language;
        }

        public String getEarnings() {
            return earnings;
        }

        public void setEarnings(String earnings) {
            this.earnings = earnings;
        }

        public Object getEarnings_status() {
            return earnings_status;
        }

        public void setEarnings_status(Object earnings_status) {
            this.earnings_status = earnings_status;
        }

        public Object getUpdate_title() {
            return update_title;
        }

        public void setUpdate_title(Object update_title) {
            this.update_title = update_title;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public Object getPipeline_num() {
            return pipeline_num;
        }

        public void setPipeline_num(Object pipeline_num) {
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

        public  class MapBean {
            /**
             * latelyclass : []
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/6EA-21000EE36D6B/Documents/1huihe1577325774.png","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","birth":1577289600000,"cash":"0.000","cat_coin":"21820.000","chatting_count":218,"city":"阿森松岛","contacts_num":676,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"123","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1577326417878,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"阿糖","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"Language Learning","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1577326417000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}
             */

            private UserinfoBean userinfo;
            private List<AppointmentinfoBean> latelyclass;

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public List<AppointmentinfoBean> getLatelyclass() {
                return latelyclass;
            }

            public void setLatelyclass(List<AppointmentinfoBean> latelyclass) {
                this.latelyclass = latelyclass;
            }

            public  class UserinfoBean {
                /**
                 * account_state : 1
                 * admin_id : 0
                 * advertising_position : false
                 * avatar : http://img.curiousmore.com/6EA-21000EE36D6B/Documents/1huihe1577325774.png
                 * background_pic : http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png
                 * birth : 1577289600000
                 * cash : 0.000
                 * cat_coin : 21820.000
                 * chatting_count : 218
                 * city : 阿森松岛
                 * contacts_num : 676
                 * country : 阿鲁巴
                 * create_time : 1555058447000
                 * distrib_qrcode :
                 * duration : 0
                 * hometown : 卡范
                 * identity_auth : false
                 * intro :
                 * invite_code : 123
                 * is_help : true
                 * is_high_quality : false
                 * is_id : false
                 * is_mail : false
                 * is_member : true
                 * is_mobile : false
                 * is_name : false
                 * is_robot : false
                 * is_teenagers : false
                 * is_unread : false
                 * lable :
                 * languages : 不限/法语
                 * like_category :
                 * like_num : 3
                 * local_time : 1577326417878
                 * log_out_time : 1575536162000
                 * mail :
                 * member_level : 1
                 * message_num : 0
                 * mobile : 18196548552
                 * mother_tongue : Dutch
                 * national_flag :
                 * nick_name : 阿糖
                 * online_state : 1
                 * oper_id : 0
                 * overseas_auth : false
                 * overseas_identity_name : 留学生
                 * preference : Language Learning
                 * qrcode :
                 * rating : 0
                 * registr_num : 1
                 * sex : 2
                 * signature :
                 * update_time : 1577326417000
                 * url : http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099
                 * user_id : 9099
                 */

                private int account_state;
                private int admin_id;
                private boolean advertising_position;
                private String avatar;
                private String background_pic;
                private long birth;
                private String cash;
                private String cat_coin;
                private int chatting_count;
                private String city;
                private int contacts_num;
                private String country;
                private long create_time;
                private String distrib_qrcode;
                private int duration;
                private String hometown;
                private boolean identity_auth;
                private String intro;
                private String invite_code;
                private boolean is_help;
                private boolean is_high_quality;
                private boolean is_id;
                private boolean is_mail;
                private boolean is_member;
                private boolean is_mobile;
                private boolean is_name;
                private boolean is_robot;
                private boolean is_teenagers;
                private boolean is_unread;
                private String lable;
                private String languages;
                private String like_category;
                private int like_num;
                private long local_time;
                private long log_out_time;
                private String mail;
                private int member_level;
                private int message_num;
                private String mobile;
                private String mother_tongue;
                private String national_flag;
                private String nick_name;
                private int online_state;
                private String oper_id;
                private boolean overseas_auth;
                private String overseas_identity_name;
                private String preference;
                private String qrcode;
                private int rating;
                private int registr_num;
                private int sex;
                private String signature;
                private long update_time;
                private String url;
                private String user_id;

                public int getAccount_state() {
                    return account_state;
                }

                public void setAccount_state(int account_state) {
                    this.account_state = account_state;
                }

                public int getAdmin_id() {
                    return admin_id;
                }

                public void setAdmin_id(int admin_id) {
                    this.admin_id = admin_id;
                }

                public boolean isAdvertising_position() {
                    return advertising_position;
                }

                public void setAdvertising_position(boolean advertising_position) {
                    this.advertising_position = advertising_position;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getBackground_pic() {
                    return background_pic;
                }

                public void setBackground_pic(String background_pic) {
                    this.background_pic = background_pic;
                }

                public long getBirth() {
                    return birth;
                }

                public void setBirth(long birth) {
                    this.birth = birth;
                }

                public String getCash() {
                    return cash;
                }

                public void setCash(String cash) {
                    this.cash = cash;
                }

                public String getCat_coin() {
                    return cat_coin;
                }

                public void setCat_coin(String cat_coin) {
                    this.cat_coin = cat_coin;
                }

                public int getChatting_count() {
                    return chatting_count;
                }

                public void setChatting_count(int chatting_count) {
                    this.chatting_count = chatting_count;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public int getContacts_num() {
                    return contacts_num;
                }

                public void setContacts_num(int contacts_num) {
                    this.contacts_num = contacts_num;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public String getDistrib_qrcode() {
                    return distrib_qrcode;
                }

                public void setDistrib_qrcode(String distrib_qrcode) {
                    this.distrib_qrcode = distrib_qrcode;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public String getHometown() {
                    return hometown;
                }

                public void setHometown(String hometown) {
                    this.hometown = hometown;
                }

                public boolean isIdentity_auth() {
                    return identity_auth;
                }

                public void setIdentity_auth(boolean identity_auth) {
                    this.identity_auth = identity_auth;
                }

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public String getInvite_code() {
                    return invite_code;
                }

                public void setInvite_code(String invite_code) {
                    this.invite_code = invite_code;
                }

                public boolean isIs_help() {
                    return is_help;
                }

                public void setIs_help(boolean is_help) {
                    this.is_help = is_help;
                }

                public boolean isIs_high_quality() {
                    return is_high_quality;
                }

                public void setIs_high_quality(boolean is_high_quality) {
                    this.is_high_quality = is_high_quality;
                }

                public boolean isIs_id() {
                    return is_id;
                }

                public void setIs_id(boolean is_id) {
                    this.is_id = is_id;
                }

                public boolean isIs_mail() {
                    return is_mail;
                }

                public void setIs_mail(boolean is_mail) {
                    this.is_mail = is_mail;
                }

                public boolean isIs_member() {
                    return is_member;
                }

                public void setIs_member(boolean is_member) {
                    this.is_member = is_member;
                }

                public boolean isIs_mobile() {
                    return is_mobile;
                }

                public void setIs_mobile(boolean is_mobile) {
                    this.is_mobile = is_mobile;
                }

                public boolean isIs_name() {
                    return is_name;
                }

                public void setIs_name(boolean is_name) {
                    this.is_name = is_name;
                }

                public boolean isIs_robot() {
                    return is_robot;
                }

                public void setIs_robot(boolean is_robot) {
                    this.is_robot = is_robot;
                }

                public boolean isIs_teenagers() {
                    return is_teenagers;
                }

                public void setIs_teenagers(boolean is_teenagers) {
                    this.is_teenagers = is_teenagers;
                }

                public boolean isIs_unread() {
                    return is_unread;
                }

                public void setIs_unread(boolean is_unread) {
                    this.is_unread = is_unread;
                }

                public String getLable() {
                    return lable;
                }

                public void setLable(String lable) {
                    this.lable = lable;
                }

                public String getLanguages() {
                    return languages;
                }

                public void setLanguages(String languages) {
                    this.languages = languages;
                }

                public String getLike_category() {
                    return like_category;
                }

                public void setLike_category(String like_category) {
                    this.like_category = like_category;
                }

                public int getLike_num() {
                    return like_num;
                }

                public void setLike_num(int like_num) {
                    this.like_num = like_num;
                }

                public long getLocal_time() {
                    return local_time;
                }

                public void setLocal_time(long local_time) {
                    this.local_time = local_time;
                }

                public long getLog_out_time() {
                    return log_out_time;
                }

                public void setLog_out_time(long log_out_time) {
                    this.log_out_time = log_out_time;
                }

                public String getMail() {
                    return mail;
                }

                public void setMail(String mail) {
                    this.mail = mail;
                }

                public int getMember_level() {
                    return member_level;
                }

                public void setMember_level(int member_level) {
                    this.member_level = member_level;
                }

                public int getMessage_num() {
                    return message_num;
                }

                public void setMessage_num(int message_num) {
                    this.message_num = message_num;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getMother_tongue() {
                    return mother_tongue;
                }

                public void setMother_tongue(String mother_tongue) {
                    this.mother_tongue = mother_tongue;
                }

                public String getNational_flag() {
                    return national_flag;
                }

                public void setNational_flag(String national_flag) {
                    this.national_flag = national_flag;
                }

                public String getNick_name() {
                    return nick_name;
                }

                public void setNick_name(String nick_name) {
                    this.nick_name = nick_name;
                }

                public int getOnline_state() {
                    return online_state;
                }

                public void setOnline_state(int online_state) {
                    this.online_state = online_state;
                }

                public String getOper_id() {
                    return oper_id;
                }

                public void setOper_id(String oper_id) {
                    this.oper_id = oper_id;
                }

                public boolean isOverseas_auth() {
                    return overseas_auth;
                }

                public void setOverseas_auth(boolean overseas_auth) {
                    this.overseas_auth = overseas_auth;
                }

                public String getOverseas_identity_name() {
                    return overseas_identity_name;
                }

                public void setOverseas_identity_name(String overseas_identity_name) {
                    this.overseas_identity_name = overseas_identity_name;
                }

                public String getPreference() {
                    return preference;
                }

                public void setPreference(String preference) {
                    this.preference = preference;
                }

                public String getQrcode() {
                    return qrcode;
                }

                public void setQrcode(String qrcode) {
                    this.qrcode = qrcode;
                }

                public int getRating() {
                    return rating;
                }

                public void setRating(int rating) {
                    this.rating = rating;
                }

                public int getRegistr_num() {
                    return registr_num;
                }

                public void setRegistr_num(int registr_num) {
                    this.registr_num = registr_num;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
                }

                public long getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(long update_time) {
                    this.update_time = update_time;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }
            }
        }
    }
}
