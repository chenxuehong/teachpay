package com.huihe.base_lib.model.personal;

import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.base.JsonListResult;

public class PaymentRankingModel extends JsonListResult<PaymentRankingModel.PaymentRankingEntity> {
    public static class PaymentRankingEntity{

        /**
         * pageSize : 10
         * currentPage : 0
         * totalItem : 0
         * startRow : 0
         * totalPage : 0
         * pay_count : 141307
         * payment_id : 9120
         * map : {"payinfo":{"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/5415C8D6-5088-41EF-9CBA-2022C0E6A559/Documents/1huihe1558927624.png","background_pic":"http://img.curiousmore.com/CAB-378A08DEFFB3/Documents/1huihe1577450667.png","birth":1558886400000,"cash":"0.000","cat_coin":"410.000","chatting_count":220,"city":"沙迦","contacts_num":173,"country":"外教","create_time":1558927515000,"distrib_qrcode":"","duration":0,"hometown":"马扎里沙里夫","identity_auth":true,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"汉语/英语/法语","like_category":"","like_num":1,"local_time":1577927056567,"log_out_time":1571189258000,"mail":"","member_level":2,"message_num":0,"mobile":"13296728663","mother_tongue":"Chinese","national_flag":"","nick_name":"可口可乐","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"交友拍拖","qrcode":"","rating":7,"registr_num":0,"sex":2,"signature":"","update_time":1577927056000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9120","user_id":9120},"userinfo":{}}
         */


        private String pay_count;
        private String payment_id;
        private MapBean map;

        public String getPay_count() {
            return pay_count;
        }

        public void setPay_count(String pay_count) {
            this.pay_count = pay_count;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public static class MapBean {
            /**
             * payinfo : {"account_state":1,"admin_id":0,"advertising_position":false,"avatar":"http://img.curiousmore.com/5415C8D6-5088-41EF-9CBA-2022C0E6A559/Documents/1huihe1558927624.png","background_pic":"http://img.curiousmore.com/CAB-378A08DEFFB3/Documents/1huihe1577450667.png","birth":1558886400000,"cash":"0.000","cat_coin":"410.000","chatting_count":220,"city":"沙迦","contacts_num":173,"country":"外教","create_time":1558927515000,"distrib_qrcode":"","duration":0,"hometown":"马扎里沙里夫","identity_auth":true,"intro":"","invite_code":"","is_help":true,"is_high_quality":false,"is_id":false,"is_mail":false,"is_member":true,"is_mobile":false,"is_name":false,"is_robot":false,"is_teenagers":false,"is_unread":false,"lable":"","languages":"汉语/英语/法语","like_category":"","like_num":1,"local_time":1577927056567,"log_out_time":1571189258000,"mail":"","member_level":2,"message_num":0,"mobile":"13296728663","mother_tongue":"Chinese","national_flag":"","nick_name":"可口可乐","online_state":1,"oper_id":0,"overseas_auth":false,"overseas_identity_name":"","preference":"交友拍拖","qrcode":"","rating":7,"registr_num":0,"sex":2,"signature":"","update_time":1577927056000,"url":"http://www.curiousmore.com:8768/eg-api/push/people.html?qmore_id=9120","user_id":9120}
             * userinfo : {}
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
