package com.huihe.entities_lib.rep.user;

public class UserInfoEntity {
    private String user_id;
    private String create_time;
    private String mobile;
    private String mail;
    private String pay_pass;
    private String update_time;
    private Integer sex;
    private String avatar;
    private String birth;
    private String lable;
    private String intro;
    private String cash;
    private String signature;
    private Integer member_level;
    private boolean is_member;
    private boolean identity_auth;
    private Boolean is_collection;
    private String cat_coin;
    private String nick_name;
    private String national_flag;
    private boolean overseas_auth;
    private String hometown;
    private String preference;
    private boolean is_robot;
    private String admin_id;
    private boolean advertising_position;
    private String invite_code;
    private String qrcode;
    private String distrib_qrcode;
    private Integer like_num;
    private String country;
    private String city;
    private String like_category;
    private SearchParam searchParam;
    private String contacts_num;
    private String message_num;
    private String registr_num;
    private String log_out_time;
    private String overseas_identity_name;
    private String distance;
    private String local_time;
    private Object begin_time;
    private String end_time;
    private int oper_id;
    private String languages;
    private String relationship;
    private int online_state;
    private int account_state;
    private int chatting_count;
    private boolean is_id;
    private boolean is_mobile;
    private boolean is_name;
    private boolean is_mail;
    private String sign;
    private String mother_tongue;
    private String background_pic;
    private String url;
    private boolean is_high_quality;
    private int rating;
    private boolean is_help;
    private boolean is_unread;
    private String duration;
    private MapBean map;
    private String loginStatistical;
    private String master_type;
    private String begin_time_statistics;
    private String end_time_statistics;
    private String statistics_time;
    private Boolean is_teenagers;
    private Boolean is_mechanism_auditing;
    private String mechanism_id;
    private String binding_id;
    private String mechanism_recommender_id;
    private String master_recommender_id;
    private String points;

    public String getPoints() {
        return points;
    }

    public Boolean getIs_collection() {
        return is_collection;
    }

    public void setIs_mechanism_auditing(Boolean is_mechanism_auditing) {
        this.is_mechanism_auditing = is_mechanism_auditing;
    }

    public Boolean getIs_mechanism_auditing() {
        return is_mechanism_auditing;
    }

    public void setMechanism_recommender_id(String mechanism_recommender_id) {
        this.mechanism_recommender_id = mechanism_recommender_id;
    }

    public void setMaster_recommender_id(String master_recommender_id) {
        this.master_recommender_id = master_recommender_id;
    }

    public String getMechanism_recommender_id() {
        return mechanism_recommender_id;
    }

    public String getMaster_recommender_id() {
        return master_recommender_id;
    }

    public void setMechanism_id(String mechanism_id) {
        this.mechanism_id = mechanism_id;
    }

    public void setBinding_id(String binding_id) {
        this.binding_id = binding_id;
    }

    public String getMechanism_id() {
        return mechanism_id;
    }

