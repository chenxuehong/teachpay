package com.huihe.base_lib.model;

public class NoteCommentEntity {
    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 259
     * note_id : 3435
     * user_id : 9099
     * content : 他说你
     * create_time : 2020-01-20 10:52:45
     * like_count : 0
     * status : 1
     * update_time : 2020-01-20 10:52:45
     * parent_id : 0
     * is_reply : false
     * reply_id : 9099
     * oper_id : null
     * map : {"replyinfo":{},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"/storage/emulated/0/DCIM/Camera/IMG_20200109_120915.jpg","birth":1578585600000,"cash":"0.000","cat_coin":"21451.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1579484213528,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1579484213000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}}
     */

    private String id;
    private String note_id;
    private String user_id;
    private String content;
    private String create_time;
    private String like_count;
    private String status;
    private String update_time;
    private String parent_id;
    private boolean is_reply;
    private String reply_id;
    private String oper_id;
    private MapBean map;

    public String getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote_id() {
        return note_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getLike_count() {
        return like_count;
    }

    public String getStatus() {
        return status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getParent_id() {
        return parent_id;
    }

    public boolean isIs_reply() {
        return is_reply;
    }

    public String getReply_id() {
        return reply_id;
    }

    public String getOper_id() {
        return oper_id;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        /**
         * replyinfo : {}
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","background_pic":"/storage/emulated/0/DCIM/Camera/IMG_20200109_120915.jpg","birth":1578585600000,"cash":"0.000","cat_coin":"21451.000","chatting_count":220,"city":"阿森松岛","contacts_num":678,"country":"阿鲁巴","create_time":1555058447000,"distrib_qrcode":"","duration":0,"hometown":"卡范","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"不限/法语","like_category":"","like_num":3,"local_time":1579484213528,"log_out_time":1575536162000,"mail":"","member_level":1,"message_num":0,"mobile":"18196548552","mother_tongue":"Dutch","national_flag":"","nick_name":"车模ooo","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"留学生","preference":"语言学习","qrcode":"","rating":0,"registr_num":1,"sex":2,"signature":"","update_time":1579484213000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","user_id":9099}
         */

        private UserInfoEntity replyinfo;
        private UserInfoEntity userinfo;

        public UserInfoEntity getReplyinfo() {
            return replyinfo;
        }

        public void setReplyinfo(UserInfoEntity replyinfo) {
            this.replyinfo = replyinfo;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserInfoEntity userinfo) {
            this.userinfo = userinfo;
        }

    }
}
