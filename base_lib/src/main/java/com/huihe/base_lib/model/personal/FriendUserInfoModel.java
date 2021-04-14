package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonResult;

public class FriendUserInfoModel extends JsonResult<FriendUserInfoModel.UserInfo> {
    public class UserInfo{

        /**
         * userInfoEntity : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"user_id":9099,"create_time":"2019-04-12 16:40:47","mobile":"18196548552","mail":"","pay_pass":"","update_time":"2020-01-18 12:52:27","sex":2,"avatar":"http://img.curiousmore.com/F91-5A8C3F335498/Documents/1huihe1578645254.png","birth":"2020-01-10","lable":"","intro":"","cash":0,"signature":"","member_level":1,"is_member":true,"identity_auth":false,"cat_coin":21431,"nick_name":"车模ooo","national_flag":"","overseas_auth":false,"hometown":"卡范","preference":"语言学习","is_robot":false,"admin_id":0,"advertising_position":false,"invite_code":"","qrcode":"","distrib_qrcode":"","like_num":3,"country":"阿鲁巴","city":"阿森松岛","like_category":"","searchParam":null,"contacts_num":678,"message_num":0,"registr_num":1,"log_out_time":"2019-12-05 16:56:02","out_time":null,"overseas_identity_name":"留学生","distance":null,"local_time":"2020-01-18 17:20:50","begin_time":null,"end_time":null,"oper_id":0,"user_idList":null,"vali_code":null,"languages":"不限/法语","relationship":null,"online_state":1,"account_state":1,"chatting_count":220,"userPreferenceEntity":null,"is_id":false,"is_mobile":false,"is_name":false,"is_mail":false,"sign":null,"mother_tongue":"Dutch","background_pic":"http://img.curiousmore.com/D8302CD9-4B8B-45B0-AFA7-67A9E7B2AD1A/Documents/1huihe1564652984.png","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9099","is_high_quality":false,"rating":0,"is_help":true,"is_unread":false,"duration":0,"map":null,"loginStatistical":null,"master_type":null,"begin_time_statistics":null,"end_time_statistics":null,"statistics_time":null,"is_teenagers":false}
         * is_like : false
         * is_classmate : false
         * gift_count : 2
         */

        private UserInfoEntity userInfoEntity;
        private boolean is_like;
        private boolean is_classmate;
        private int gift_count;

        public UserInfoEntity getUserInfoEntity() {
            return userInfoEntity;
        }

        public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
            this.userInfoEntity = userInfoEntity;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public boolean isIs_classmate() {
            return is_classmate;
        }

        public void setIs_classmate(boolean is_classmate) {
            this.is_classmate = is_classmate;
        }

        public int getGift_count() {
            return gift_count;
        }

        public void setGift_count(int gift_count) {
            this.gift_count = gift_count;
        }

    }
}
