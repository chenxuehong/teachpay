package com.huihe.base_lib.model.im;

import com.huihe.base_lib.model.base.JsonListResult;

public class UserLikeModel extends JsonListResult<UserLikeModel.UserLikeEntity> {

    public class UserLikeEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 37
         * user_id : 9099
         * is_like : true
         * other_user_id : 9252
         * create_time : 2019-10-22 10:56:59
         * update_time : 2019-10-22 10:56:59
         * map : {"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/4EE-D7F626CB8A63/Documents/1huihe1571411340.png","background_pic":"","birth":1571328000000,"cash":"0.000","cat_coin":"50.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1571411302000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"123456","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1571411507730,"log_out_time":1571411313000,"mail":"","member_level":0,"message_num":0,"mobile":"18857909750","mother_tongue":"","national_flag":"","nick_name":"123","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":2,"signature":"","update_time":1571411506000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9252","user_id":9252}}
         */

        private int pageSize;
        private int currentPage;
        private int totalItem;
        private int startRow;
        private Object sortName;
        private Object orderBy;
        private Object fileds;
        private int totalPage;
        private int id;
        private int user_id;
        private boolean is_like;
        private int other_user_id;
        private String create_time;
        private String update_time;
        private MapBean map;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalItem() {
            return totalItem;
        }

        public void setTotalItem(int totalItem) {
            this.totalItem = totalItem;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public Object getSortName() {
            return sortName;
        }

        public void setSortName(Object sortName) {
            this.sortName = sortName;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public Object getFileds() {
            return fileds;
        }

        public void setFileds(Object fileds) {
            this.fileds = fileds;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public int getOther_user_id() {
            return other_user_id;
        }

        public void setOther_user_id(int other_user_id) {
            this.other_user_id = other_user_id;
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

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public  class MapBean {
            /**
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/4EE-D7F626CB8A63/Documents/1huihe1571411340.png","background_pic":"","birth":1571328000000,"cash":"0.000","cat_coin":"50.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1571411302000,"distrib_qrcode":"","duration":0,"hometown":"","identity_auth":false,"intro":"","invite_code":"123456","is_help":false,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":0,"local_time":1571411507730,"log_out_time":1571411313000,"mail":"","member_level":0,"message_num":0,"mobile":"18857909750","mother_tongue":"","national_flag":"","nick_name":"123","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"","qrcode":"","rating":0,"registr_num":0,"sex":2,"signature":"","update_time":1571411506000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9252","user_id":9252}
             */

            private UserinfoBean userinfo;

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public  class UserinfoBean {
                /**
                 * account_state : 1
                 * admin_id : 0
                 * advertising_position : false
                 * avatar : http://img.curiousmore.com/4EE-D7F626CB8A63/Documents/1huihe1571411340.png
                 * background_pic :
                 * birth : 1571328000000
                 * cash : 0.000
                 * cat_coin : 50.000
                 * chatting_count : 0
                 * city :
                 * contacts_num : 0
                 * country :
                 * create_time : 1571411302000
                 * distrib_qrcode :
                 * duration : 0
                 * hometown :
                 * identity_auth : false
                 * intro :
                 * invite_code : 123456
                 * is_help : false
                 * is_high_quality : false
                 * is_id : false
                 * is_mail : false
                 * is_member : false
                 * is_mobile : false
                 * is_name : false
                 * is_robot : false
                 * is_teenagers : false
                 * is_unread : false
                 * lable :
                 * languages :
                 * like_category :
                 * like_num : 0
                 * local_time : 1571411507730
                 * log_out_time : 1571411313000
                 * mail :
                 * member_level : 0
                 * message_num : 0
                 * mobile : 18857909750
                 * mother_tongue :
                 * national_flag :
                 * nick_name : 123
                 * online_state : 1
                 * oper_id : 0
                 * overseas_auth : false
                 * overseas_identity_name :
                 * preference :
                 * qrcode :
                 * rating : 0
                 * registr_num : 0
                 * sex : 2
                 * signature :
                 * update_time : 1571411506000
                 * url : http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9252
                 * user_id : 9252
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
                private int oper_id;
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
                private int user_id;

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

                public int getOper_id() {
                    return oper_id;
                }

                public void setOper_id(int oper_id) {
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

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }
            }
        }
    }
}
