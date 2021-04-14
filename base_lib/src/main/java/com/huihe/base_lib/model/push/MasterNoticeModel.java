package com.huihe.base_lib.model.push;

import com.huihe.base_lib.model.base.JsonListResult;
import com.huihe.base_lib.model.personal.MasterAppointmentEntity;

public class MasterNoticeModel extends JsonListResult<MasterNoticeModel.MasterNoticeEntity> {
    public static class MasterNoticeEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * sortName : null
         * orderBy : null
         * fileds : null
         * totalPage : 0
         * id : 196
         * user_id : 9191
         * create_time : 2020-04-11 15:13:49
         * update_time : 2020-04-11 16:52:39
         * master_id : 4260
         * appointment_id : 162
         * status : 1
         * type : curriculumcancel
         * handle : 3
         * identity : User details
         * read_type : read
         * map : {"userAppointmentInfo":{"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":162,"user_id":9191,"master_id":4260,"create_time":"2020-04-11 14:58:00","update_time":"2020-04-11 15:13:49","appointment_id":1470,"status":5,"start_time":"2020-04-12 07:30:00","end_time":"2020-04-12 07:59:59","update_identity":"User details","title":"topic_exchange/聊聊音乐","offset":8,"timezone_id":324,"update_appointment_id":0,"master_type":"major","language":"","earnings":25,"earnings_status":1,"update_title":"","remarks":"","pipeline_num":"PT20200411145800569448265","userAppointmentEntities":null,"identity":null,"whether":null,"map":null,"is_apply":null,"update_type":null,"studyCard":null,"masterNotice_id":null,"statistics_time":null},"masterinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1584104232184.jpg","background_pic":"http://img.curiousmore.com/1584354512798.jpg","birth":811526400000,"cash":"0.000","cat_coin":"9.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554712462000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":8,"local_time":1584354513871,"log_out_time":1584131023000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584325713000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584535512217.jpg","birth":1585843200000,"cash":"0.000","cat_coin":"198.000","chatting_count":0,"city":"爱尔巴桑","contacts_num":2,"country":"阿尔巴尼亚","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"中国·北京","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1586322630711,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Polish","national_flag":"","nick_name":"kitychen1","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1586293830000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}}
         */

        private String id;
        private String user_id;
        private String create_time;
        private String update_time;
        private String master_id;
        private String appointment_id;
        private Integer status;
        private String type;
        private int handle;
        private String identity;
        private String read_type;
        private MapBean map;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getHandle() {
            return handle;
        }

        public void setHandle(int handle) {
            this.handle = handle;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getRead_type() {
            return read_type;
        }

        public void setRead_type(String read_type) {
            this.read_type = read_type;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public static class MapBean {
            /**
             * userAppointmentInfo : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"id":162,"user_id":9191,"master_id":4260,"create_time":"2020-04-11 14:58:00","update_time":"2020-04-11 15:13:49","appointment_id":1470,"status":5,"start_time":"2020-04-12 07:30:00","end_time":"2020-04-12 07:59:59","update_identity":"User details","title":"topic_exchange/聊聊音乐","offset":8,"timezone_id":324,"update_appointment_id":0,"master_type":"major","language":"","earnings":25,"earnings_status":1,"update_title":"","remarks":"","pipeline_num":"PT20200411145800569448265","userAppointmentEntities":null,"identity":null,"whether":null,"map":null,"is_apply":null,"update_type":null,"studyCard":null,"masterNotice_id":null,"statistics_time":null}
             * masterinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1584104232184.jpg","background_pic":"http://img.curiousmore.com/1584354512798.jpg","birth":811526400000,"cash":"0.000","cat_coin":"9.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1554712462000,"distrib_qrcode":"","duration":0,"hometown":"阿富汗·马扎里沙里夫","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":8,"local_time":1584354513871,"log_out_time":1584131023000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":0,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1584325713000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1584535512217.jpg","birth":1585843200000,"cash":"0.000","cat_coin":"198.000","chatting_count":0,"city":"爱尔巴桑","contacts_num":2,"country":"阿尔巴尼亚","create_time":1564099314000,"distrib_qrcode":"","duration":0,"hometown":"中国·北京","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1586322630711,"log_out_time":1584037085000,"mail":"1319817447@qq.com","member_level":0,"message_num":0,"mobile":"18106548078","mother_tongue":"Polish","national_flag":"","nick_name":"kitychen1","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"交友拍拖","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1586293830000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
             */

            private UserAppointmentInfoBean userAppointmentInfo;
            private MasterAppointmentEntity updateAppointmentEntity;
            private MasterinfoBean masterinfo;
            private UserinfoBean userinfo;

            public MasterAppointmentEntity getUpdateAppointmentEntity() {
                return updateAppointmentEntity;
            }

            public UserAppointmentInfoBean getUserAppointmentInfo() {
                return userAppointmentInfo;
            }

            public void setUserAppointmentInfo(UserAppointmentInfoBean userAppointmentInfo) {
                this.userAppointmentInfo = userAppointmentInfo;
            }

            public MasterinfoBean getMasterinfo() {
                return masterinfo;
            }

            public void setMasterinfo(MasterinfoBean masterinfo) {
                this.masterinfo = masterinfo;
            }

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public static class UserAppointmentInfoBean {
                /**
                 * pageSize : 10
                 * currentPage : 0
                 * totalItem : 0
                 * startRow : 0
                 * sortName : null
                 * orderBy : null
                 * fileds : null
                 * totalPage : 0
                 * id : 162
                 * user_id : 9191
                 * master_id : 4260
                 * create_time : 2020-04-11 14:58:00
                 * update_time : 2020-04-11 15:13:49
                 * appointment_id : 1470
                 * status : 5
                 * start_time : 2020-04-12 07:30:00
                 * end_time : 2020-04-12 07:59:59
                 * update_identity : User details
                 * title : topic_exchange/聊聊音乐
                 * offset : 8
                 * timezone_id : 324
                 * update_appointment_id : 0
                 * master_type : major
                 * language :
                 * earnings : 25
                 * earnings_status : 1
                 * update_title :
                 * remarks :
                 * pipeline_num : PT20200411145800569448265
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

                private int pageSize;
                private int currentPage;
                private int totalItem;
                private int startRow;
                private Object sortName;
                private Object orderBy;
                private Object fileds;
                private int totalPage;
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
                private int timezone_id;
                private String update_appointment_id;
                private String master_type;
                private String language;
                private int earnings;
                private int earnings_status;
                private String update_title;
                private String remarks;
                private String pipeline_num;
                private Object userAppointmentEntities;
                private Object identity;
                private Object whether;
                private Object map;
                private Object is_apply;
                private Object update_type;
                private Object studyCard;
                private Object masterNotice_id;
                private Object statistics_time;

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

                public int getTimezone_id() {
                    return timezone_id;
                }

                public void setTimezone_id(int timezone_id) {
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

                public int getEarnings() {
                    return earnings;
                }

                public void setEarnings(int earnings) {
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

                public Object getMap() {
                    return map;
                }

                public void setMap(Object map) {
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
            }

            public static class MasterinfoBean {
                /**
                 * account_state : 1
                 * admin_id : 0
                 * advertising_position : false
                 * avatar : http://img.curiousmore.com/1584104232184.jpg
                 * background_pic : http://img.curiousmore.com/1584354512798.jpg
                 * birth : 811526400000
                 * cash : 0.000
                 * cat_coin : 9.000
                 * chatting_count : 0
                 * city : 天津
                 * contacts_num : 2
                 * country : 中国
                 * create_time : 1554712462000
                 * distrib_qrcode :
                 * duration : 0
                 * hometown : 阿富汗·马扎里沙里夫
                 * identity_auth : false
                 * intro :
                 * invite_code :
                 * is_help : false
                 * is_high_quality : true
                 * is_id : false
                 * is_mail : false
                 * is_member : false
                 * is_mobile : false
                 * is_name : false
                 * is_robot : true
                 * is_teenagers : false
                 * is_unread : false
                 * lable :
                 * languages : 中文
                 * like_category :
                 * like_num : 8
                 * local_time : 1584354513871
                 * log_out_time : 1584131023000
                 * mail :
                 * member_level : 0
                 * message_num : 0
                 * mobile : 13956398572
                 * mother_tongue : Spanish
                 * national_flag : http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png
                 * nick_name : 唐世鹏
                 * online_state : 0
                 * oper_id : 0
                 * overseas_auth : false
                 * overseas_identity_name : 境外工作人员
                 * preference : 交友拍拖
                 * qrcode :
                 * rating : 0
                 * registr_num : 0
                 * sex : 1
                 * signature :
                 * update_time : 1584325713000
                 * url : http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260
                 * user_id : 4260
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

            public static class UserinfoBean {
                /**
                 * account_state : 1
                 * admin_id : 0
                 * advertising_position : false
                 * avatar : http://img.curiousmore.com/1583727854606.jpg
                 * background_pic : http://img.curiousmore.com/1584535512217.jpg
                 * birth : 1585843200000
                 * cash : 0.000
                 * cat_coin : 198.000
                 * chatting_count : 0
                 * city : 爱尔巴桑
                 * contacts_num : 2
                 * country : 阿尔巴尼亚
                 * create_time : 1564099314000
                 * distrib_qrcode :
                 * duration : 0
                 * hometown : 中国·北京
                 * identity_auth : false
                 * intro :
                 * invite_code :
                 * is_help : true
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
                 * like_num : 15
                 * local_time : 1586322630711
                 * log_out_time : 1584037085000
                 * mail : 1319817447@qq.com
                 * member_level : 0
                 * message_num : 0
                 * mobile : 18106548078
                 * mother_tongue : Polish
                 * national_flag :
                 * nick_name : kitychen1
                 * online_state : 1
                 * oper_id : 0
                 * overseas_auth : false
                 * overseas_identity_name : 学生
                 * preference : 交友拍拖
                 * qrcode :
                 * rating : 0
                 * registr_num : 0
                 * sex : 1
                 * signature :
                 * update_time : 1586293830000
                 * url : http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191
                 * user_id : 9191
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
