package com.huihe.base_lib.model.login;

import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.base.JsonResult;

public class WxLoginModel extends JsonResult<WxLoginModel.WxLoginEntity> {

    public static class WxLoginEntity extends LoginResultEntity {

        /**
         * reward : null
         * userToken : usertokeneyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5MzgzIiwiaWF0IjoxNTg1MDQ1NzkxLCJzdWIiOiJxbW9yZSIsImlzcyI6InVzZXIifQ.5FaAbk49x63Cf1LB1aave-jM5og_OWKY75IvdiXC6i0
         * userInfoEntity : {"pageSize":10,"currentPage":0,"totalItem":0,"startRow":0,"sortName":null,"orderBy":null,"fileds":null,"totalPage":0,"user_id":9383,"create_time":"2020-03-24 10:29:51","mobile":"","mail":"","pay_pass":null,"update_time":"2020-03-24 10:29:51","sex":1,"avatar":"","birth":"2020-03-24","lable":"","intro":"","cash":0,"signature":"","member_level":0,"is_member":false,"identity_auth":false,"cat_coin":0,"nick_name":"","national_flag":"","overseas_auth":false,"hometown":"","preference":"","is_robot":false,"admin_id":0,"advertising_position":false,"invite_code":"","qrcode":"","distrib_qrcode":"","like_num":0,"country":"","city":"","like_category":"","searchParam":null,"contacts_num":0,"message_num":0,"registr_num":0,"log_out_time":"2020-03-24 10:29:51","out_time":null,"overseas_identity_name":"","distance":null,"local_time":"2020-03-24 18:29:51","begin_time":null,"end_time":null,"oper_id":0,"user_idList":null,"vali_code":null,"languages":"","relationship":null,"online_state":0,"account_state":1,"chatting_count":0,"userPreferenceEntity":null,"is_id":false,"is_mobile":false,"is_name":false,"is_mail":false,"sign":null,"mother_tongue":"","background_pic":"","url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9383","is_high_quality":false,"rating":0,"is_help":false,"is_unread":false,"duration":0,"map":null,"loginStatistical":null,"master_type":null,"begin_time_statistics":null,"end_time_statistics":null,"statistics_time":null,"is_teenagers":false}
         * userSign : eJyrVgrxCdZLrSjILEpVsjIytTQyMDDQAQuWpRYpWSkZ6RkoQfjFKdmJBQWZKUpWhiYGBkampgYm
         * 5hCZzJTUvJLMtEywBktjC2OYjsx0oIC*dkCFuUtxpKFfirNBUq5nomeUf7GHj0FEZklhlKG7Wb6j
         * qUWUb662m4GFLVRjSWYu0DWGphZAO0zNLQ1rAWFhLsI_
         * TIMMessage : 1
         * user_id : 9383
         * message : 注册成功
         * type : register
         */

        private Object reward;
        private int TIMMessage;
        private int user_id;
        private String message;
        private String type;

        public Object getReward() {
            return reward;
        }

        public void setReward(Object reward) {
            this.reward = reward;
        }

        public int getTIMMessage() {
            return TIMMessage;
        }

        public void setTIMMessage(int TIMMessage) {
            this.TIMMessage = TIMMessage;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}