    public String getBinding_id() {
        return binding_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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

    public String getPay_pass() {
        return pay_pass;
    }

    public void setPay_pass(String pay_pass) {
        this.pay_pass = pay_pass;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public Integer getSex() {
        return sex == null ? 1 : sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getMember_level() {
        return member_level;
    }

    public void setMember_level(int member_level) {
        this.member_level = member_level;
    }

    public boolean isIs_member() {
        return is_member;
    }

    public void setIs_member(boolean is_member) {
        this.is_member = is_member;
    }

    public boolean isIdentity_auth() {
        return identity_auth;
    }

    public void setIdentity_auth(boolean identity_auth) {
        this.identity_auth = identity_auth;
    }

    public String getCat_coin() {
        return cat_coin;
    }

    public void setCat_coin(String cat_coin) {
        this.cat_coin = cat_coin;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getNational_flag() {
        return national_flag;
    }

    public void setNational_flag(String national_flag) {
        this.national_flag = national_flag;
    }

    public boolean isOverseas_auth() {
        return overseas_auth;
    }

    public void setOverseas_auth(boolean overseas_auth) {
        this.overseas_auth = overseas_auth;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public boolean isIs_robot() {
        return is_robot;
    }

    public void setIs_robot(boolean is_robot) {
        this.is_robot = is_robot;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public boolean isAdvertising_position() {
        return advertising_position;
    }

    public void setAdvertising_position(boolean advertising_position) {
        this.advertising_position = advertising_position;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getDistrib_qrcode() {
        return distrib_qrcode;
    }

    public void setDistrib_qrcode(String distrib_qrcode) {
        this.distrib_qrcode = distrib_qrcode;
    }

    public Integer getLike_num() {
        return like_num;
    }

    public void setLike_num(Integer like_num) {
        this.like_num = like_num;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLike_category() {
        return like_category;
    }

    public void setLike_category(String like_category) {
        this.like_category = like_category;
    }

    public SearchParam getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(SearchParam searchParam) {
        this.searchParam = searchParam;
    }

    public String getContacts_num() {
        return contacts_num;
    }

    public void setContacts_num(String contacts_num) {
        this.contacts_num = contacts_num;
    }

    public String getMessage_num() {
        return message_num;
    }

    public void setMessage_num(String message_num) {
        this.message_num = message_num;
    }

    public String getRegistr_num() {
        return registr_num;
    }

    public void setRegistr_num(String registr_num) {
        this.registr_num = registr_num;
    }

    public String getLog_out_time() {
        return log_out_time;
    }

    public void setLog_out_time(String log_out_time) {
        this.log_out_time = log_out_time;
    }
    public String getOverseas_identity_name() {
        return overseas_identity_name;
    }

    public void setOverseas_identity_name(String overseas_identity_name) {
        this.overseas_identity_name = overseas_identity_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLocal_time() {
        return local_time;
    }

    public void setLocal_time(String local_time) {
        this.local_time = local_time;
    }

    public Object getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Object begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getOper_id() {
        return oper_id;
    }

    public void setOper_id(int oper_id) {
        this.oper_id = oper_id;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getOnline_state() {
        return online_state;
    }

    public void setOnline_state(int online_state) {
        this.online_state = online_state;
    }

    public int getAccount_state() {
        return account_state;
    }

    public void setAccount_state(int account_state) {
        this.account_state = account_state;
    }

    public int getChatting_count() {
        return chatting_count;
    }

    public void setChatting_count(int chatting_count) {
        this.chatting_count = chatting_count;
    }

    public boolean isIs_id() {
        return is_id;
    }

    public void setIs_id(boolean is_id) {
        this.is_id = is_id;
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

    public boolean isIs_mail() {
        return is_mail;
    }

    public void setIs_mail(boolean is_mail) {
        this.is_mail = is_mail;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMother_tongue() {
        return mother_tongue;
    }

    public void setMother_tongue(String mother_tongue) {
        this.mother_tongue = mother_tongue;
    }

    public String getBackground_pic() {
        return background_pic;
    }

    public void setBackground_pic(String background_pic) {
        this.background_pic = background_pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIs_high_quality() {
        return is_high_quality;
    }

    public void setIs_high_quality(boolean is_high_quality) {
        this.is_high_quality = is_high_quality;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isIs_help() {
        return is_help;
    }

    public void setIs_help(boolean is_help) {
        this.is_help = is_help;
    }

    public boolean isIs_unread() {
        return is_unread;
    }

    public void setIs_unread(boolean is_unread) {
        this.is_unread = is_unread;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public String getLoginStatistical() {
        return loginStatistical;
    }

    public void setLoginStatistical(String loginStatistical) {
        this.loginStatistical = loginStatistical;
    }

    public String getMaster_type() {
        return master_type;
    }

    public void setMaster_type(String master_type) {
        this.master_type = master_type;
    }

    public String getBegin_time_statistics() {
        return begin_time_statistics;
    }

    public void setBegin_time_statistics(String begin_time_statistics) {
        this.begin_time_statistics = begin_time_statistics;
    }

    public Object getEnd_time_statistics() {
        return end_time_statistics;
    }

    public void setEnd_time_statistics(String end_time_statistics) {
        this.end_time_statistics = end_time_statistics;
    }

    public String getStatistics_time() {
        return statistics_time;
    }

    public void setStatistics_time(String statistics_time) {
        this.statistics_time = statistics_time;
    }

    public boolean isIs_teenagers() {
        return is_teenagers;
    }

    public void setIs_teenagers(boolean is_teenagers) {
        this.is_teenagers = is_teenagers;
    }

    public static class SearchParam {

        /**
         * latitude : null
         * longitude : null
         * search_type : master
         */

        private String latitude;
        private String longitude;
        private String search_type;

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

        public String getSearch_type() {
            return search_type;
        }

        public void setSearch_type(String search_type) {
            this.search_type = search_type;
        }
    }

    public static class MapBean {

        /**
         * masterprice : 398
         * studentCount : 0
         * classCount : 0
         * masterinfo : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":115,"nationality":"西班牙","user_id":9120,"language":"English","create_time":"2019-09-26 18:42:01","introduction":"1233","grade":1,"score":1.1,"address":"","update_time":"2020-07-08 00:17:24","mother_tongue":"Arabic","sex":1,"status":2,"type":"major","identity":"学生","graduation_school":"13","specialty":"133","mobile":"23","mail":".ad","teach_language":"Arabic","relevant_information":"","mother_tongue_url":"http://img.curiousmore.com/userid9120/774523362","language_url":"http://img.curiousmore.com/userid9120/461911806","teach_language_url":"http://img.curiousmore.com/userid9120/774523362","requirement_type":"","education":"本科","recommend_type":"","layout":3,"earnings":0,"recommend_video":"http://img.curiousmore.com//storage/emulated/0/DCIM/Camera/68856ca89d5826b42fa630c4a7f7c4f4.mp4","cover":"http://img.curiousmore.com//storage/emulated/0/DCIM/Camera/68856ca89d5826b42fa630c4a7f7c4f4.mp4?vframe/jpg/offset/0","activity_type":"","popularity":0,"online":"0","offline":"0","service_type":0,"serve_people":"","language_level":"","serve_range":0,"nhom_address":"","introduction_content":"","introduction_cover":"","max_student_count":0,"contact_information":"","charging_standard":0,"photo":"","full_name":"","master_age":0,"course_duration":0,"mechanism_id":0,"is_self_modification":false,"stringList":null,"login_name":null,"login_pass":null,"latitude":0,"longitude":0,"distance":null,"price":0,"map":null,"start_time":null,"end_time":null,"nick_name":null,"idList":null,"searchParam":null,"verification_code":null}
         */

        private String masterprice;
        private String studentCount;
        private String classCount;
        private MasterinfoBean masterinfo;

        public String getMasterprice() {
            return masterprice;
        }

        public void setMasterprice(String masterprice) {
            this.masterprice = masterprice;
        }

        public String getStudentCount() {
            return studentCount;
        }

        public void setStudentCount(String studentCount) {
            this.studentCount = studentCount;
        }

        public String getClassCount() {
            return classCount;
        }

        public void setClassCount(String classCount) {
            this.classCount = classCount;
        }

        public MasterinfoBean getMasterinfo() {
            return masterinfo;
        }

        public void setMasterinfo(MasterinfoBean masterinfo) {
            this.masterinfo = masterinfo;
        }

    }
}
