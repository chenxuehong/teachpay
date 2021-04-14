package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

public class UserRankingModel extends JsonListResult<UserRankingModel.UserRankingEntity> {

    public static class UserRankingEntity {

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * totalPage : 0
         * user_id : 36
         * pay_count : 16499
         * map : {"payinfo":{},"userinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/D32-A583E15C3A5C/Documents/1huihe1583337046.png","background_pic":"http://img.curiousmore.com/086D98D0-755F-44E8-AA3C-3A26177193A3/Documents/1huihe1557333807.png","birth":745459200000,"cash":"0.000","cat_coin":"988924.000","chatting_count":45,"city":"江干区","contacts_num":2390,"country":"中国","create_time":1554740185000,"distrib_qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/a134e723-a93a-3906-9f0d-af2784b201bc_200_200.jpg","duration":0,"hometown":"德兴市","identity_auth":false,"intro":"再艰难，只要内心坚定，一定会越来越好的","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"{帅哥,旅游达人}","languages":"英语/中文","like_category":"","like_num":2,"local_time":1583336842657,"log_out_time":1577251098000,"mail":"","member_level":3,"message_num":0,"mobile":"18658876977","mother_tongue":"Chinese","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/China.png","nick_name":"后浪","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"本国境友","preference":"语言学习","qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/1bcc6997-ff34-30bd-9df7-a5f19a138e63_200_200.jpg","rating":0,"registr_num":8,"sex":1,"signature":"跨境、境外社交领导者","update_time":1583336842000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=36","user_id":36}}
         */

        public String user_id;
        public String pay_count;
        public MapBean map;

        public String getUser_id() {
            return user_id;
        }

        public String getPay_count() {
            return pay_count;
        }

        public MapBean getMap() {
            return map;
        }

        public static class MapBean {

            /**
             * payinfo : {}
             * userinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/D32-A583E15C3A5C/Documents/1huihe1583337046.png","background_pic":"http://img.curiousmore.com/086D98D0-755F-44E8-AA3C-3A26177193A3/Documents/1huihe1557333807.png","birth":745459200000,"cash":"0.000","cat_coin":"988924.000","chatting_count":45,"city":"江干区","contacts_num":2390,"country":"中国","create_time":1554740185000,"distrib_qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/a134e723-a93a-3906-9f0d-af2784b201bc_200_200.jpg","duration":0,"hometown":"德兴市","identity_auth":false,"intro":"再艰难，只要内心坚定，一定会越来越好的","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"{帅哥,旅游达人}","languages":"英语/中文","like_category":"","like_num":2,"local_time":1583336842657,"log_out_time":1577251098000,"mail":"","member_level":3,"message_num":0,"mobile":"18658876977","mother_tongue":"Chinese","national_flag":"http://www.huihejituan.com/tripPictstorage/nationalflag/China.png","nick_name":"后浪","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"本国境友","preference":"语言学习","qrcode":"http://www.huihejituan.com/tripPictstorage/user/36/qrcode/1bcc6997-ff34-30bd-9df7-a5f19a138e63_200_200.jpg","rating":0,"registr_num":8,"sex":1,"signature":"跨境、境外社交领导者","update_time":1583336842000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=36","user_id":36}
             */

            private UserInfoEntity payinfo;
            private UserInfoEntity userinfo;

            public UserInfoEntity getPayinfo() {
                return payinfo;
            }

            public void setPayinfo(UserInfoEntity payinfo) {
                this.payinfo = payinfo;
            }

            public UserInfoEntity getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserInfoEntity userinfo) {
                this.userinfo = userinfo;
            }
        }
    }
}
