package com.huihe.base_lib.model;

public class SubCommentBean {
    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * sortName : null
     * orderBy : null
     * fileds : null
     * totalPage : 0
     * id : 420
     * note_id : 3462
     * user_id : 9191
     * content : 方法一
     * create_time : 2020-03-09 17:46:47
     * like_count : 0
     * status : 1
     * update_time : 2020-03-09 17:46:47
     * parent_id : 412
     * is_reply : true
     * reply_id : 4260
     * oper_id : null
     * map : {"is_like":false,"replyinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://www.huihejituan.com/tripPictstorage/user/2214/play/f49f0994-e4f7-4de8-807e-b0b400b1e028_200_200.jpg","background_pic":"http://img.curiousmore.com/A8F-713757C0D227/Documents/1huihe1576739038.png","birth":811555200000,"cash":"0.000","cat_coin":"20.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"尼加拉瓜","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":0,"local_time":1576738858472,"log_out_time":1576658501000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"跑腿代购","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1576738858000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1583322403182.jpg","birth":1546272000000,"cash":"0.000","cat_coin":"559.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"北京市·市辖区·东城区","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1583727634784,"log_out_time":1583316267000,"mail":"15514766147@qq.com","member_level":0,"message_num":0,"mobile":"13093793169","mother_tongue":"Chinese","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"搞笑/习俗","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1583727634000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}}
     */

    private String id;
    private String note_id;
    private String user_id;
    private String content;
    private String create_time;
    private Integer like_count;
    private Integer status;
    private String update_time;
    private Integer parent_id;
    private boolean is_reply;
    private Integer reply_id;
    private Object oper_id;
    private MapBean map;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isIs_reply() {
        return is_reply;
    }

    public void setIs_reply(boolean is_reply) {
        this.is_reply = is_reply;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public Object getOper_id() {
        return oper_id;
    }

    public void setOper_id(Object oper_id) {
        this.oper_id = oper_id;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        /**
         * is_like : false
         * replyinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://www.huihejituan.com/tripPictstorage/user/2214/play/f49f0994-e4f7-4de8-807e-b0b400b1e028_200_200.jpg","background_pic":"http://img.curiousmore.com/A8F-713757C0D227/Documents/1huihe1576739038.png","birth":811555200000,"cash":"0.000","cat_coin":"20.000","chatting_count":0,"city":"","contacts_num":0,"country":"","create_time":1554741262000,"distrib_qrcode":"","duration":0,"hometown":"尼加拉瓜","identity_auth":false,"intro":"","invite_code":"","is_help":false,"is_high_quality":true,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":true,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"","like_num":0,"local_time":1576738858472,"log_out_time":1576658501000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398572","mother_tongue":"Spanish","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/Nicaragua.png","nick_name":"唐世鹏","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作人员","preference":"跑腿代购","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1576738858000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=4260","user_id":4260}
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/1583727854606.jpg","background_pic":"http://img.curiousmore.com/1583322403182.jpg","birth":1546272000000,"cash":"0.000","cat_coin":"559.000","chatting_count":0,"city":"天津","contacts_num":2,"country":"中国","create_time":1564128114000,"distrib_qrcode":"","duration":0,"hometown":"北京市·市辖区·东城区","identity_auth":false,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"","like_category":"","like_num":15,"local_time":1583727634784,"log_out_time":1583316267000,"mail":"15514766147@qq.com","member_level":0,"message_num":0,"mobile":"13093793169","mother_tongue":"Chinese","national_flag":"","nick_name":"kitychen","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"学生","preference":"搞笑/习俗","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1583727634000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9191","user_id":9191}
         */

        private boolean is_like;
        private UserInfoEntity replyinfo;
        private UserInfoEntity userinfo;

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

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
