package com.huihe.base_lib.model.dynamic;

import com.huihe.base_lib.model.GiftInfoEntity;
import com.huihe.base_lib.model.LikeInfoEntity;
import com.huihe.base_lib.model.MasterSetPriceEntity;
import com.huihe.base_lib.model.NoteCommentEntity;
import com.huihe.base_lib.model.UserInfoEntity;

import java.util.List;

public class NoteUserEntity {


    /**
     * pageSize : 10
     * currentPage : 0
     * totalItem : 0
     * startRow : 0
     * totalPage : 0
     * id : 3365
     * user_id : 9108
     * content : It's easier to give up, but you won't get anything in the end. It's harder to keep going, but you would get something in the end.
     * picts : http://img.curiousmore.com/tmp_fc72b2aca20d4898e2e133f6bc603fbcbf4b9aca4ef23bf2.jpg
     * location : 中国杭州市
     * create_time : 2019-12-07 23:41:09
     * status : 3
     * like_count : 0
     * comment_count : 0
     * share_count : 0
     * watch_count : 0
     * style : 1
     * cover :
     * img_height : 332
     * img_width : 500
     * video_duration :
     * classfiy : 其他
     * collect_count : 0
     * url : http://www.curiousmore.com:8768/eg-api/push/noteshare.html?note_id=3365
     * map : {"likeinfo":[],"noteCommentEntities":[],"giftinfo":[],"giftCount":0,"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/tmp_92e6f2080877f12978d3ce94ffdb5c94ad00777d6d60ad41.jpg","background_pic":"http://img.curiousmore.com/tmp/wxdbd9410523ec2921.o6zAJs2bel8UMMafp-gtnT7OfHzo.UoKL5WNRQfck656bf42eed4d66d74b518954114f7bdf.jpg","birth":813081600000,"cash":"0.000","cat_coin":"140.000","chatting_count":0,"city":"唐山","contacts_num":11,"country":"中国","create_time":1555393232000,"distrib_qrcode":"","duration":0,"hometown":"埃塞俄比亚","identity_auth":true,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"交友","like_num":3,"local_time":1574349168611,"log_out_time":1574150513000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398571","mother_tongue":"Chinese","national_flag":"","nick_name":"Miracles","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作者","preference":"风景,美食","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1574349168000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9108","user_id":9108},"commentCount":0}
     */

    private String id;
    private String user_id;
    private String content;
    private String picts;
    private String location;
    private String create_time;
    private String status;
    private int like_count;
    private int comment_count;
    private int share_count;
    private int watch_count;
    private int style;
    private String cover;
    private String video_duration;
    private String classfiy;
    private int collect_count;
    private String url;
    private String categories;
    private MapBean map;

    public String getCategories() {
        return categories;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicts() {
        return picts;
    }

    public void setPicts(String picts) {
        this.picts = picts;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public int getWatch_count() {
        return watch_count;
    }

    public void setWatch_count(int watch_count) {
        this.watch_count = watch_count;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }

    public String getClassfiy() {
        return classfiy;
    }

    public void setClassfiy(String classfiy) {
        this.classfiy = classfiy;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        /**
         * likeinfo : []
         * noteCommentEntities : []
         * giftinfo : []
         * giftCount : 0
         * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/tmp_92e6f2080877f12978d3ce94ffdb5c94ad00777d6d60ad41.jpg","background_pic":"http://img.curiousmore.com/tmp/wxdbd9410523ec2921.o6zAJs2bel8UMMafp-gtnT7OfHzo.UoKL5WNRQfck656bf42eed4d66d74b518954114f7bdf.jpg","birth":813081600000,"cash":"0.000","cat_coin":"140.000","chatting_count":0,"city":"唐山","contacts_num":11,"country":"中国","create_time":1555393232000,"distrib_qrcode":"","duration":0,"hometown":"埃塞俄比亚","identity_auth":true,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":false,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"中文","like_category":"交友","like_num":3,"local_time":1574349168611,"log_out_time":1574150513000,"mail":"","member_level":0,"message_num":0,"mobile":"13956398571","mother_tongue":"Chinese","national_flag":"","nick_name":"Miracles","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"境外工作者","preference":"风景,美食","qrcode":"","rating":0,"registr_num":0,"sex":1,"signature":"","update_time":1574349168000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9108","user_id":9108}
         * commentCount : 0
         */

        private Integer giftCount;
        private MasterSetPriceEntity masterSetPriceEntity;
        private boolean is_like;
        private UserInfoEntity userinfo;
        private Integer commentCount;
        private List<LikeInfoEntity> likeinfo;
        private List<NoteCommentEntity> noteCommentEntities;
        private List<GiftInfoEntity> giftinfo;

        public MasterSetPriceEntity getMasterSetPriceEntity() {
            return masterSetPriceEntity;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public int getGiftCount() {
            return giftCount;
        }

        public void setGiftCount(int giftCount) {
            this.giftCount = giftCount;
        }

        public UserInfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserInfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public List<LikeInfoEntity> getLikeinfo() {
            return likeinfo;
        }
        public void setLikeinfo(List<LikeInfoEntity> likeinfo) {
            this.likeinfo = likeinfo;
        }

        public List<NoteCommentEntity> getNoteCommentEntities() {
            return noteCommentEntities;
        }

        public void setNoteCommentEntities(List<NoteCommentEntity> noteCommentEntities) {
            this.noteCommentEntities = noteCommentEntities;
        }

        public List<GiftInfoEntity> getGiftinfo() {
            return giftinfo;
        }

        public void setGiftinfo(List<GiftInfoEntity> giftinfo) {
            this.giftinfo = giftinfo;
        }

    }
}
